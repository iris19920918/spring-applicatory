package dom4j;

import com.alibaba.fastjson.JSONObject;
import jetbrick.util.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Dom4jUtil;
import util.SalzburgUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;

/**
 * @Auther: wangdingding5
 * @Date: 2019/2/16 14:15
 * @Description:
 */
public class XmlTest {

    private static final Logger logger = LoggerFactory.getLogger(XmlTest.class);


    @Test
    public void getProductVersionTest() throws FileNotFoundException {
        String targetPath = "D:\\工作\\任务\\产品\\1.3产品描述\\";
        File properties = new File(targetPath + "META-INF\\versioninfo.xml");
        InputStream input = null;
        //模型版本
        String modelVersion = "120";
        if(properties.exists()) {
            input = new FileInputStream(properties);
            modelVersion = getVersion(input);
            if(StringUtils.isBlank(modelVersion)) {
                modelVersion = "120";
            }
        }
        logger.info("modelVersion:{}", modelVersion);
    }


    /**
     *
     * @param is
     * @return
     */
    private String getVersion(InputStream is) {
        SAXReader reader = new SAXReader();
        String version = "";
        try {
            Document document = reader.read(is);
            Element packageinfo = document.getRootElement();
            List<Element> els = packageinfo.elements("frameworks");
            for(Element e : els) {
                List<Element> frameworkEls = e.elements("framework");
                for (Element frameworkEl : frameworkEls) {
                    if(frameworkEl.attribute("type").getValue().equals("web")) {
                        String id = frameworkEl.attribute("id").getValue().trim(); //构架标识
                        String versionStr = frameworkEl.attribute("version").getValue().trim(); //构架版本
                        logger.info("getVersion:id={},versionStr={}", id, versionStr);
                        if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(versionStr)) {
                            version = id + "_" + versionStr;
                        }
                        return version;
                    }

                }
            }
        }catch (DocumentException e) {
            logger.error(e.getMessage() + e.getStackTrace());
        }
        return version;
    }

    /**
     * //读取versioninfo.xml文件
     */
    @Test
    public void readProductVersionInfo() throws DocumentException {

        String metaPath = "D:\\工作\\任务\\产品\\1.3产品描述\\" + "META-INF" + File.separator;
        File versioninfoFile = new File(metaPath + "versioninfo.xml");

        SAXReader reader = new SAXReader();
        Document document = reader.read(versioninfoFile);
        Element versioninfo = document.getRootElement();

        Element frameworks = versioninfo.element("frameworks");
        List<Element> frameworkEles = frameworks.elements();
        for (Element element : frameworkEles) {
            String type = element.attributeValue("type");
            String id = element.attributeValue("id");
            String version = element.attributeValue("version");
            logger.info("type:{},id:{},version:{}", type, id, version);

            Element languages = element.element("languages");
            List<Element> languageEles = languages.elements();
            for (Element language : languageEles) {
                String languageId = language.attributeValue("id");
                logger.info("languageId:{}", languageId);
            }

            Element layouts = element.element("layouts");
            List<Map<String, Object>> elems = Dom4jUtil.getElementNameValue(layouts);
            logger.info("layouts-elems:{}", JSONObject.toJSONString(elems));
        }
    }

    /**
     * 测试保存产品安装项
     * @throws Exception
     */
    @Test
    public void saveProductVersionInstallTest() throws Exception {

        String targetPath = "D:\\工作\\任务\\产品\\1.3产品描述\\";
        this.saveProductVersionInstall("", targetPath, "zh_CN", 1);
    }

    /**
     * 保存产品安装项
     * @param jfrogPathAll
     * @param targetPath
     * @param language
     * @param rProductVersionInfo
     * @throws Exception
     */
    public void saveProductVersionInstall(String jfrogPathAll, String targetPath, String language, int rProductVersionInfo) throws Exception {
        BufferedReader bufferedReader = null;
        try {
            String metaPath = targetPath + "META-INF" + File.separator;
            File installFile = new File(metaPath + "installation.xml");
            if (installFile.exists()) {
                SAXReader reader = new SAXReader();
                Document document = reader.read(installFile);
                Element rootElement = document.getRootElement();
                if (rootElement != null) {
                    Element frameworksEle = rootElement.element("frameworks");
                    List<Element> frameworkList = frameworksEle.elements("framework");
                    if (frameworkList != null && frameworkList.size() > 0) {
                        for (Element element : frameworkList) {
                            String c_framework_id = element.attributeValue("id"); //构架标识
                            Integer r_product_object = 0;
//                            List<Map<String, Object>> productObjectList = productObjectQueryDao.queryProObjListDev(rProductVersionInfo, "framework", c_framework_id);
//                            if (productObjectList != null || productObjectList.size() > 0) {
//                                r_product_object = (Integer) productObjectList.get(0).get("id");
//                            } else {
//                                logger.error("保存产品安装项-查询product_object为空，r_product_version_info：{}，c_object_type：{}，c_object_id：{}", rProductVersionInfo, "framework", c_framework_id);
//                            }

                            // 读取translate配置文件
                            String languagePath = metaPath + "language" + File.separator + language + File.separator;
                            Properties properties = new Properties();
                            File translateFile = new File(languagePath + "translate.properties");
                            if (translateFile.exists()) {
                                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(languagePath + "translate.properties"), "UTF-8"));
                                properties.load(bufferedReader);
                            }
                            //读取options
                            Element options = element.element("options");
                            if (options != null) {
                                this.loopInsertInstallOptions(options, properties, r_product_object, metaPath);
                            }
                        }
                    }
                }
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }


    /**
     * 循环插入下载分组项
     * @param rootOptions
     * @param properties
     * @param r_product_object
     * @param metaPath
     * @throws Exception
     */
    private void loopInsertInstallOptions(Element rootOptions, Properties properties, int r_product_object, String metaPath) throws Exception {
        // 创建堆栈对象
        Stack<Map<Element, Integer>> stack = new Stack<>();
        // 如果元素为空，则直接返回
        if (rootOptions == null || rootOptions.elements().isEmpty()) {
            return;
        }
        // 把第一层节点放入堆栈
        for (Element els : (List<Element>) rootOptions.elements()) {
            Map<Element, Integer> mapTop = new HashMap<>();
            mapTop.put(els, null);
            stack.push(mapTop);
            // 第一层菜单没有父菜单ID
        }

        Map<Element, Integer> map = null;
        Element option = null;
        while (stack.size() > 0) {
            // 从堆栈获取元素
            map = stack.pop();
            for (Element element : map.keySet()) {
                option = element;
            }

            List<Map<String, Object>> result = new ArrayList<>();
            if (option != null) {
                if ("option".equals(option.getName())) {
                    List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
                    Map<String, Object> dataMap = new HashMap<String, Object>();
                    dataMap.put("r_product_object", r_product_object);
                    dataMap.put("c_code", SalzburgUtil.getNullString(option.attributeValue("code")));
                    dataMap.put("c_display_name", SalzburgUtil.getNullString(properties.getProperty("option." + option.attributeValue("code") + ".displayName")));
                    dataMap.put("c_description", SalzburgUtil.getNullString(properties.getProperty("option." + option.attributeValue("code") + ".description")));
                    dataMap.put("i_sort", SalzburgUtil.getNullString(option.attributeValue("sort")));

                    List<Element> optionList = option.elements();
                    if (optionList != null && optionList.size() > 0) {
                        List<Map<String, Object>> c_components_list = new ArrayList<>();
                        for (Element optElement : optionList) {
                            if ("component".equals(optElement.getName())) {
                                String c_component_id = optElement.attributeValue("id");
                                if (StringUtils.isNotBlank(c_component_id)) {
                                    Map<String, Object> c_components_map = new HashMap<>();
                                    c_components_map.put("id", c_component_id);
                                    c_components_list.add(c_components_map);
                                }
                            }
                        }
                        if (c_components_list.size() > 0) {
                            dataMap.put("c_components", JSONObject.toJSONString(c_components_list));
                        }
                    }
                    // 保存数据
                    data.add(dataMap);
//                    result = commonDAO.insert("model_dev_db.product_version_install_option", JSONObject.toJSONString(data));
//                    if (result == null || result.size() == 0 || result.get(0).get("id") == null) {
//                        throw new ProgramException(ErrorCode.SAVE_PRODUCT_VERSION_INSTALL_OPTION_ERROR, ErrorCode.getErrorMsg(ErrorCode.SAVE_PRODUCT_VERSION_INSTALL_OPTION_ERROR));
//                    }
                }

                if (!option.elements().isEmpty()) {
                    int i = 0;
                    for (Element els : (List<Element>) option.elements()) {
                        if ("option".equals(els.getName())) {
                            Map<Element, Integer> mapOption = new HashMap<>();
                            mapOption.put(els, i++);
                            stack.push(mapOption);
                        }
                    }
                    continue;
                }
            }
        }
    }
}
