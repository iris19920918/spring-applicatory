package base;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by WANGDD on 2017/3/30.
 */
public class NumTest {

    private static final Logger logger = LoggerFactory.getLogger(NumTest.class);

    private static final DecimalFormat df = new DecimalFormat("######0.00");

    @Test
    public void testPercent() {
        Double a = 141D;
        Double b = 141D;
        Double percent = a/b;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(4);
        numberFormat.setMinimumFractionDigits(4);
        String towDigits = numberFormat.format(percent);
        BigDecimal bigDecimal = new BigDecimal(towDigits);
        logger.info("a/b=" + percent);
        logger.info("保留四位小数：" + towDigits);
        logger.info("bigDecimal表示：" + bigDecimal);
        logger.info("用百分数表示：" + Double.parseDouble(towDigits)*100 + "%");
        logger.info("强制保存两位小数用百分数表示：" + df.format(Double.parseDouble(towDigits)*100) + "%");
    }
}
