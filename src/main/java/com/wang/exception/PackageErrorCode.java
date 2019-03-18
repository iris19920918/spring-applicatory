package com.wang.exception;

/**
 * 类功能描述:包模块错误码描述
 *
 * @author guoyangyang
 */
public class PackageErrorCode {

    /**
     * 参数为空异常码
     */
    public static final String PARAMETER_NULL = "0x00131000";

    /**
     * 文件路径异常错误码(文件不存在)
     */
    public static final String FILE_PATH_ERROR = "0x00131700";

    /**
     * SAXReader读取文件的FileInputStream对象异常
     */
    public static final String SAXREADER_FILE_ERROR = "0x00131701";

    /**
     * 文件格式异常码
     */
    public static final String FILE_FORMAT_ERROR = "0x00131702";

    /**
     * 读取组件包中和META-INF同级目录的language文件夹异常
     */
    public static final String READ_RUNTIMELANGUAGE_ERROR = "0x00131703";

    /**
     * 读取组件环境的依赖信息异常
     */
    public static final String READ_COMPONENT_ENVIRONMENT_ERROR = "0x00131704";

    /**
     * 读取组件包的封装时间异常
     */
    public static final String READ_COMPONENT_PACKAGETIME_ERROR = "0x00131705";

    /**
     * 时间转换异常
     */
    public static final String TIME_CONVERT_ERROR = "0x00131706";

    /**
     * 读取installation.xml文件异常
     */
    public static final String READ_INSTALLATIONXML_FILE_ERROR = "0x00131707";

    /**
     * 读取packageinfo.xml文件异常
     */
    public static final String READ_PACKAGEINFOXML_FILE_ERROR = "0x00131708";

    /**
     * packageinfo.xml文件内容异常或者格式不对
     */
    public static final String PACKAGEINFO_FORMAT_ERROR = "0x00131710";

    /**
     * 读取component.xml文件异常
     */
    public static final String READ_COMPONENTXML_FILE_ERROR = "0x00131709";

    /**
     * component.xml文件内容异常或者格式不对
     */
    public static final String COMPONENT_FORMAT_ERROR = "0x00131711";

    /**
     * 读取config.xml文件异常
     */
    public static final String READ_CONFIGXML_ERROR = "0x00131712";

    /**
     * 读取monitor.xml文件异常
     */
    public static final String READ_MONITORXML_ERROR = "0x00131713";

    /**
     * 读取xml文件中节点异常
     */
    public static final String READ_ELEMENT_ERROR = "0x00131714";

    /**
     * 获取包的版本的信息异常
     */
    public static final String GET_COMPONENT_VERSION_ERROR = "0x00131715";

    /**
     * 读取组件包中version.xml文件异常
     */
    public static final String READ_VERSIONXML_ERROR = "0x00131716";

    /**
     * 读取组件包中META-INF下language文件夹中的多语言内容异常
     */
    public static final String READ_COMPONENT_DOWN_LANGUAGE_ERROR = "0x00131717";

    /**
     * 读取组件包中的versioninfo.xml文件中冲突关系内容异常
     */
    public static final String READ_COMPONENT_CONFLICT_ERROR = "0x00131718";

    /**
     * 依据包id查询组件包信息返回值为null异常
     */
    public static final String PACKAGE_NOT_EXIST_ERROR = "0x00131719";

    /**
     * 删除组件包文件异常
     */
    public static final String DELETE_PACKAGE_FILE_ERROR = "0x00131720";

    /**
     * 删除组件包请求操作异常
     */
    public static final String DELETE_PACKAGE_REQUEST_ERROR = "0x00131721";

    /**
     * 上传组件包请求异常
     */
    public static final String UPLOAD_PACKAGE_ERROR = "0x00131722";

    /**
     * 删除组件包请求异常
     */
    public static final String DELETE_PACKAGE_ERROR = "0x00131723";

    /**
     * 查询组件包列表请求异常
     */
    public static final String FIND_PACKAGE_LIST_ERROR = "0x00131724";

