package base;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

    static Pattern r = Pattern.compile("\\{(.*?)\\}");

    static String genContent(String tpl, Map<String,Object> params){
        long start = System.currentTimeMillis();
        Matcher m = r.matcher(tpl);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String key = m.group(1);
            Object v = params.get(key);
            if (null == v) {
                throw new RuntimeException("参数:"+key+" 未传");
            }
            m.appendReplacement(sb, v.toString());
        }
        m.appendTail(sb);
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start) + "ms.");
        return sb.toString();
    }


    public static void main(String[] args) {
        
        String tplMsg = "您在口碑订购的{appName}," +
                "后台已经帮您部署完毕，" +
                "查看家长预约详情网址为：{url} 账号为您登记的手机号，" +
                "初始密码为{password}。如有疑问请致电：{tel}";

        Map<String,Object> map = new HashMap<>();
        map.put("appName","闪闪早教");
        map.put("url","http://www.ishanshan.com");
        map.put("password","082338");
        map.put("tel","18668082338");
        String content = genContent(tplMsg,map);
        System.out.println(content);
        
    }

}