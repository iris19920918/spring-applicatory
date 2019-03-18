/**
 * @ProjectName: CMS基线平台软件
 * @Copyright: 2010 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017年5月19日 下午3:47:41
 * @Description: 本内容仅限于杭州海康威视数字技术系统公司内部使用，禁止转发.
 */
package com.wang.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 错误吗常量,以30开头,位数为6位
 * </p>
 *
 * @author wujian3 2017年5月19日 下午3:47:41
 * @version V1.0
 */
public class ErrorCode {

		//程序错误信息
		private static final Map<String, String> ERROR_MSG_MAP = new HashMap<String, String>();
		
		//程序内部错误
	    public static final String SELF_PROGRAMER = "0x08800107";
	    //表单提交错误
	    public static final String FORM_ERROR = "0x0880121"; 
	    //正常
	    public static final String CORRECT_CODE = "0";
	    //未知错误
	    public static final String UN_KNOWN_ERROR = "0x00137001"; 
	    //请求超时
	    public static final String REST_CONN_TIMEOUT = "0x0013700A"; 
	    //读取超时
	    public static final String REST_READ_TIMEOUT = "0x0013700B"; 
	    //对方服务器未启动或host错误
	    public static final String HTTP_HOST_CONNECT_TIMEOUT = "0x0013700F";
	    //上传图片出错
	    public static final String ERROR_UPLOAD_FILE_BY_LOCAL = "0x00137029";
	    
	    //解压组件包出错
	    public static final String UNZIP_ERROR = "0x01000004";
	    //获取组件的component.xml文件出错，文件不存在
	    public static final String COMPONENT_XML_FILE_NOT_EXIST = "0x01000005";
	    
	    //获取组件标识出错，组件标识为空
	    public static final String COMPONENT_ID_NOT_EXIST = "0x01000006";
	    //开发库中不存在组件(标识为[componentId])信息
	    public static final String DEV_COMPONENT_INFO_NOT_EXIST = "0x01000007";
	    //拷贝开发库的组件(标识为[componentId])信息出错
	    public static final String COPY_COMPONENT_FROM_DEV_TO_CONTROLLED_ERROR = "0x01000008";
	    
	    //获取组件的versioninfo.xml文件出错，文件不存在
	    public static final String VERSIONINFO_XML_FILE_NOT_EXIST = "0x01000009";
	    //获取组件的版本号出错，请检查导入组件包中的versioninfo.xml配置文件是否正确
	    public static final String VERSION_NO_NOT_EXIST = "0x0100000A";
	    //保存组件版本信息出错
	    public static final String SAVE_COMPONENT_VERSION_INFO_ERROR = "0x0100000B";
	    public static final String REALEASE_PRODUCT_VERSION = "0x01000018";
	    public static final String COPY_NO_FILE = "0x01000019";
	    public static final String FILE_ELEMENT_NULL = "0x0100001D";
	    public static final String FILE_NO_ROOT_ELEMENT = "0x0100001E";
	    public static final String FILE_NO_EXIST = "0x0100001F";
	    public static final String jfrog_FAILURE = "0x01000031";
	    public static final String INIT_TABLE_MAPPING_ERROR = "0x01000035";
	    public static final String FILE_PATH_ERROR = "0x01000036";
	    public static final String DECOMPRESSION_FILE_ERROR = "0x01000037";
	    public static final String COMPRESSION_FILE_ERROR = "0x01000038";
	    public static final String jfrog_FILE_FAILURE = "0x01000041";
	    
	    //获取构架的framework.xml文件出错，文件不存在
	    public static final String FRAMEWORK_XML_FILE_NOT_EXIST = "0x0100003A";
	    //获取构架标识出错，构架标识为空
	    public static final String FRAMEWORK_ID_NOT_EXIST = "0x0100003B";
	    //开发库中不存在构架(标识为[frameworkId])信息
	    public static final String DEV_FRAMEWORK_INFO_NOT_EXIST = "0x0100003C";
	    //拷贝开发库的构架(标识为[frameworkId])信息出错
	    public static final String COPY_FRAMEWORK_FROM_DEV_TO_CONTROLLED_ERROR = "0x0100003D";
	    //获取构架的versioninfo.xml文件出错，文件不存在
	    public static final String FRAMEWORK_VERSIONINFO_XML_FILE_NOT_EXIST = "0x0100003E";
	    //获取构架的版本号出错，请检查导入构架包中的versioninfo.xml配置文件是否正确
	    public static final String FRAMEWORK_VERSION_NO_NOT_EXIST = "0x0100003F";
	    //保存构架版本信息出错
	    public static final String SAVE_FRAMEWORK_VERSION_INFO_ERROR = "0x01000040";

