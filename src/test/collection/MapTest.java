package collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WANGDD on 2017-06-05.
 */
public class MapTest {

    private static final Logger logger = LoggerFactory.getLogger(MapTest.class);

    public final static Map<String, String> PROCESS_TABLE;

    static {
//        PROCESS_TABLE = new HashMap<>();
        PROCESS_TABLE = Collections.unmodifiableMap(new HashMap<>());
        PROCESS_TABLE.put("DRIVE_IDENTIFICATION", "processes/DriverIdentificationApplication.bpmn");
        PROCESS_TABLE.put("DRIVE_DICT", "processes/DriverDict.bpmn");
        PROCESS_TABLE.put("COMPONENT_PUBLISH", "processes/ComponentPublishApplication.bpmn");
    }

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
