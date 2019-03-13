package file;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @Auther: wangdingding5
 * @Date: 2019/2/28 15:38
 * @Description:
 */
public class FileTest {

    private static final Logger logger = LoggerFactory.getLogger(FileTest.class);

    @Test
    public void fileTest() {
        String filePath = "D:\\工作\\任务\\产品\\导入目录\\Infovision_iEstate_DS_V1.0.0_20190330_Win_x64_zh-CN\\Source\\framework\\isecure_1.2.1.zip";
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            logger.info("filePath: {}", file.getPath());
            logger.info("absolutePath: {}", file.getAbsolutePath());
            logger.info("fileName: {}", file.getName());
            logger.info("fileName: {}", file.getParent());
        }
    }
}