	    //核心服务
		//packageinfo.xml文件不合法
		public static final String CORE_SERVICE_PACKAGEINFO_ILLEGAL = "0x01100001";
		//packageinfo.xml文件下不存在package元素
		public static final String CORE_SERVICE_PACKAGEINFO_PACKAGE_NOT_EXIST = "0x01100002";
		//core目录不合法
		public static final String CORE_SERVICE_CORE_DIRECTORY_ILLEGAL = "0x01100003";
		//core目录下不存在opsMgrCenter
		public static final String CORE_SERVICE_OPS_MGR_CENTER_NOT_EXIST = "0x01100004";
		//保存result_output到数据库失败
		public static final String CORE_SERVICE_RESULT_OUTPUT_SAVE_FAILED = "0x01100005";
		//tools目录不存在
		public static final String CORE_SERVICE_TOOLS_NOT_EXIST  = "0x01100006";
		//光盘安装工具不存在
		public static final String CORE_SERVICE_DISC_SETUP_NOT_EXIST  = "0x01100007";
		//保存core_tools到数据库失败
		public static final String CORE_SERVICE_RESULT_CORE_TOOLS_SAVE_FAILED = "0x01100008";
		//保存result_related_svn_info到数据库失败
		public static final String CORE_SERVICE_RESULT_RELATED_SVN_INFO_SAVE_FAILED = "0x01100009";
		//标识存在，版本存在，数据异常-存在多条md5一样的数据
		public static final String CORE_SERVICE_RESULT_OUTPUT_MD5_NOT_UNIQUE = "0x01100010";
		//核心服务光盘安装工具不唯一
		public static final String CORE_SERVICE_CORE_TOOLS_NOT_UNIQUE = "0x01100011";
		//components目录不合法
		public static final String CORE_SERVICE_COMPONENTS_DIRECTORY_ILLEGAL = "0x01100012";
		//components目录下缺少预置组件
		public static final String CORE_SERVICE_COMPONENTS_DIRECTORY_LACK = "0x01100013";
		//标识存在，版本存在，数据异常-存在多条unique_no一样的数据
		public static final String CORE_SERVICE_RESULT_OUTPUT_UNIQUE_NO_NOT_UNIQUE = "0x01100014";

