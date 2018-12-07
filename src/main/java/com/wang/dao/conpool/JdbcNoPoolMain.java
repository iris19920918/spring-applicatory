package com.wang.dao.conpool;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 */
public class JdbcNoPoolMain {

    static final int threadSize = 200;

    static JdbcPool jdbcPool = PoolManager.getInstance();

    static CountDownLatch countDownLatch1 = new CountDownLatch(1);
    static CountDownLatch countDownLatch2 = new CountDownLatch(threadSize);

    public static void main(String[] args) throws InterruptedException {
        threadTest();
    }

    public static void threadTest() throws InterruptedException {
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < threadSize; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //使得线程阻塞到coutDownLatch1为0时才执行
                        countDownLatch1.await();
                        selectNoPool();
                        //每个独立子线程执行完后，countDownLatch2减1
                        countDownLatch2.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        //将countDownLatch1置为0，从而使线程并发执行
        countDownLatch1.countDown();

        //等待countDownLatch2变为0时才继续执行
        countDownLatch2.await();
        long time2 = System.currentTimeMillis();

        System.out.println("thread size: "+threadSize+" no use pool :" + (time2 - time1) + "ms.");
    }

    public static void selectNoPool() throws SQLException {

        Connection conn = jdbcPool.getConnectionNoPool();
        Statement sm = null;
        ResultSet rs = null;
        try {
            sm = conn.createStatement();
            rs = sm.executeQuery("select * from model_released_db.component dc");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                System.out.println(Thread.currentThread().getName()+" ==== "+"identification: "+rs.getString("c_component_id")+" name: "+rs.getString("c_display_name_zh_cn"));
            }
            Thread.sleep(100);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rs.close();
        sm.close();
        conn.close();
    }

}