package com.wang.thread;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by wangdd on 2018/11/15.
 * 生产线程
 */
public class ProductThread implements Runnable {

    private int taskNum;
    private ArrayBlockingQueue queue;

    public ProductThread(int taskNum, ArrayBlockingQueue queue) {
        this.taskNum = taskNum;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            //模拟生产
            Thread.currentThread().sleep(1000);
//            System.out.println("开始生产");
            queue.add(taskNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
