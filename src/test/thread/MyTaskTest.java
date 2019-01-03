package thread;

import com.alibaba.fastjson.JSONObject;
import com.wang.Application;
import com.wang.thread.MyTask;
import com.wang.thread.ThreadDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by WANGDD on 2017-10-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class MyTaskTest {

    private static final Logger logger = LoggerFactory.getLogger(MyTaskTest.class);

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 1000, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(50000));

    @Autowired private RestTemplate restTemplate;

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
     * 测试volatile关键字
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

    /**
     * 模拟并发请求-检测数据库连接池
     */
    @Test
    public void dataSourcePoolTest() {
        for (int j = 0; j < 9999999; j++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.execute(() -> {
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String result = restTemplate
                            .postForObject("http://127.0.0.1:9090/es-dre/es/dre/uploadDocRecord/queryList", jsonObject, String.class);
                    logger.info("第" + i + "次" + "线程池中线程数目："+executor.getPoolSize()+
                            "，队列中等待执行的任务数目："+ executor.getQueue().size()+
                            "，已执行完任务数目："+executor.getCompletedTaskCount()
//                            + "result:{}", result
                    );
                }
            });
        }
        while (true) {

        }
    }
}
