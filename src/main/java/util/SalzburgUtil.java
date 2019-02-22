package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SalzburgUtil {
	
	private static final String ENCODE_UTF8 = "UTF-8";
	private static final char UNDERLINE = '_';

	/**
	 * 转换sql模板成sql执行语句
	 * @param data			数据源
	 * @param filePath		模板文件路径（喊文件名）
	 * @param encode		编码格式
	 * @return
	 */
	public static String mergeSqlTemplate(String jsonString, String filePath, String encode) throws Exception {
		if(StringUtils.isBlank(jsonString)) {
			return null;
		}
		
		//构建模板引擎
		VelocityEngine velocityEngine = new VelocityEngine();
		//设置参数
		velocityEngine.setProperty(Velocity.ENCODING_DEFAULT, ENCODE_UTF8);
		velocityEngine.setProperty(Velocity.INPUT_ENCODING, ENCODE_UTF8);
		velocityEngine.setProperty(Velocity.OUTPUT_ENCODING, ENCODE_UTF8);
		//获取上下文
		VelocityContext context = new VelocityContext();
		
		//使用反射获取对象的属性名和属性值
		JSONObject paramsData = JSONObject.parseObject(jsonString);
		Set<Entry<String, Object>> set = paramsData.entrySet();
		Iterator<Entry<String, Object>> it = set.iterator();
		while(it.hasNext()) {
			Entry<String, Object> e = it.next();
			context.put(e.getKey(), e.getValue());
		}
		
		StringWriter sw = new StringWriter();
		velocityEngine.mergeTemplate(filePath, encode, context, sw);
		
		return sw.toString();
	}
	
	/**
	 * 获取对象的属性名和属性值
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> reflectObject(Object obj) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(obj == null) {
			return result;
		}
		
		//获取对象所有的属性
		Field[] field = obj.getClass().getDeclaredFields();
		//遍历属性
		for(int i = 0; i < field.length; i++) {
			Field f = field[i];
			f.setAccessible(true);
            result.put(f.getName(), f.get(obj));
		}
		
		return result;
	}
	
	/**
	 * 根据json数据，生成对应table的插入语句
	 * @param tableName		表名
	 * @param jsonData		json数据
	 * @return
	 */
	public static String formatInsertSQL(String tableName, String jsonData) throws Exception {
		StringBuilder sql1 = new StringBuilder();
		sql1.append("insert into ").append(tableName).append("(");
		
		StringBuilder sql2 = new StringBuilder();
		sql2.append(" values (");
		
		JSONArray jsonArray= JSONArray.parseArray(jsonData);
		if(jsonArray == null) {
			return null;
		}
		for(int i = 0; i < jsonArray.size();) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			//遍历json对象
			for (Entry<String, Object> entry : jsonObj.entrySet()) {
				if(entry.getKey().equalsIgnoreCase("id")) {
					continue;
				}
				if(entry.getValue() != null) {
					sql1.append(entry.getKey()).append(",");
					sql2.append("\"").append(String.valueOf(entry.getValue()).replaceAll("\"", "&quot;").replaceAll("\\\\", "\\\\\\\\")).append("\"").append(",");
				}else {
					sql1.append(entry.getKey()).append(",");
					sql2.append("").append(entry.getValue()).append("").append(",");
				}
	        }
			//只处理一条记录
			break;
		}

		sql1.deleteCharAt(sql1.lastIndexOf(",")).append(")");
		sql2.deleteCharAt(sql2.lastIndexOf(",")).append(")");
		sql1.append(sql2.toString());
		System.out.println(sql1.toString());
		return sql1.toString();
	}

	/**
	 * 根据json数据，生成对应table的更新语句
	 * @param tableName
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public static String formatUpdateSQL(String tableName, String jsonData) throws Exception {
		StringBuilder sql1 = new StringBuilder();
		sql1.append("update ").append(tableName).append(" set ");

		StringBuilder sql2 = new StringBuilder();
		sql2.append(" where ");
		boolean isId = false;

		JSONArray jsonArray= JSONArray.parseArray(jsonData);
		if(jsonArray == null) {
			return null;
		}
		for(int i = 0; i < jsonArray.size();) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			//遍历json对象
			for (Entry<String, Object> entry : jsonObj.entrySet()) {
				if(entry.getKey().equalsIgnoreCase("id")) {
					sql2.append(" id = ").append(entry.getValue());
					isId = true;
					continue;
				}
				if(entry.getValue() != null) {
					sql1.append(entry.getKey()).append("=").append("\"").append(String.valueOf(entry.getValue()).replaceAll("\"", "&quot;").replaceAll("\\\\", "\\\\\\\\")).append("\"").append(",");
				}else {
					sql1.append(entry.getKey()).append("=").append("").append(entry.getValue()).append("").append(",");
				}
	        }
			//只处理一条记录
			break;
		}
		if(!isId) {
			return null;
		}
	    log.info("sql1:"+sql1);
	    log.info("sql2:"+sql2);
	    System.out.println("sql1:" + sql1 + ";sql2:" + sql2);
		sql1.deleteCharAt(sql1.lastIndexOf(",")).append(sql2.toString());
		System.out.println(sql1.toString());
		return sql1.toString();
	}

	/**
	 * 根据json数据，生成对应table的删除语句
	 * @param tableName
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public static String formatDeleteSQL(String tableName, String jsonData) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ").append(tableName).append(" where ");
		boolean isId = false;

		JSONArray jsonArray= JSONArray.parseArray(jsonData);
		if(jsonArray == null) {
			return null;
		}
		for(int i = 0; i < jsonArray.size();) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			//遍历json对象
			for (Entry<String, Object> entry : jsonObj.entrySet()) {
				if(entry.getKey().equalsIgnoreCase("id")) {
					sql.append(" id = ").append(entry.getValue());
					isId = true;
					continue;
				}
	        }
			//只处理一条记录
			break;
		}
		if(!isId) {
			return null;
		}
		log.info("delete:"+ sql);
		return sql.toString();
	}

	/**
	 * 根据json数据，生成对应的调用存储过程的语句
	 * @param procName			存储过程名称
	 * @param jsonData			存储过程参数的json数据
	 * @return
	 * @throws Exception
	 */
	public static String formatProcSQL(String procName, String jsonData) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("{call ").append(procName).append("(");
		//转成有序的集合列表
		List<LinkedHashMap<String, Object>> results = JSON.parseObject(jsonData, new TypeReference<List<LinkedHashMap<String, Object>>>(){});
		//遍历列表数据
		for (int i = 0; i < results.size();) {
			//转换成有序的集合对象
			LinkedHashMap<String, Object> result = JSON.parseObject(JSON.toJSONString(results.get(i)), LinkedHashMap.class);
			//遍历json对象
			for (Entry<String, Object> entry : result.entrySet()) {
				sql.append("\"").append(String.valueOf(entry.getValue()).replaceAll("\"", "&quot;").replaceAll("\\\\", "\\\\\\\\")).append("\"").append(",");
	        }
			//只处理一条记录
			break;
		}
		sql.deleteCharAt(sql.lastIndexOf(",")).append(")}");
		return sql.toString();
	}

	/**
     * 驼峰格式字符串转换为下划线格式字符串
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串2
     *
     * @param param
     * @return
     */
    public static String underlineToCamel2(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("_").matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 获取""字符串
     * @param str
     * @return
     */
    public static String getNullString(String str) {
    	if(StringUtils.isBlank(str)) {
    		return StringUtils.EMPTY;
    	}
    	return str;
    }

    /**
     * 转换特殊字符
     * @param sourceStr		被替换的字符串
     * @param replaceChar	被替换的特殊字符
     * @param newChar		替换后的字符
     * @return
     */
    public static String exchangeSpecicalChar(String sourceStr, String replaceChar, String newChar) {
    	if(StringUtils.isNotBlank(sourceStr) && sourceStr.contains(replaceChar)) {
    		return sourceStr.replaceAll(replaceChar, newChar);
    	}
    	return sourceStr;
    }

    /**
     * 转为sql(insert)
     * @param tableName
     * @param jsonArray
     * @return
     * @throws Exception
     */
    public static List<String> formatInsertSQL(List<String> tableNames, List<Map<String, Object>> jsonArray) throws Exception {

		if(jsonArray == null) {
			return null;
		}
		//表名不可为空
		String tableName = "";
		if(tableNames == null || tableNames.isEmpty()) {
			return null;
		}
		if(tableNames.size() == 1){
			tableName = tableNames.get(0);
		}
		List<String> sqls = new ArrayList<>();
		for(int i = 0; i < jsonArray.size(); i++) {
			Map<String, Object> jsonObj = jsonArray.get(i);
			if(jsonObj == null || jsonObj.isEmpty()) {
				continue;
			}
			StringBuilder sql1 = new StringBuilder();
			if(tableNames.size() > 1) {
				tableName = tableNames.get(i);
			}
			sql1.append("insert into ").append(tableName).append("(");

			StringBuilder sql2 = new StringBuilder();
			sql2.append(" values (");
			//遍历json对象
			for (Entry<String, Object> entry : jsonObj.entrySet()) {
				if(entry.getKey().equalsIgnoreCase("id")) {
					continue;
				}
				if(entry.getValue() != null) {
					sql1.append(entry.getKey()).append(",");
					sql2.append("\"").append(String.valueOf(entry.getValue()).replaceAll("\"", "&quot;").replaceAll("\\\\", "\\\\\\\\")).append("\"").append(",");
				}else {
					sql1.append(entry.getKey()).append(",");
					sql2.append("").append(entry.getValue()).append("").append(",");
				}
	        }
			if(sqls.toString().equals("insert into "+ tableName + "(")) {
				continue;
			}
			sql1.deleteCharAt(sql1.lastIndexOf(",")).append(")");
			sql2.deleteCharAt(sql2.lastIndexOf(",")).append(")");
			sql1.append(sql2.toString());
			System.out.println(sql1.toString());
			sqls.add(sql1.toString());
		}

		return sqls;
	}

    /**
	 * 根据json数据，生成对应table的更新语句(No Where)
	 * @param tableName
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public static String formatUpdateSQLNoWhere(String tableName, List<Map<String, Object>> jsonData) throws Exception {
		StringBuilder sql1 = new StringBuilder();
		sql1.append("update ").append(tableName).append(" set ");

		boolean isId = false;
		//转为List<Map>
		if(jsonData != null) {
			for(int i = 0; i < jsonData.size();) {
				Map<String, Object> jsonObj = jsonData.get(i);
				//遍历json对象
				for (Entry<String, Object> entry : jsonObj.entrySet()) {
					if(entry.getValue() != null){
						if(entry.getValue().toString().contains("\"")) {
							sql1.append(entry.getKey()).append("=").append("\"").append(String.valueOf(entry.getValue()).replaceAll("\"", "&quot;").replaceAll("\\\\", "\\\\\\\\")).append("\"").append(",");									
						}else {
							sql1.append(entry.getKey()).append("=").append("\"").append(entry.getValue()).append("\"").append(",");																
						}
					}else {
						sql1.append(entry.getKey()).append("=").append("").append(entry.getValue()).append("").append(",");									
					}
				}
				//只处理一条记录
				break;
			}			
			sql1.deleteCharAt(sql1.lastIndexOf(","));			
		}
		return sql1.toString();
	}
}
