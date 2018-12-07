package com.wang.dao.conpool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @Auther: wangdingding5
 * @Date: 2018/12/7 14:54
 * @Description:
 */
@Service
public class JdbcPool implements IPool {

    @Value("${spring.datasource.driver-class-name}")
    private String jdbcDriver;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.init-connect-count}")
    private static Integer initConnectCount;

    @Value("${spring.datasource.max-connects}")
    private static Integer maxConnects;

    @Value("${spring.datasource.increment-count}")
    private static Integer incrementCount;

    private static Vector<PoolConnection> connections = new Vector<>();

    /**
     * 通过实例初始化块来初始化
     * */ {
        try {
            /*
             * 注册jdbc驱动
             * */
            Driver driver = (Driver) Class.forName(jdbcDriver).newInstance();
            DriverManager.registerDriver(driver);
            /*
             * 根据initConnectCount来初始化连接池
             * */
            createConnections(initConnectCount);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取可用连接
     */
    @Override
    public PoolConnection getConnection() {
        if (connections.isEmpty()) {
            System.out.println("连接池中没有连接");
            throw new RuntimeException("连接池中没有连接");
        }

        return getActiveConnection();
    }

    @Override
    public Connection getConnectionNoPool() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * 同步方法来获取连接池中可用连接，在多线程情况下，只有一个线程访问该方法来获取连接，防止由于多线程情况下多个线程获取同一个连接从而引起出错
     */
    private synchronized PoolConnection getActiveConnection() {
        /*
         * 通过循环来获取可用连接，若获取不到可用连接，则依靠无限循环来继续获取
         * */
        while (true) {
            for (PoolConnection con : connections) {
                if (!con.isUse()) {
                    Connection trueConn = con.getConn();
                    try {
                        //验证连接是否失效 0表示不校验超时
                        if (!trueConn.isValid(0)) {
                            con.setConn(DriverManager.getConnection(jdbcUrl, username, password));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    con.setUse(true);
                    return con;
                }
            }
            /*
             * 根据连接池中连接数量从而判断是否增加对应的数量的连接
             * */
            if (connections.size() <= maxConnects - incrementCount) {
                createConnections(incrementCount);
            } else if (connections.size() < maxConnects && connections.size() > maxConnects - incrementCount) {
                createConnections(maxConnects - connections.size());
            }
        }
    }

    /*
     * 创建对应数量的连接并放入连接池中
     * */
    private void createConnections(int count) {
        for (int i = 0; i < count; i++) {
            if (maxConnects > 0 && connections.size() >= maxConnects) {
                System.out.println("连接池中连接数量已经达到最大值");
                throw new RuntimeException("连接池中连接数量已经达到最大值");
            }
            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                /*
                 * 将连接放入连接池中，并将状态设为可用
                 * */
                connections.add(new PoolConnection(connection, false));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 获取连接池中连接数量
     * */
    public int getSize() {
        return connections.size();
    }

}