	    static {
	    	ERROR_MSG_MAP.put(SELF_PROGRAMER, "程序内部错误");
	    	ERROR_MSG_MAP.put(FORM_ERROR, "表单提交错误");
	    	ERROR_MSG_MAP.put(UN_KNOWN_ERROR, "未知错误");
	    	ERROR_MSG_MAP.put(REST_CONN_TIMEOUT, "请求超时");
	    	ERROR_MSG_MAP.put(REST_READ_TIMEOUT, "读取超时");
	    	ERROR_MSG_MAP.put(HTTP_HOST_CONNECT_TIMEOUT, "对方服务器未启动或host错误");
	    	ERROR_MSG_MAP.put(ERROR_UPLOAD_FILE_BY_LOCAL, "上传图片出错");
	    	ERROR_MSG_MAP.put(UNZIP_ERROR, "解压组件包出错");
	    	
	    	ERROR_MSG_MAP.put(COMPONENT_XML_FILE_NOT_EXIST, "获取组件包的component.xml文件出错，文件不存在");
	    	ERROR_MSG_MAP.put(COMPONENT_ID_NOT_EXIST, "获取组件标识出错，组件标识为空");
	    	ERROR_MSG_MAP.put(DEV_COMPONENT_INFO_NOT_EXIST, "开发库中不存在组件(标识为[componentId])信息");
	    	ERROR_MSG_MAP.put(COPY_COMPONENT_FROM_DEV_TO_CONTROLLED_ERROR, "拷贝开发库的组件(标识为[componentId])信息出错");
	    	ERROR_MSG_MAP.put(VERSIONINFO_XML_FILE_NOT_EXIST, "获取组件的versioninfo.xml文件出错，文件不存在");
	    	ERROR_MSG_MAP.put(VERSION_NO_NOT_EXIST, "获取组件的版本号出错，请检查导入组件包中的versioninfo.xml配置文件是否正确");
	    	ERROR_MSG_MAP.put(SAVE_COMPONENT_VERSION_INFO_ERROR, "保存组件版本信息出错");
	    	
	    	ERROR_MSG_MAP.put(COPY_NO_FILE, "[url]路径下没有文件");
	    	ERROR_MSG_MAP.put(FILE_ELEMENT_NULL, "[file]中没有[element]元素");
	    	ERROR_MSG_MAP.put(FILE_NO_ROOT_ELEMENT, "[file]中根元素为空");
	    	ERROR_MSG_MAP.put(FILE_NO_EXIST, "[file]文件不存在");
	    	ERROR_MSG_MAP.put(jfrog_FAILURE, "[action]jfrog文件出错");
	    	ERROR_MSG_MAP.put(jfrog_FILE_FAILURE, "[path]出错");
	    	ERROR_MSG_MAP.put(INIT_TABLE_MAPPING_ERROR, "初始化table-column-relevance-xml配置文件出错");
	    	ERROR_MSG_MAP.put(FILE_PATH_ERROR, "文件路径错误");
	    	
	    	//构架
	    	ERROR_MSG_MAP.put(FRAMEWORK_XML_FILE_NOT_EXIST, "获取构架包的framwork.xml文件出错，文件不存在");
	    	ERROR_MSG_MAP.put(FRAMEWORK_ID_NOT_EXIST, "获取构架标识出错，构架标识为空");
	    	ERROR_MSG_MAP.put(DEV_FRAMEWORK_INFO_NOT_EXIST, "开发库中不存在构架(标识为[frameworkId])信息");
	    	ERROR_MSG_MAP.put(COPY_FRAMEWORK_FROM_DEV_TO_CONTROLLED_ERROR, "拷贝开发库的构架(标识为[frameworkId])信息出错");
	    	ERROR_MSG_MAP.put(FRAMEWORK_VERSIONINFO_XML_FILE_NOT_EXIST, "获取构架的versioninfo.xml文件出错，文件不存在");
	    	ERROR_MSG_MAP.put(FRAMEWORK_VERSION_NO_NOT_EXIST, "获取构架的版本号出错，请检查导入构架包中的versioninfo.xml配置文件是否正确");
	    	ERROR_MSG_MAP.put(SAVE_FRAMEWORK_VERSION_INFO_ERROR, "保存构架版本信息出错");

	    	//核心服务
			ERROR_MSG_MAP.put(CORE_SERVICE_PACKAGEINFO_ILLEGAL, "packageinfo.xml文件不合法");
			ERROR_MSG_MAP.put(CORE_SERVICE_PACKAGEINFO_PACKAGE_NOT_EXIST, "packageinfo.xml文件下不存在package元素");
			ERROR_MSG_MAP.put(CORE_SERVICE_CORE_DIRECTORY_ILLEGAL, "core目录不合法");
			ERROR_MSG_MAP.put(CORE_SERVICE_OPS_MGR_CENTER_NOT_EXIST, "core目录下不存在opsMgrCenter");
			ERROR_MSG_MAP.put(CORE_SERVICE_RESULT_OUTPUT_SAVE_FAILED, "保存result_output到数据库失败");
			ERROR_MSG_MAP.put(CORE_SERVICE_TOOLS_NOT_EXIST, "tools目录不存在");
			ERROR_MSG_MAP.put(CORE_SERVICE_DISC_SETUP_NOT_EXIST, "光盘安装工具不存在");
			ERROR_MSG_MAP.put(CORE_SERVICE_RESULT_CORE_TOOLS_SAVE_FAILED, "保存core_tools到数据库失败");
			ERROR_MSG_MAP.put(CORE_SERVICE_RESULT_RELATED_SVN_INFO_SAVE_FAILED, "保存result_related_svn_info到数据库失败");
			ERROR_MSG_MAP.put(CORE_SERVICE_RESULT_OUTPUT_MD5_NOT_UNIQUE, "标识存在，版本存在，数据异常-存在多条md5一样的数据");
			ERROR_MSG_MAP.put(CORE_SERVICE_CORE_TOOLS_NOT_UNIQUE, "核心服务光盘安装工具不唯一");
			ERROR_MSG_MAP.put(CORE_SERVICE_COMPONENTS_DIRECTORY_ILLEGAL, "components目录不合法");
			ERROR_MSG_MAP.put(CORE_SERVICE_COMPONENTS_DIRECTORY_LACK, "components目录下缺少预置组件");
			ERROR_MSG_MAP.put(CORE_SERVICE_RESULT_OUTPUT_UNIQUE_NO_NOT_UNIQUE, "标识存在，版本存在，数据异常-存在多条unique_no一样的数据");
	    }
	    
	    /**
	     * 获取错误信息
	     * @param errorCode
	     * @return
	     */
	    public static String getErrorMsg(String errorCode) {
	    	return ERROR_MSG_MAP.get(errorCode);
	    }
    
}
