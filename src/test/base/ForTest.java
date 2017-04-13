package base;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WANGDD on 2017/4/13.
 */
public class ForTest {

    private static final Logger logger = LoggerFactory.getLogger(ForTest.class);

    /**
     * 测试for循环结束时机
     */
    @Test
    public void testFor() {

        List<Object> list = new ArrayList<>();
        list.add("rwer");
        for (Object o : list) {
            if (1==1) {
                logger.info("i=" + 1);
                for (Object o1 : list) {
                    if (1 == 1) {
                        logger.info("j=" + 1);
                        break;
                    }
                }
                break;
            }
        }
    }
}
