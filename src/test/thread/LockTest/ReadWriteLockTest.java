package thread.LockTest;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wangdd on 2018/6/14.
 *  读写锁的妙用
 * 锁又分为读锁和写锁，读锁与读锁不互斥，读锁与写锁互斥，写锁与写锁互斥，这是由jvm自己控制的。这很好理解，读嘛，大家都能读，不会对数据造成修改，只要涉及到写，那就可能出问题。 我们写代码的时候只要在正确的位置上相应的锁即可。读写锁有个接口叫ReadWriteLock，我们可以创建具体的读写锁实例，通过读写锁也可以拿到读锁和写锁。下面看一下读写锁的例子：
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        final Queue3 q3 = new Queue3(); //封装共享的数据、读写锁和待执行的任务的类

        for (int i = 0; i < 3; i++) {
            new Thread() { // 开启三个线程写数据
                public void run() {
                    while (true) {
                        q3.put(new Random().nextInt(10000));
                    }
                }
            }.start();

            new Thread() { // 开启三个线程读数据
                public void run() {
                    while (true) {
                        q3.get();
                    }
                }
            }.start();
        }
    }
}

class Queue3 {

    private Object data = null; // 共享的数据
    private ReadWriteLock rwl = new ReentrantReadWriteLock();// 定义读写锁

    // 读取数据的任务方法
    public void get() {
        rwl.readLock().lock(); // 上读锁
        try {
            System.out.println(Thread.currentThread().getName()
                    + ":before read: " + data); // 读之前打印数据显示

            Thread.sleep((long) (Math.random() * 1000)); // 睡一会儿~

            System.out.println(Thread.currentThread().getName()
                    + ":after read: " + data); // 读之后打印数据显示
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();// 释放读锁
        }
    }

    // 写数据的任务方法
    public void put(Object data) {
        rwl.writeLock().lock(); // 上写锁
        try {
            System.out.println(Thread.currentThread().getName()
                    + ":before write: " + this.data); // 读之前打印数据显示

            Thread.sleep((long) (Math.random() * 1000)); // 睡一会儿~
            this.data = data; //写数据

            System.out.println(Thread.currentThread().getName()
                    + ":after write: " + this.data); // 读之后打印数据显示
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();// 释放写锁
        }
    }
}
