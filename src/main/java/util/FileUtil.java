package util;

import com.wang.config.ParamsConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by WANGDD on 2017/3/8.
 */
@Slf4j
public class FileUtil {

    @Autowired private static ParamsConfiguration paramsConfiguration;

    public static String saveFile(String filePath, String fileName, byte[] bytes) throws IOException {
        String dateFolder = new SimpleDateFormat("yyyyMMdd", Locale.CHINA).format(new Date());
            String suffix = String.valueOf(System.currentTimeMillis());
            if (filePath == null || filePath.trim().length() == 0) {
                filePath = paramsConfiguration.getUploadPath() + "/" + dateFolder + "/";
            } else {
                filePath = filePath + "/" + dateFolder + "/";
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File fullFile = new File(filePath, suffix + fileName);
            String fileFullName = fullFile.getPath();
            FileOutputStream fos = new FileOutputStream(fullFile);
            fos.write(bytes);
            fos.close();
        return fileFullName;
    }

    /**
     * 获取路径
     *
     * @param strPath
     * @return
     */
    public static String getAbsolutePath(String strPath) {
        try {
            java.net.URL tempUrl = FileUtil.class.getResource("/");
            if(tempUrl != null) {
                return URLDecoder.decode(tempUrl.getPath() + strPath, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage() + e.getStackTrace());
        }
        return strPath;
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param sPath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public static boolean deleteFolder(String sPath) {
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return false;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            if (file.delete()) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    private static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        if (files != null) {
            for (File file : files) {
                //删除子文件
                if (file.isFile()) {
                    flag = deleteFile(file.getAbsolutePath());
                    if (!flag) break;
                } //删除子目录
                else {
                    flag = deleteDirectory(file.getAbsolutePath());
                    if (!flag) break;
                }
            }
            if (!flag) return false;
            //删除当前目录
            if (dirFile.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
