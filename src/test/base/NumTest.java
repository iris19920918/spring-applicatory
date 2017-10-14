package base;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.NumberValidationUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WANGDD on 2017/3/30.
 */
public class NumTest {

    private static final Logger logger = LoggerFactory.getLogger(NumTest.class);

    private static final DecimalFormat df = new DecimalFormat("######0.00");

    @Test
    public void testPercent() {
        Double a = 3D;
        Double b = 27D;
        Double percent = a/b;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(4);
        numberFormat.setMinimumFractionDigits(4);
        String towDigits = numberFormat.format(percent);
        BigDecimal bigDecimal = new BigDecimal(towDigits);
        Float parseFloat = Float.parseFloat(towDigits);
        Double parseDouble = Double.parseDouble(towDigits);
        logger.info("a/b=" + percent);
        logger.info("保留四位小数：" + towDigits);
        logger.info("bigDecimal表示：" + bigDecimal);
        logger.info("parseDouble表示：" + parseDouble);
        logger.info("用百分数表示parseDouble表示：" + parseDouble*100 + "%");
        logger.info("parseFloat：" + parseFloat);
        logger.info("用百分数表示parseFloat表示：" + parseFloat*100 + "%");
        logger.info("强制保存两位小数用百分数表示：" + df.format(Double.parseDouble(towDigits)*100) + "%");
    }

    /**
     * 保留几位小数
     * @return
     */
    @Test
    public void getFloat() {
        Float floatNumber = 23.4535F;
        int decimalDigits = 0;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(decimalDigits);
        Float floatNum = Float.parseFloat(numberFormat.format(floatNumber));
        logger.info("floatNum=" + floatNum);
    }

    /**
     * 数字校验
     */
    @Test
    public void validNum() {
//        String str = "12353re";
//        if (str.matches("\\d+")) {
//            logger.info("是纯数字！");
//        } else {
//            logger.warn("不是纯数字！");
//        }
        long a = 300002L;
        long b = 300002L;
        System.out.print(a==b);
    }

    @Test
    public void validLong() {
        Long a = 1234L;
        Long b = 1234L;
        String astr = "1234";
        if (a == b) {
            logger.info("a=b true");
        } else {
            logger.info("a=b false");
        }
        if (a.equals(b)){
            logger.info("a.equals(b),true");
        } else {
            logger.info("a.equals(b),false");
        }
        if (a.equals(astr)) {
            logger.info("a.equals(astr),true");
        } else {
            logger.info("a.equals(astr),false");
        }
    }

    @Test
    public void doubleCompute() {
        Double a = 1.59D;
        Double b = 3.42D;
        Double c = a.sum(a, b);
        Double d = 10*a;
        Double e = a*b;
        Double f = b - a;
        double g = 0;
        double h = 6;
        logger.info("a + b = " + c);
        logger.info("b - a = " + (b-a));
        logger.info("10a = " + df.format(d));
        logger.info("a*b = " + e + "保留两位小数：" +df.format(e));
        logger.info("f=" + f);
        logger.info("a/g=" + a/g + ";h/g=" + h/g);
        if (f > 0) {
            logger.info("a>b");
        } else {
            logger.info("a<b");
        }
    }

    /**
     * 生产随机数
     */
    @Test
    public void radomNumTest() {
        String fileName = "1501772287874学员信息模板.xlsx";
        int index = fileName.indexOf(".");
        logger.info("文件后缀：" + fileName.substring(index));
        Double a = (Double)null;
    }

    /**
     * 查询是否是纯数字
     */
    @Test
    public void isNum(){
        String a = "-120498";
        String b = "0";
//        if (a.matches("\\d+")) {
//            logger.info("a是数字！");
//        } else {
//            logger.info("a不是纯数字！");
//        }

        if (a.matches("[0-9]*")) {
            logger.info("a是数字！");
        } else {
            logger.info("a不是纯数字！");
        }

        if (b.matches("[1-9]*")) {
            logger.info("b是大于零的整数");
        } else {
            logger.info("b不是大于零的整数");
        }
    }

    /**
     * 校验正小数
     */
    @Test
    public void isPositiveDecimalTest() {
        String a = "1.1011";
        if (NumberValidationUtils.isPositiveDecimal(a)) {
            logger.info("true");
        } else {
            logger.info("false");
        }
    }

    /**
     * 是否是非负实数
     */
    @Test
    public void isPositiveRealNumberTest() {
        String a = "0.00";
        if (NumberValidationUtils.isPositiveRealNumber(a)) {
            logger.info("true");
        } else {
            logger.info("false");
        }
    }

    /**
     * 取整数
     */
    @Test
    public void getInteger() {
        int month = 0;
        int age = (int) Math.floor(month/12); //向下取整
        logger.info("月龄=" + month);
        logger.info("年龄=" + age);
    }

    /**
     * 测试Double类型
     */
    @Test
    public void testDouble() {
        Double a = 1.02;
        double b = 1.992;

        logger.info("a+b=" + (a+b));
    }
}
