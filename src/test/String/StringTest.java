package String;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by WANGDD on 2017-10-17.
 */
public class StringTest {

    private static final Logger logger = LoggerFactory.getLogger(StringTest.class);

    /**
     * 测试字符串长度
     */
    @Test
    public void strLength() {
//        String a = "[{\"id\":14,\"categoryId\":1},{\"id\":15,\"categoryId\":1},{\"id\":16,\"categoryId\":1},{\"id\":17,\"categoryId\":1},{\"id\":18,\"categoryId\":1},{\"id\":19,\"categoryId\":1},{\"id\":20,\"categoryId\":1},{\"id\":21,\"categoryId\":1},{\"id\":22,\"categoryId\":1},{\"id\":23,\"categoryId\":1},{\"id\":101,\"categoryId\":1},{\"id\":102,\"categoryId\":1},{\"id\":103,\"categoryId\":1},{\"id\":104,\"categoryId\":1},{\"id\":105,\"categoryId\":1},{\"id\":106,\"categoryId\":1},{\"id\":107,\"categoryId\":1},{\"id\":108,\"categoryId\":1},{\"id\":109,\"categoryId\":1},{\"id\":119,\"categoryId\":1},{\"id\":124,\"categoryId\":1},{\"id\":129,\"categoryId\":1},{\"id\":130,\"categoryId\":1},{\"id\":131,\"categoryId\":1},{\"id\":132,\"categoryId\":1},{\"id\":133,\"categoryId\":1},{\"id\":135,\"categoryId\":1},{\"id\":137,\"categoryId\":1},{\"id\":138,\"categoryId\":1},{\"id\":140,\"categoryId\":1},{\"id\":144,\"categoryId\":1},{\"id\":146,\"categoryId\":1},{\"id\":147,\"categoryId\":1},{\"id\":157,\"categoryId\":1},{\"id\":168,\"categoryId\":1},{\"id\":169,\"categoryId\":1},{\"id\":170,\"categoryId\":1},{\"id\":171,\"categoryId\":1},{\"id\":187,\"categoryId\":1},{\"id\":188,\"categoryId\":1},{\"id\":189,\"categoryId\":1},{\"id\":1,\"categoryId\":1},{\"id\":2,\"categoryId\":1},{\"id\":3,\"categoryId\":1},{\"id\":4,\"categoryId\":1},{\"id\":5,\"categoryId\":1},{\"id\":6,\"categoryId\":1},{\"id\":7,\"categoryId\":1},{\"id\":8,\"categoryId\":1},{\"id\":9,\"categoryId\":1},{\"id\":10,\"categoryId\":1},{\"id\":11,\"categoryId\":1},{\"id\":12,\"categoryId\":1},{\"id\":13,\"categoryId\":1},{\"id\":\"201701191705580006\",\"categoryId\":3},{\"id\":\"201701191705580007\",\"categoryId\":3},{\"id\":\"201701191705580008\",\"categoryId\":3},{\"id\":\"201701191705580009\",\"categoryId\":3},{\"id\":\"201701191705580010\",\"categoryId\":3},{\"id\":\"201701191705580011\",\"categoryId\":3},{\"id\":\"201701191705580012\",\"categoryId\":3},{\"id\":\"201701191705580013\",\"categoryId\":3},{\"id\":\"201701191705580014\",\"categoryId\":3},{\"id\":\"201701191705580015\",\"categoryId\":3},{\"id\":\"201612271405583414\",\"categoryId\":3},{\"id\":\"201612271405583416\",\"categoryId\":3},{\"id\":\"201612271405583420\",\"categoryId\":3},{\"id\":\"201701191705580001\",\"categoryId\":3},{\"id\":\"201701191705580002\",\"categoryId\":3},{\"id\":\"201701191705580003\",\"categoryId\":3},{\"id\":\"201701191705580004\",\"categoryId\":3},{\"id\":\"201701191705580005\",\"categoryId\":3}]";
//        String b = "\"a\"";
//        logger.info("a的长度=" + a.length() + "b的长度=" + b.length());
        String signType = "null";
        logger.info("---" + "3,4,5".contains(signType));
    }

    @Test
    public void stuSub() {
        String referer = "https://saas.ishanshan.com/saas-web/login";

        String str = referer.substring(referer.indexOf("//") + 2);
        logger.info("str=" + str);
    }
}
