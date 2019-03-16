package svn;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import util.SvnUtil;

import java.util.List;

/**
 * @Auther: wangdingding5
 * @Date: 2019/3/13 19:59
 * @Description:
 */
@Slf4j
public class SvnTest {

    /**
     * 测试svn检出
     * @throws SVNException
     */
    @Test
    public void svnTest(){
        String url = "https://192.0.0.240/PJ03R2018080803/集成环境/99相关资料/核心服务/tools";
        SvnUtil svn = new SvnUtil("wangdingding5", "20181203wDD");
        List<SVNDirEntry> svnDirEntryList = svn.listFolder(url);
        log.info("返回结果：{}", JSONObject.toJSONString(svnDirEntryList));
    }
}
