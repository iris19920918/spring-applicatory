package com.wang.thread;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wangdd on 2018/11/15.
 * 手写简单的线程池
 */
public class ThreadExecutor {

    private volatile boolean RUNNING = true;

    private static BlockingQueue<Runnable> queue = null; //阻塞队列

    int poolSize = 0;

    int coreSize = 0;

    boolean shutdown = false;

    //对于一个final变量，如果是基本数据类型的变量，则其数值一旦在初始化之后便不能更改；如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。
    private final HashSet<Worker> workers = new HashSet<>();

    private final ArrayList<Thread> threadList = new ArrayList<>();

    //构造函数,
    public ThreadExecutor(int poolSize, int queueNum) {
        this.poolSize = poolSize;
        queue = new LinkedBlockingQueue<>(queueNum);
    }

    /**
     * 执行
     * @param runnable
     */
    public void exec(Runnable runnable) {
        if (runnable == null) throw new NullPointerException();
        if (coreSize < poolSize) {
            addThread(runnable);
        } else {
            try {
                queue.put(runnable);                //coreSize>poolSize 加入阻塞队列中去
                System.out.println("加入阻塞队列" + runnable.toString() + "队列长度：" + queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加线程
     *
     * @param runnable
     */
    public void addThread(Runnable runnable) {
        coreSize++;                                 //正在工作的线程+1
        Worker worker = new Worker(runnable);        //
        workers.add(worker);
        Thread t = new Thread(worker);
        threadList.add(t);
        try {
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void shutdown() {
        RUNNING = false;
        if (!workers.isEmpty()) {
            workers.forEach(Worker::interruptIfIdle);
        }
        shutdown = true;
        Thread.currentThread().interrupt();
    }

    /**
     * 消费者
     */
    class Worker implements Runnable {

        public Worker(Runnable runnable) {
            queue.offer(runnable);
        }

        @Override
        public void run() {
            while (true && RUNNING) {
                if (shutdown == true) {
                    Thread.interrupted();
                }
                Runnable task = null;
                try {
                    task = getTask();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task.run();
            }
        }

        public Runnable getTask() throws InterruptedException {
            return queue.take();
        }

        /**
         * 结束线程池
         */
        public void interruptIfIdle() {
            for (Thread thread : threadList) {
                System.out.println(thread.getName() + " interrupt");
                thread.interrupt();
            }
        }

    }


}
