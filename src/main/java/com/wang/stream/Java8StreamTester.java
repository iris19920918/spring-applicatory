package com.wang.stream;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import javax.swing.text.html.ObjectView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Auther: wangdingding5
 * @Date: 2020/3/7 15:39
 * @Description: java8Stream测试
 */
public class Java8StreamTester {

    @Test
    public void streamTest() {
        // 计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

        System.out.println("使用java8");
        System.out.println("字符串列表: " + strings);

        //空字符串
        long count = strings.stream().filter(s -> s.isEmpty()).count();
        System.out.println("空字符串数量：" + count);

        //字符串长度为3
        count = strings.stream().filter(s -> s.length() == 3).count();
        System.out.println("字符串长度为3的数量为：" + count);

        //过滤掉空字符串后的列表
        List<String> filterList = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println("过滤掉空字符串后的列表为：" + filterList);

        //使用英文逗号连接
        String mergedStr = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(","));
        System.out.println("使用英文逗号连接后字符串为：" + mergedStr);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        System.out.println("数字列表: " + numbers);

        //平方
        List<Integer> squaresList = numbers.stream().map(integer -> integer * integer).collect(Collectors.toList());
        System.out.println("平方数列表: " + squaresList);
        squaresList = numbers.stream().map(integer -> integer * integer).distinct().collect(Collectors.toList());
        System.out.println("平方数去重后列表: " + squaresList);

        //统计
        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
        IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");

        // 输出10个随机数
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count);

        //将List<Map>转为Set
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        mapList.add(map);
        Set<Object> set = mapList.stream().map(m -> m.get("name")).collect(Collectors.toSet());
        System.out.println("将List<Map>转为Set，返回结果：" + JSONObject.toJSONString(set));
    }
}
