package json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by WANGDD on 2017-06-03.
 */
public class JSONArrayTest {

    private static final Logger logger = LoggerFactory.getLogger(JSONArrayTest.class);

    @Test
    public void jsonArrayTest() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(123L);
        jsonArray.add(124l);
        logger.info("json数组：" + jsonArray.toJSONString());
    }

    @Test
    public void jsonToStringTest() {
        String a = null;
        logger.info(JSONObject.toJSONString(a));
    }
}
