package date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DateUtils;

/**
 * Created by wangdd on 2018/1/11.
 */
public class DateTest {

    private static final Logger logger = LoggerFactory.getLogger(DateTest.class);

    @Test
    public void testDate() {
        String date = "2018-01-01";
        String speDateStr = DateUtils.format(DateUtils.getBeforOrFutureDate(-31), DateUtils.PATTERN_DAY);
        int speDateInt = DateUtils.formatInt(DateUtils.getBeforOrFutureDate(-30), "yyyyMMdd");
//        logger.info("speDateStr:" + speDateStr + ";speDateInt:" + speDateInt);

        int days = DateUtils.getDaysOfMonth(DateUtils.parse(date, DateUtils.PATTERN_DAY));
        logger.info("days:" + days);
    }
}
