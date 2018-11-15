package com.wang.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangdd on 2018/11/6.
 */
public class Test4 {
    class DTask implements Runnable {
        private CountDownLatch downLatch;

        private String name;

        public DTask(CountDownLatch downLatch, String name) {
            this.downLatch = downLatch;
            this.name = name;
        }

        @Override
        public void run() {
//            if (name.equals("A")) {
                try {
                    this.downLatch.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//            }
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + this.name + "====>" + i);
            }

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // if(name.equals("B"))
            this.downLatch.countDown();
        }

    }

    public static void test1() {
        ExecutorService service = Executors.newFixedThreadPool(3);
        Test4 tt = new Test4();
        CountDownLatch downLatch = new CountDownLatch(20);
        service.execute(tt.new DTask(downLatch, "A"));
        service.execute(tt.new DTask(downLatch, "B"));
        service.execute(tt.new DTask(downLatch, "B"));
        service.shutdown();

    }

    public static void main(String[] args) {
        test1();

    }

}
