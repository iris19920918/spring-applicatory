package date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DateUtils;

import java.util.Date;

/**
 * Created by wangdd on 2018/1/30.
 */
public class ParamsUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(ParamsUtilTest.class);

    @Test
    public void testGetParams() {
        Integer speDateInt = DateUtils.formatInt(DateUtils.getBeforOrFutureDate(-63), "yyyyMMdd");
        logger.info("日期：" + speDateInt);

        Date a = new Date();

        Date firstDay = DateUtils.getMonthBegin(a); //指定日期月初
        Date lastDay = DateUtils.getMonthEnd(a); //指定日期月末

        int dateInt = DateUtils.formatInt(a, "yyyyMM"); //日期
        String startTime = DateUtils.format(firstDay, "yyyy-MM-dd 00:00:00"); //月初
        String endTime = DateUtils.format(lastDay, "yyyy-MM-dd 23:59:59"); //月末

        logger.info("startTime：" + startTime + "endTime：" + endTime);
    }


}
