package com.wang.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by wangdd on 2018/11/15.
 * 线程池测试
 */
public class ThreadExecutorTest {

    private static final Logger logger = LoggerFactory.getLogger(ThreadExecutorTest.class);

    ThreadExecutor executor = new ThreadExecutor(100, 5000);

    ArrayBlockingQueue queue = new ArrayBlockingQueue(10000);

    public static void main(String[] args) throws InterruptedException {
        ThreadExecutor executor = new ThreadExecutor(100, 1000);
        for (int i = 0; i < 100; i++) {
            executor.exec(() -> System.out.println("线程 " + Thread.currentThread().getName() + " 在帮我干活"));
        }
        executor.shutdown();
    }

    /**
     * 模拟生产者消费者
     */
    @Test
    public void producerTest() {
//        ThreadExecutor executor = new ThreadExecutor(100, 5000);
        int i =0;
        while (true) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.exec(new ProductThread(i, queue));
            i++;
            logger.info("生产第{}个。", i);

            //消费者
            ThreadExecutor consumerExecutor = new ThreadExecutor(100, 10000);
            ConsumerThread consumerThread = new ConsumerThread(queue);
            consumerExecutor.exec(consumerThread);
        }
    }

    /**
     * 消费者
     */
    @Test
    public void consumerTest() {
        ThreadExecutor consumerExecutor = new ThreadExecutor(100, 1000);
        ConsumerThread consumerThread = new ConsumerThread(queue);
        while (true) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.exec(consumerThread);
        }
    }

}
