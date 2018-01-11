package valid;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    @Test
    public void switchTest() {
        String s = "test";
        switch (s) {
            case "test":
                System.out.println("test");
                break;
            case "test1":
                System.out.println("test1");
                break;
            default:
                System.out.println("break");
                break;
        }
    }

    public static Object read(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(String.format("%n"));
            }
            return builder.toString();
        }
    }
}
