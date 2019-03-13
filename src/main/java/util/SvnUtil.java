package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Auther: wangdingding5
 * @Date: 2019/3/13 19:52
 * @Description:
 */
public class SvnUtil {
    private Logger logger = LoggerFactory.getLogger(SvnUtil.class);

//    @Inject(instance = PropertyUtil.class)
//    private PropertyUtil propertyUtil;

    static {
        DAVRepositoryFactory.setup();
    }

    private SVNClientManager manager;
    private SVNUpdateClient updateClient;
    private String url;
    private String userName;
    private String password;


    public SvnUtil(String userName, String passwd) {
        init(userName, passwd);
    }

    public SvnUtil(String userName, String passwd, String url){
        this(userName,passwd);
        this.url=url;
    }

    private void init(String userName,String passwd){
        DefaultSVNOptions options = new DefaultSVNOptions();
        manager = SVNClientManager.newInstance(options);
        manager = SVNClientManager.newInstance(options,userName,passwd);
        updateClient = manager.getUpdateClient();
        updateClient.setIgnoreExternals(false);
    }

    public long checkOut(String localPath, String url) throws SVNException {
        //要把版本库的内容check out到的目录
        File wcDir = new File(localPath);
        SVNUpdateClient updateClient = manager.getUpdateClient();//通过客户端管理类获得updateClient类的实例。
        updateClient.setIgnoreExternals(false);//sets externals not to be ignored during the checkout

        //执行check out 操作，返回工作副本的版本号。
        long workingVersion= updateClient
                .doCheckout(SVNURL.parseURIEncoded(url), wcDir, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY,false);

        System.out.println("把版本："+workingVersion+" check out 到目录："+wcDir+"中。");
        return workingVersion;
    }

    /**获取文档内容
     * @param url
     * @return
     */
    public String checkoutFileToString(String url) throws SVNException {//"", -1, null
        SVNRepository repository = createRepository(url);
        SVNDirEntry entry = repository.getDir("", -1, false, null);
        int size = (int)entry.getSize();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(size);
        SVNProperties properties = new SVNProperties();
        repository.getFile("", -1, properties, outputStream);
        String doc = new String(outputStream.toByteArray(), Charset.forName("utf-8"));
        return doc;
    }

    public boolean toParantFolder(){
        if(url!=null){
            StringBuffer sb = new StringBuffer(url);
            if(url.endsWith("/")){
                sb.deleteCharAt(sb.length()-1);
            }
            int index = sb.lastIndexOf("/");
            url=sb.substring(0, index);
            return true;
        }
        return false;
    }

    /**进入子目录
     * @param folder
     * @return
     */
    public boolean toChildFolder(String folder){
        if(url!=null){
            StringBuffer sb = new StringBuffer(url);
            boolean a = url.endsWith("/");
            boolean b = folder.startsWith("/");
            if(a^b){
                sb.append(folder);
            }else if(a&b){
                sb.deleteCharAt(sb.length()-1);
                sb.append(folder);
            }else{
                sb.append('/').append(folder);
            }
            if(checkPath(sb.toString())==1){
                this.url=sb.toString();
                return true;
            }
        }
        return false;
    }

    /**获取当前目录下的子目录和文件
     * @return
     * @throws SVNException
     */
    public List<SVNDirEntry> listFolder() throws SVNException {
        return listFolder(url);
    }

    /**列出指定SVN 地址目录下的子目录
     * @param url
     * @return
     * @throws SVNException
     */
    public List<SVNDirEntry> listFolder(String url){
        if(checkPath(url)==1){

            SVNRepository repository = createRepository(url);
            try {
                Collection<SVNDirEntry> list = repository.getDir("", -1, null, (List<SVNDirEntry>)null);
                List<SVNDirEntry> dirs = new ArrayList<SVNDirEntry>(list.size());
                dirs.addAll(list);
                return dirs;
            } catch (SVNException e) {
                logger.error("listFolder error",e);
            }

        }
        return null;
    }

    private SVNRepository createRepository(String url){

        try {
            return manager.createRepository(SVNURL.parseURIEncoded(url), true);
        } catch (SVNException e) {
            logger.error("createRepository error",e);
        }
        return null;
    }

    /**检查路径是否存在
     * @param url
     * @return 1：存在    0：不存在   -1：出错
     */
    public int checkPath(String url){
        SVNRepository repository = createRepository(url);
        SVNNodeKind nodeKind;
        try {
            nodeKind = repository.checkPath("", -1);
            boolean result = nodeKind == SVNNodeKind.NONE ? false : true;
            if(result) return 1;
        } catch (SVNException e) {
            logger.error("checkPath error",e);
            return -1;
        }
        return 0;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
