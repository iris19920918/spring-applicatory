package collection;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WANGDD on 2017/4/1.
 */
public class ListTest {

    private static final Logger logger = LoggerFactory.getLogger(ListTest.class);

    @Test
    public void ListTest() {
        List<Map<String, Object>> mapList = new ArrayList<>();
//        int i = 0;
//        for (Map<String, Object> item : mapList) {
//            logger.info("第" + i + "次");
//            i++;
//        }
//        Map<String, Object> map = mapList.get(0);
        List<String> emptyList = new ArrayList<>();
        boolean isEmpty = true;
        for (int i = 0; i < 10; i++) {
            emptyList.add("");
        }
        for (int i = 0; i < emptyList.size(); i++) {
            if (emptyList.get(i) != null && !"".equals(emptyList.get(i))){
                isEmpty = false;
                break;
            }
        }
        logger.info("isEmpty=" + isEmpty + ";emptyList=" + JSONObject.toJSONString(emptyList));
    }

    @Test
    public void ListToJsonString() {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(i + "");
        }
        logger.info("返回结果：" + JSONObject.toJSONString(list));
    }

    /**
     * List转成String数组
     */
    @Test
    public void ListToArrayTest() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(i + "");
        }
        String[] a = list.toArray(new String[]{});
        logger.info("返回数组"+ a.toString());
    }
}
