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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
