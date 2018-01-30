package date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by wangdd on 2018/1/25.
 */
public class LocalDateTest {

    private static final Logger logger = LoggerFactory.getLogger(LocalDateTest.class);

    public void testLocalDate() {
        LocalDate today = LocalDate.now().minusMonths(1);
        LocalDate firstday = LocalDate.of(today.getYear(),today.getMonth(),1); //月初
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth()); //月末
        logger.info("");
    }
}
