package com.wang.thread;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by wangdd on 2018/11/15.
 */
public class ConsumerThread implements Runnable {

    private ArrayBlockingQueue queue;

    public ConsumerThread(ArrayBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
//        System.out.println("准备消费");
        int taskNum = 0;
        try {
            Thread.sleep(7000);
            taskNum = (int) queue.take();
            System.out.println("消费头部为" + taskNum + "；剩余" + queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
