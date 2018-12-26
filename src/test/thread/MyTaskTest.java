package thread;

import com.wang.thread.MyTask;
import com.wang.thread.ThreadDemo;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by WANGDD on 2017-10-14.
 */
public class MyTaskTest {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(50));

    @Test
    public void taskTest() {

        for(int i=0; i<20; i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println(i + "线程池中线程数目："+executor.getPoolSize()+
                    "，队列中等待执行的任务数目："+ executor.getQueue().size()+
                    "，已执行完任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        while (true) {
//            synchronized (td) {  //加上synchronized关键字或flag用关键字volatile修饰(强制线程每次读取该值的时候都去“主内存”中取值)，都可以读取到true
                if (td.isFlag()) {
                    System.out.println("-------while----" + td.isFlag());
                    break;
//                }
            }
        }
    }
}