    /**
     * 依据查询条件和包类型查询组件包列表
     */
    public static final String FIND_PACKAGE_LIST_BY_CONDITION_PACKAGETYPE_ERROR = "0x00131725";

    /**
     * 下载组件包异常
     */
    public static final String DOWNLOAD_PACKAGE_ERROR = "0x00131726";

    /**
     * agent上传文件异常
     */
    public static final String UPLOAD_FILE_BY_AGENT = "0x00131727";

    /**
     * 依据组件包id查询组件包详情信息失败
     */
    public static final String FIND_COMPONENT_INFO_BY_PACKAGEID_ERROR = "0x00131728";

    /**
     * 依据过滤规则查询组件包列表
     */
    public static final String FIND_COMPONENT_LIST_BY_FILTER_ERROR = "0x00131729";

    /**
     * 发送同步通知异常码
     */
    public static final String SEND_SYNCHRONIZATION_NOTICE_ERROR = "0x00131730";

    /**
     * 发送扩容服务通知异常码
     */
    public static final String SEND_EXPANSION_SERVICE_MOTICE_ERROR = "0x00131731";

    /**
     * 发送资源导入通知异常码
     */
    public static final String SEND_RESOURCE_IMPORT_NOTICE_ERROR = "0x00131732";

    /**
     * 发送卸载服务通知异常码
     */
    public static final String SEND_UNINSTALL_SERVICE_NOTICE_ERROR = "0x00131733";

    /**
     * 发送更新组件通知异常码
     */
    public static final String SEND_UPDATE_COMPONENT_NOTICE_ERROR = "0x00131734";

    /**
     * 发送安装组件通知异常码
     */
    public static final String SEND_INSTALL_COMPONENT_NOTICE_ERROR = "0x00131735";

    /**
     * 发送端口检测通知异常码
     */
    public static final String SEND_PORT_CHECK_NOTICE_ERROR = "0x00131736";

    /**
     * 包类型异常码
     */
    public static final String PACKAGE_TYPE_ERROR = "0x00131737";

    /**
     * 接口访问参数传递异常
     */
    public static final String ACCESS_INTERFACE_PARAMETER_ERROR = "0x00131738";

    /**
     * 上传文件类型异常
     */
    public static final String UPLOAD_FILE_SUFFIX_ERROR = "0x00131739";

    /**
     * 上传的包中类型异常
     */
    public static final String UPLOAD_PACKAGE_TYPE_ERROR = "0x00131740";

    /**
     * 同步agent的上报的组件包中的静态信息异常码
     */
    public static final String SYNC_COMPONET_PACKAGE_METAINF_ERROR = "0x00131741";

    /**
     * 创建一条新的任务进度记录异常码
     */
    public static final String CREATE_PROGRESS_RECORD_ERROR = "0x00131742";

    /**
     * 解压文件异常
     */
    public static final String DECOMPRESSION_FILE_ERROR = "0x00131743";

    /**
     * 压缩文件已存在异常码
     */
    public static final String FILE_ALREADY_EXIST_ERROR = "0x00131744";

    /**
     * 压缩文件异常码
     */
    public static final String COMPRESSION_FILE_ERROR = "0x00131745";

    /**
     * 从Multipart中接收文件异常
     */
    public static final String FROM_MULTIPART_RECEIVE_FILE_ERROR = "0x00131746";

    /**
     * 添加一个组件包异常
     */
    public static final String ADD_COMPONENT_PACKAGE_ERROR = "0x00131747";

    /**
     * 添加一个资源包异常
     */
    public static final String ADD_RESOURCE_PACKAGE_ERROR = "0x00131748";

    /**
     * 批量删除任务进度记录异常
     */
    public static final String DELETE_PROGRESS_RECORD_ERROR = "0x00131749";

    /**
     * 更新任务进度记录异常
     */
    public static final String UPDATE_PROGRESS_RECORD_ERROR = "0x00131750";

