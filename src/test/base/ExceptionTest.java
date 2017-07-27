package base;

import org.junit.Test;

/**
 * Created by WANGDD on 2017-07-04.
 */
public class ExceptionTest {

    @Test
    public void testException() {
        try {
            int a = 1/0;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
