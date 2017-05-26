package base;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by WANGDD on 2017/3/25.
 */
public class DateTest {

    private static final Logger logger = LoggerFactory.getLogger(DateTest.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testDate() {
//        String str = "2017-02-02 20:59:33";
        String str = "1991-10-01";
//        String regex = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])\\s([0-1]\\d|2[0-3]):[0-5]\\d:[0-5]\\d";
        String regex = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])";
        if (str.matches(regex)) {
            logger.info("符合时间格式！");
        } else {
            logger.warn("不符合时间格式！");
        }
    }

    /**
     * 获取昨天时间
     */
    @Test
    public void testCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        logger.info("CST: " + yesterday.toString() + "yesterday:" + sdf.format(yesterday));
    }

    /**
     * 获取某个月份的天数
     */
    @Test
    public void getDaysOfMonth() throws ParseException {
        String dateStr = "2017-02-5";
        Calendar calendar = Calendar.getInstance();
        Date date = sdf.parse(dateStr);
        calendar.setTime(date);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        logger.warn(dateStr + "这个月份总共" + days + "天！");
    }

    /**
     * 获取当前天、月、年、星期
     */
    @Test
    public void getCurrentDateInfo() throws ParseException {
        String dateStr = "2017-04-01";
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        int dow = calendar.get(Calendar.DAY_OF_WEEK);
        int dom = calendar.get(Calendar.DAY_OF_MONTH);
        int doy = calendar.get(Calendar.DAY_OF_YEAR);
        logger.info("Current Date:" + calendar.getTime());
        logger.info("Day:" + day);
        logger.info("month:" + month);
        logger.info("year:" + year);
        logger.info("Day of Week:" + dow);
        logger.info("Day of Month:" + dom);
        logger.info("Day of Year:" + doy);
    }

    /**
     * 获取N天前的日期
     */
    @Test
    public void getBeforDate(){
        int n = 0;
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.add(Calendar.DATE, n);
        String date = sdf.format(calendar.getTime());
        String dates = sdfs.format(calendar.getTime());
        logger.info("当前时间：" + sdf.format(new Date()) + ";" + n + "天前的日期为：" + date);
        logger.info(n + "天前的带时分秒日期为：" + dates);
    }
}
