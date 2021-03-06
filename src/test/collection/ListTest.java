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

    @Test
    public void stringToListTest() {
        String a = "{\"courseId\":\"340901710685933568\",\"royaltyMethod\":\"1\",\"payMethod\":\"2\",\"gradientOne\":[{\"time\":\"0\",\"money\":\"100\",\"zj_parent_index\":\"0\",\"zj_son_index\":\"0\"}";
        String b = "{\"time\":\"2\",\"money\":\"200\",\"zj_parent_index\":\"0\",\"zj_son_index\":\"1\"}],\"personMoney\":[{\"money\":\"1\"}],\"zj_parent_index\":\"0\",\"gradientTwo\":[{\"time\":\"0\",\"zj_parent_index\":\"0\",\"zj_son_index\":\"0\"}],\"gradientThree\":[{\"time\":\"0\",\"zj_parent_index\":\"0\",\"zj_son_index\":\"0\"}]}";
        List<String> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        logger.info("返回結果：" + JSONObject.toJSONString(list));
    }

    /**
     * 测试for循环1s执行多少次
     */
    @Test
    public void forTest() {
        Long a = Long.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        logger.info("a=" + a + "b=" + b);
        long start = System.currentTimeMillis();
        for (int i = 0; i < a; i++) {
            long end = System.currentTimeMillis();
            if (end - start == 1000) {
                logger.info("i=" + i);
                break;
            }
        }
    }

    /**
     * 测试addAll
     */
    @Test
    public void testAddAll() {
        List<Long> periodFlowIdList = new ArrayList<>();
        List<Long> periodFlowIdListSpe = new ArrayList<>();
        List<Long> periodFlowIdListCom = new ArrayList<>();
        List<Long> periodFlowIdListCur = new ArrayList<>();
        periodFlowIdListSpe.add(1L);
//        periodFlowIdListCom.add(1L);
        packList(periodFlowIdListCom);
        periodFlowIdListCur.add(2L);
        periodFlowIdList.addAll(periodFlowIdListSpe);
        periodFlowIdList.addAll(periodFlowIdListCom);
        periodFlowIdList.addAll(periodFlowIdListCur);
        logger.info("periodFlowIdList=" + JSONObject.toJSONString(periodFlowIdList));
    }

    public void packList(List a) {
        logger.info("a=" + JSONObject.toJSONString(a));
        getList(a);
        logger.info("a=" + JSONObject.toJSONString(a));
    }

    public void getList(List list) {
        list.add(3L);
//        return list;
    }

    public List<Long> getList() {
        List list = new ArrayList<>();
        list.add(3L);
        return list;
    }
}
