package MD5;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.MD5Util;

import java.io.File;
import java.io.IOException;

/**
 * @Auther: wangdingding5
 * @Date: 2019/3/15 13:56
 * @Description:
 */
@Slf4j
public class Md5Test {

    /**
     * 比较两个md5值是否一样
     * @throws IOException
     */
    @Test
    public void md5Test() throws IOException {
        String coreZipPath_1 = "D:\\工作\\任务\\提供联调版本\\svn\\20190315115828\\core.zip";
        String coreZipPath_2 = "D:\\工作\\任务\\提供联调版本\\svn\\core.zip";
        String core_md5_1 = MD5Util.getFileMD5String(new File(coreZipPath_1));
        String core_md5_2 = MD5Util.getFileMD5String(new File(coreZipPath_2));
        log.info("core_md5_1: {},", core_md5_1);
        log.info("core_md5_2: {},", core_md5_2);
        if (core_md5_1.equals(core_md5_2)) {
            log.info("true");
        } else {
            log.info("false");
        }
    }
}
