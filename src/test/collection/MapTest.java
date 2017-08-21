package collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WANGDD on 2017-06-05.
 */
public class MapTest {

    private static final Logger logger = LoggerFactory.getLogger(MapTest.class);

    @Test
    public void mapTest() {
        Map<Object, Object> map = new HashMap<>();
        long a = 12232;
        map.put("uid", a);
        map.put("num", a);
        if(map.containsKey("uid")) {
            logger.info("containsKey=true");
        } else {
            logger.info("containsKey=flase");
        }
        if (map.containsValue(12232L)) {
            logger.info("containsValue=true");
        } else {
            logger.info("containsValue=false");
        }
    }
}
