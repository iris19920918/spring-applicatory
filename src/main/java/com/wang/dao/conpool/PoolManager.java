package com.wang.dao.conpool;

/**
 * @Auther: wangdingding5
 * @Date: 2018/12/7 15:01
 * @Description: 连接池维护类(单例模式)
 */
public class PoolManager {

    /**
     * 静态内部类实现连接池的单例
     * */
    private static class CreatePool{
        private static JdbcPool pool = new JdbcPool();
    }

    public static JdbcPool getInstance(){
        return CreatePool.pool;
    }
}
