package com.wang.mq.example;

import com.wang.thread.ThreadExecutor;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangdd on 2018/11/16.
 * java多线程模拟生产者消费者问题
 * ProducerConsumer是主类，Producer生产者，Consumer消费者，Product产品，Storage仓库
 */
public class ProducerConsumer {

    public static void main(String[] args) {

        Storage s = new Storage();
//
//        ExecutorService service = Executors.newCachedThreadPool();
//        Producer p = new Producer("张三", s);
//        Producer p2 = new Producer("李四", s);
//        Consumer c = new Consumer("王五", s);
//        Consumer c2 = new Consumer("老刘", s);
//        Consumer c3 = new Consumer("老林", s);
//        service.submit(p);
//        //service.submit(p2);
//        service.submit(c);
//        service.submit(c2);
//        service.submit(c3);

        //自己写的线程池模拟
        ThreadExecutor producerExecutor = new ThreadExecutor(2, 1000);

        //生产
        Producer p = new Producer("一", s);
        Producer p2 = new Producer("二", s);
        Producer p3 = new Producer("三", s);
        Producer p4 = new Producer("四", s);
        Producer p5 = new Producer("五", s);
        Producer p6 = new Producer("六", s);
        producerExecutor.exec(p);
        producerExecutor.exec(p2);
        producerExecutor.exec(p3);
        producerExecutor.exec(p4);
//        producerExecutor.exec(p5);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //消费
        ThreadExecutor consumerExecutor = new ThreadExecutor(10, 1000);
        Consumer c = new Consumer("壹", s);
        Consumer c2 = new Consumer("贰", s);
        Consumer c3 = new Consumer("叁", s);
        Consumer c4 = new Consumer("肆", s);
        Consumer c5 = new Consumer("伍", s);
        Consumer c6 = new Consumer("陆", s);
        Consumer c7 = new Consumer("柒", s);
        consumerExecutor.exec(c);
        consumerExecutor.exec(c2);
        consumerExecutor.exec(c3);
        consumerExecutor.exec(c4);
        consumerExecutor.exec(c5);
        consumerExecutor.exec(c6);
        consumerExecutor.exec(c7);

    }

    /**
     * 使用自己写的线程池模拟生产者消费者
     */
    @Test
    public void producerConsumerTest(){
        Storage s = new Storage();

        ThreadExecutor executor = new ThreadExecutor(2, 1000);

        //生产
        Producer p = new Producer("张三", s);
        Producer p2 = new Producer("李四", s);
        executor.exec(p);
        executor.exec(p2);

        //消费
        Consumer c = new Consumer("王五", s);
        Consumer c2 = new Consumer("老刘", s);
        Consumer c3 = new Consumer("老林", s);
        executor.exec(c);
        executor.exec(c2);
        executor.exec(c3);
    }


}
