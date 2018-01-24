package date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wangdd on 2018/1/11.
 */
public class DateTest {

    private static final Logger logger = LoggerFactory.getLogger(DateTest.class);

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testDate() {
        String date = "2018-01-01";
        String speDateStr = DateUtils.format(DateUtils.getBeforOrFutureDate(-31), DateUtils.PATTERN_DAY);
        int speDateInt = DateUtils.formatInt(DateUtils.getBeforOrFutureDate(-30), "yyyyMMdd");
//        logger.info("speDateStr:" + speDateStr + ";speDateInt:" + speDateInt);

        int days = DateUtils.getDaysOfMonth(DateUtils.parse(date, DateUtils.PATTERN_DAY));
        logger.info("days:" + days);
    }

    /**
     * 根据当前日期获得所在周的日期区间（周一和周日日期）
     */
    @Test
    public void testGetTimeInterval() {
//        String ret = DateUtils.getTimeInterval(new Date());
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(new Date());
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        logger.info("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 获取一星期的第一天；例如，在美国，这一天是 SUNDAY，而在法国，这一天是 MONDAY。
        logger.info("所在周的第一天：" + cal.getFirstDayOfWeek());
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        logger.info("所在周星期一的日期：" + imptimeBegin);
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        logger.info("所在周星期日的日期：" + imptimeEnd);
    }

    /**
     * 根据当前日期获得所在周的日期区间（周一至周日日期）
     */
    @Test
    public void testGetLocalWeekTime() {
        int interval = 2;
        Date date = DateUtils.getLocalWeekTime(new Date(), interval);
        logger.info("本周周" + interval + "日期是：" + sdf.format(date));
    }

    /**
     * 根据当前日期获得上周的日期区间（周一至周日日期）
     */
    @Test
    public void testGetLastWeekTime() {
        int interval = 3;
        Date date = DateUtils.getLastWeekTime(new Date(), interval);
        logger.info("上周周" + interval + "日期是：" + sdf.format(date));
    }
}
