package util;

import org.dom4j.Element;
import org.dom4j.tree.DefaultAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wangdingding5
 * @Date: 2019/2/18 17:01
 * @Description:
 */
public class Dom4jUtil {

    /**
     * 获取xml某个元素下的一级内容
     * @param element
     * @return
     */
    public static List<Map<String, Object>> getElementNameValue(Element element) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (element == null) {
            return mapList;
        }
        List<Element> elementList = element.elements();
        for (Element element1 : elementList) {
            List<DefaultAttribute> list = element1.attributes();
            for (DefaultAttribute defaultAttribute : list) {
                Map<String, Object> map = new HashMap<>();
                map.put(defaultAttribute.getName(), defaultAttribute.getValue());
                mapList.add(map);
            }
        }
        return mapList;
    }
}
