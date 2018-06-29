package thread;

import com.wang.thread.MyTask;
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
}
