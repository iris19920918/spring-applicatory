package com.wang.mq.example;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by wangdd on 2018/11/16.
 * 仓库，用来存放产品
 */
public class Storage {

    BlockingDeque<Product> queues = new LinkedBlockingDeque<>();

    /**
     * 生产
     * @param p 产品
     * @throws InterruptedException
     */
    public void push(Product p) throws InterruptedException {
        queues.put(p);
    }

    /**
     * 消费
     * @return
     * @throws InterruptedException
     */
    public Product pop() throws InterruptedException {
        return queues.take();
    }

    /**
     * 获取仓库容量
     * @return
     */
    public int getSize() {
        return queues.size();
    }
}
