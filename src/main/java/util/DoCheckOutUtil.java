package util;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.io.File;
import java.util.Properties;

/**
 * @Auther: wangdingding5
 * @Date: 2019/3/13 19:51
 * @Description:
 *  SVN的API查询官网在http://svnkit.com/javadoc/overview-summary.html
 */
public class DoCheckOutUtil {
    //声明SVN客户端管理类
    private static SVNClientManager ourClientManager;

    public static void main(String[] args) throws Exception {

        //初始化支持svn://协议的库。 必须先执行此操作。
        Properties properties = System.getProperties();
        properties.setProperty("svnkit.http.methods", "Basic,Digest,NTLM");
        DAVRepositoryFactory.setup();

        //相关变量赋值
        SVNURL repositoryURL = null;
        try {
            repositoryURL = SVNURL.parseURIEncoded("SVN检出地址initsql/base/lib");
        } catch (SVNException e) {
            //
        }
        String name = "用户名";
        String password = "登录密码";
        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);

        //实例化客户端管理类
        ourClientManager = SVNClientManager.newInstance(
                (DefaultSVNOptions) options, name, password);

        //要把版本库的内容check out到的目录
        File wcDir = new File("e:/svntest/wc");

        //通过客户端管理类获得updateClient类的实例。
        SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
        /*
         * sets externals not to be ignored during the checkout
         */
        updateClient.setIgnoreExternals(false);

        //执行check out 操作，返回工作副本的版本号。
        long workingVersion= updateClient
                .doCheckout(repositoryURL, wcDir, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY,false);

        System.out.println("把版本："+workingVersion+" check out 到目录："+wcDir+"中。");

    }





}
