package collection;

import org.junit.Test;

/**
 * Created by WANGDD on 2017/5/22.
 * 数组相关测试
 */
public class ArrayTest {

    @Test
    public void arrayLengthTest() {
        String str = "";
        String[] strArray = str.split(",");
        for (int i = 0; i < strArray.length; i++) {
            Long a = Long.valueOf(strArray[i]);
            System.out.println(i);
        }
    }
}
