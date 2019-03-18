package zip;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ZipUtil;

/**
 * @Auther: wangdingding5
 * @Date: 2019/3/18 10:59
 * @Description:
 */
@Slf4j
public class ZipUtilTest {

    /**
     * 无需解压直接读取Zip文件和文件内容测试
     */
    @Test
    public void readZipFileTest() throws Exception {
        String zipPath = "D:\\工作\\任务\\提供联调版本\\svn\\20190315144652046\\components\\postgresql96linux64_1.1.1.zip";
        long start = System.currentTimeMillis();
        ZipUtil.readZipFile(zipPath);
        long end = System.currentTimeMillis();
        log.info("读取用时：{}", (end - start));
    }
}
