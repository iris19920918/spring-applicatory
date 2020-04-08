package thread.LockTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.atomic.LongAdder;

/**
 * @Auther: wangdingding5
 * @Date: 2020/4/8 16:22
 * @Description:
 */
@Slf4j
public class MultiThreadTest {

    /**
     *  volatile 解决多线程内存不可见问题。对于一写多读，是可以解决变量同步问题，
     * 但是如果多写，同样无法解决线程安全问题。如果是 count ++操作，使用如下类实现：
     * AtomicInteger count =  new AtomicInteger(); count . addAndGet( 1 );  如果是 JDK 8，推
     * 荐使用 LongAdder 对象，比 AtomicLong 性能更好 （ 减少乐观锁的重试次数 ） 。
     */
    @Test
    public void longAdderTest() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(1);
        longAdder.add(3);

        int a = longAdder.intValue();
        log.info("a: 【{}】", a);
    }
}
