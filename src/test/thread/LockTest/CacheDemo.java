package thread.LockTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wangdd on 2018/6/14.
 * 读写锁用于缓存系统
 * 续进阶，如果现在要缓存多个数据，即要写一个缓存系统，那该如何做呢？一个缓存系统无非就是一个容器，可以存储很多缓存数据，很自然的想到使用一个Map，专门装缓存数据，然后供多个线程去使用。所以整个设计思路，跟上面缓存单个数据是一样的，不过就是多考虑一些东西而已，看下代码
 */
public class CacheDemo {

    /**
     * 整个代码的结构和上面的一样，理解了缓存单个数据后，这个代码也不难理解。这里只是个demo，实际中可以是跟数据库打交道，第一次从缓存中拿肯定是没有的，那么就要去数据库中查，然后把取到的数据放到缓存中，下次别的线程来就能直接从缓存中取了。看一下运行结果：
     * Thread-0 write cache for cache1
     Thread-0 has already written cache!
     Thread-4 write cache for cache2
     Thread-0: aaa1464782404722
     Thread-4 has already written cache!
     Thread-4: aaa1464782404723
     Thread-3: aaa1464782404723
     Thread-2: aaa1464782404722
     Thread-1: aaa1464782404722
     Thread-5: aaa1464782404723
     * @param args
     */
    public static void main(String[] args) {

        Cache cac = new Cache();
        for(int i = 0; i < 3; i ++) { //开启三个线程去缓存中拿key为cache1的数据，
            new Thread(new Runnable() {

                @Override
                public void run() {
                    String value = (String) cac.getData("cache1"); //第一个进入的线程要先写一个数据进去（相当于第一次从数据库中取）
                    System.out.println(Thread.currentThread().getName() + ": " + value);
                }
            }).start();
        }

        for(int i = 0; i < 3; i ++) { //开启三个线程去缓存中拿key为cacahe2的数据
            new Thread(new Runnable() {

                @Override
                public void run() {
                    String value = (String) cac.getData("cache2");//第一个进入的线程要先写一个数据进去（相当于第一次从数据库中取）
                    System.out.println(Thread.currentThread().getName() + ": " + value);
                }
            }).start();
        }
    }
}

class Cache {
    //存储缓存数据的Map，注意HashMap是非线程安全的，也要进行同步操作
    private Map<String, Object> cache = Collections.synchronizedMap(new HashMap<String, Object>());
    private ReadWriteLock rwl = new ReentrantReadWriteLock(); //定义读写锁

    public synchronized Object getData(String key) {
        rwl.readLock().lock(); //上读锁
        Object value = null;
        try {
            value = cache.get(key); //根据key从缓存中拿数据
            if (value == null) { //如果第一次那该key对应的数据，拿不到
                rwl.readLock().unlock(); //释放读锁
                rwl.writeLock().lock(); //换成写锁
                try {
                    if (value == null) { //之所以再去判断，是为了防止几个线程同时进入了上面那个if，然后一个个都来重写赋值一遍
                        System.out.println(Thread.currentThread().getName() + " write cache for " + key);
                        value = "aaa" + System.currentTimeMillis(); // 实际中是去数据库中取，这里只是模拟
                        cache.put(key, value); //放到缓存中
                        System.out.println(Thread.currentThread().getName() + " has already written cache!");
                    }
                } finally {
                    rwl.writeLock().unlock(); //写完了释放写锁
                }
                rwl.readLock().lock(); //换读锁
            }
        } finally {
            rwl.readLock().unlock(); //最后呢释放读锁
        }
        return value; //返回要取的数据
    }
}
