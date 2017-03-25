package util;

import com.wang.config.ParamsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by WANGDD on 2017/3/8.
 */
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
}
