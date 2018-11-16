package com.wang.mq.example;

/**
 * Created by wangdd on 2018/11/16.
 */
public class Consumer implements Runnable {

    private String name;
    private Storage s = null;

    public Consumer(String name, Storage s) {
        this.name = name;
        this.s = s;
    }

    @Override
    public void run() {
        try {
            while (true) {
//                System.out.println("线程：" + Thread.currentThread().getName() + "，" + name + "准备消费产品.");
                Product product = s.pop();
                System.out.println("线程：" + Thread.currentThread().getName() + "，" + name + "已消费(" + product.toString() + ").仓库还剩" + s.getSize() + "个.");
                System.out.println("===============");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
