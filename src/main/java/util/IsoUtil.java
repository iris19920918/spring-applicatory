package util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Auther: wangdingding5
 * @Date: 2019/2/28 11:12
 * @Description:
 */
@Slf4j
public class IsoUtil {

    /**
     * 将指定iso文件挂载到linux文件系统中
     * mount /var/daoru/Infovision_iEstate_DS_V1.0.0_20190330_Win_x64_zh-CN.iso /var/daoru/1 -o loop
     * @param isoPath /var/daoru/Infovision_iEstate_DS_V1.0.0_20190330_Win_x64_zh-CN.iso
     * @return 挂载路径
     */
    public static String mountIsoLinux(String isoPath) {
        if (StringUtils.isBlank(isoPath)) {
            return null;
        }
        String dateStr = DateUtils.format(new Date(), DateUtils.PATTERN_TIME_LONG);
        String tempPath = isoPath.substring(0, isoPath.lastIndexOf(File.separator)) + File.separator + dateStr;
        File tempDir = new File(tempPath);
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("mount ");
        stringBuffer.append(isoPath);
        stringBuffer.append(" ");
        stringBuffer.append(tempPath);
        stringBuffer.append(" -o rw"); //rw：采用读写方式挂接设备
        String shell = stringBuffer.toString();
        log.info("将指定iso文件挂载到linux文件系统中-shell: {}", shell);
        Runtime runtime = Runtime.getRuntime();

        Process process = null;
        try {
            process = runtime.exec(new String[]{"/bin/sh", "-c", shell});
        } catch (IOException e) {
            log.error("将指定iso文件挂载到linux文件系统中-", e);
        }

        try {
            int value = process.waitFor(); //从Process中获取执行结果
            if (value == 0) {
                log.info("mount file success and file path is:{}", isoPath);
            } else {
                log.error("mount file error and file path is:{} and runtime return module is:{}", isoPath, value);
//                throw new ProgramException(PackageErrorCode.DECOMPRESSION_FILE_ERROR, "mount file has a exception and file path is: " + isoPath);
            }
        } catch (InterruptedException e) {
            log.error("将指定iso文件挂载到linux文件系统中-", e);
        }
        return tempPath;
    }

}
