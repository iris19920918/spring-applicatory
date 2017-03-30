package base;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by WANGDD on 2017/3/30.
 */
public class NumTest {

    private static final Logger logger = LoggerFactory.getLogger(NumTest.class);

    @Test
    public void testPercent() {
        Double a = 139D;
        Double b = 145D;
        Double percent = a/b;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        String towDigits = numberFormat.format(percent);
        BigDecimal bigDecimal = new BigDecimal(towDigits);
        logger.info("a/b=" + percent);
        logger.info("保留两位小数：" + towDigits);
        logger.info("bigDecimal表示：" + bigDecimal);
        logger.info("用百分数表示：" + Double.parseDouble(towDigits)*100 + "%");
    }
}
