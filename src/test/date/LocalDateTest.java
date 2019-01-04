package date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by wangdd on 2018/1/25.
 */
public class LocalDateTest {

    private static final Logger logger = LoggerFactory.getLogger(LocalDateTest.class);
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void testLocalDate() {
//        LocalDate today = LocalDate.now().minusMonths(1);
//        LocalDate firstday = LocalDate.of(today.getYear(),today.getMonth(),1); //月初
//        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth()); //月末

        LocalDateTime dateTime = LocalDateTime.parse("2018-10-09 12:02", formatter);
        String formattedDateTime = dateTime.format(formatter); // "1986-04-08 12:30"
        logger.info("" + formattedDateTime);
    }
}
