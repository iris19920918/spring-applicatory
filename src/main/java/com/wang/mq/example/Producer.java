package com.wang.mq.example;

/**
 * Created by wangdd on 2018/11/16.
 * 生产者
 */
public class Producer implements Runnable {

    private String name;
    private Storage s = null;

    public Producer(String name, Storage s) {
        this.name = name;
        this.s = s;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Product product = new Product((int) (Math.random() * 10000)); // 产生0~9999随机整数
//                System.out.println("线程：" + Thread.currentThread().getName() + "，" +name + "准备生产(" + product.toString() + ").");
                s.push(product);
                System.out.println("线程：" + Thread.currentThread().getName() + "，" + name + "已生产(" + product.toString() + ").仓库剩余" + s.getSize());
                System.out.println("===============");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
