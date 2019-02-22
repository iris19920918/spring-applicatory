package http;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Auther: wangdingding5
 * @Date: 2019/2/22 14:22
 * @Description:
 */
public class URITest {

    private static final Logger logger = LoggerFactory.getLogger(URITest.class);

    /**
     * 测试URL编码
     */
    @Test
    public void URIPathEncodeTest() throws UnsupportedEncodingException {
        String url = "http://af.hikvision.com.cn/artifactory/usta/file/product/iSecure Center Pro (DS)/V1.0.0_20180330 JKN20180405_118/20190222142007/iSecure Center Pro (DS)_V1.0.0_20180330 JKN20180405_118";
        url = URLEncoder.encode(url, "UTF-8");
        logger.info("URLEncoder.encode：{}", url);
    }

    /**
     * 测试URL解码
     */
    @Test
    public void URIPathDecodeTest() throws UnsupportedEncodingException {
        String url = "http%3A%2F%2Faf.hikvision.com.cn%2Fartifactory%2Fusta%2Ffile%2Fproduct%2FiSecure+Center+Pro+%28DS%29%2FV1.0.0_20180330+JKN20180405_118%2F20190222143127%2FiSecure+Center+Pro+%28DS%29_V1.0.0_20180330+JKN20180405_118%2FMETA-INF%2Ficon%2Fshortcut%2Fs06.ico";
        url = URLDecoder.decode(url, "UTF-8");
        logger.info("URLDecoder.decode：{}", url);
    }
}