    /**
     * xml文件映射到数据库异常
     */
    public static final String XML_MAPPPED_DB_ERROR = "0x00131751";

    /**
     * 检测端口冲突异常
     */
    public static final String CHECK_BE_USED_PORT_ERROR = "0x00131752";

    /**
     * 依据组件实例id查询组件实例信息异常
     */
    public static final String FIND_INSTANCE_COMPONENT_ERROR = "0x00131753";

    /**
     * 获取解压文件路径异常
     */
    public static final String GET_UNZIP_FILEPATH_ERROR = "0x00131754";

    /**
     * 端口检测异常码控制器异常码
     */
    public static final String PORT_CONFLICT_CONTROLLER_ERROR = "0x00131755";

    /**
     * agent上报一个任务进度接口控制器异常码
     */
    public static final String UPLOAD_ONETAKSPROGRESS_CONTROLLER_ERROR = "0x00131756";

    /**
     * agent上报多个任务进度接口控制器异常码
     */
    public static final String UPLOAD_MANYTASKPROGRESS_CONTROLLER_ERROR = "0x00131757";

    /**
     * 安装语言包控制器异常码
     */
    public static final String INSTALL_LANGUAGEPACKAGE_CONTROLLER_ERROR = "0x00131758";

    /**
     * 全量同步控制器异常码
     */
    public static final String SYNCHRONIZATION_ALL_CONTROLLER_ERROR = "0x00131759";

    /**
     * 安装语言包时失败异常码
     */
    public static final String INSTALL_LANGUAGE_PACKAGE_ERROR = "0x00131760";

    /**
     * 安装驱动包时异常码
     */
    public static final String INSTALL_DRIVER_PACAKGE_ERROR = "0x00131761";

    /**
     * 依据taskId查询任务进度记录异常
     */
    public static final String FIND_PROGRESS_RECORD_BY_TASKID_ERROR = "0x00131762";

    /**
     * 执行sql脚本异常
     */
    public static final String EXECUTE_SQL_BAT_ERROR = "0x00131763";

    /**
     * 生成sql脚本异常
     */
    public static final String CREATE_SQL_BAT_ERROR = "0x00131764";

    /**
     * 解析资源包的xml文件异常
     */
    public static final String ANALYSIS_RESOURCE_XML_ERROR = "0x00131765";

    /**
     * 数据库文件映射到java实体类异常码
     */
    public static final String DBFILE_MAPPED_CLASS_ERROR = "0x00131766";

    /**
     * 解析agent传递的db文件异常
     */
    public static final String ANALYSIS_AGENT_DBFILE_ERROR = "0x00131767";

    /**
     * 关闭sqlit数据库连接异常
     */
    public static final String CLOSE_SQLIT_CONNECTION_ERROR = "0x00131768";

    /**
     * 创建新文件异常
     */
    public static final String CREATE_NEW_FILE_ERROR = "0x00131769";

    /**
     * 数据库中的表名和找不到对应的实体类异常
     */
    public static final String JUDEG_TABLENAME_CLASSNAME_ERROR = "0x00131770";

    /**
     * 更新TaskProgressCopyEntity实体类异常
     */
    public static final String UPADTE_TASKPROGRESSCOPYENTITY_ERROR = "0x00131771";

    /**
     * 查找资源多语言异常
     */
    public static final String FIND_RESOURCE_NAME_ERROR = "0x00131772";

    /**
     * 依据资源包版本+资源包标识查询资源包信息异常
     */
    public static final String FIND_RESOURCE_PACKAGE_INFO_ERROR = "0x00131773";

    /**
     * 搜索引擎操作异常
     */
    public static final String SEARCH_HANDLE_ERROR = "0x00131774";

    /**
     * 端口占用错误
     */
    public static final String PORT_USED_ERROR = "0x00131775";

    /**
     * 没有下载权限
     */
    public static final String DOWNLOAD_AUTHORITY_ERROR = "0x00131776";

    /**
     * SVN操作异常
     */
    public static final String SVN_ERROR = "0x00131778";
}
