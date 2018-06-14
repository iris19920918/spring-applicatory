package thread.LockTest;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wangdd on 2018/6/14.
 * 读写锁用于缓存数据
 * 现在使用读写锁写一个稍微高级一点的应用demo，即模拟缓存数据。实现的功能如下：现在有5个线程都需要拿数据，一开始是没有数据的，所以最先去拿数据的那个线程发现没数据，它就得去初始化一个数据，然后其他线程拿数据的时候就可以直接拿了
 */
public class ReadWriteLockTest2 {

    /**
     * 从代码中可以看出，在processCache方法中对读锁和写锁的交替使用。一开始进来都是读数据的，所以一开始都是上了读锁，但当第一个线程进来发现没有缓存数据的时候，它得写数据，那么此时它得先把读锁给释放掉，换了把写锁，告诉其他线程：”哎哥们，这里面边儿根本没数据啊，我们被坑了，让我先弄个数据来吧，不好意思你们先等会儿~“，等该线程初始化好了数据后，其他线程就可以读了，于是它又把读锁装起来了，把写锁释放了，然后它出去了。这就模拟了拿缓存数据的一个demo，可以看出，在一个方法中，同一个线程可以操作两个锁的。看一下运行结果：
     * @param args
     */
    public static void main(String[] args) {

        CacheData cache = new CacheData();

        for(int i = 1; i <= 5; i ++) { //开启5个线程
            new Thread(new Runnable() {

                @Override
                public void run() {
                    cache.processCache(); //都去拿数据
                }
            }).start();
        }
    }
}

class CacheData {

    private Object data = null; // 需要缓存的数据
    private boolean cacheValid; //用来标记是否有缓存数据
    private ReadWriteLock rwl = new ReentrantReadWriteLock();// 定义读写锁

    public void processCache() {
        rwl.readLock().lock(); //上读锁

        if(!cacheValid) { //如果没有缓存，那说明是第一次访问，需要给data赋个值
            rwl.readLock().unlock(); //先把读锁释放掉
            rwl.writeLock().lock(); //上写锁
            if(!cacheValid) {
                System.out.println(Thread.currentThread().getName() + ": no cache!");
                data = new Random().nextInt(1000); //赋值
                cacheValid = true; //标记已经有缓存了
                System.out.println(Thread.currentThread().getName() + ": already cached!");
            }
            rwl.readLock().lock(); //再把读锁上上
            rwl.writeLock().unlock(); //把刚刚上的写锁释放掉
        }
        System.out.println(Thread.currentThread().getName() + " get data: " + data);
        rwl.readLock().unlock(); //释放读锁
    }
}
