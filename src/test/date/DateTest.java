package date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by WANGDD on 2017/3/25.
 */
public class DateTest {

    private static final Logger logger = LoggerFactory.getLogger(DateTest.class);

    @Test
    public void testDate() {
        String str = "2017-02-02 12:33:33";
        if (str.matches("^\\d{4}-0[1-9]|1[1-2]-0[1-9]|[1-2]\\d|3[0-1] [0-2][0-4]:[0-6]\\d:[0-6]\\d$")) {
            logger.info("符合时间格式！");
        } else {
            logger.warn("不符合时间格式！");
        }
    }
}
