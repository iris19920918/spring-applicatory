package valid;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by WANGDD on 2017/3/27.
 */
public class ValidTest {

    private static final Logger logger = LoggerFactory.getLogger(ValidTest.class);

    @Test
    public void validTest(){
        String mobile = "13528567853";
        if (mobile.matches("1\\d{10}")) {
            logger.info("符合手机格式！");
        } else {
            logger.warn("不符合手机格式！");
        }

        Object id = 01;
        if (id.equals(0)) {
            logger.info("ture");
        } else {
            logger.info("false");
        }
    }
}
