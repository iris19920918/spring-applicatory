package util;

import com.wang.exception.PackageErrorCode;
import com.wang.exception.ProgramException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * 解压缩工具类
 *
 * @author kewenli
 */
@Slf4j
public class ZipUtil {

    private static final int buffer = 2048;

    /**
     * 解压文件
     *
     * @param path 文件目录
     * @return
     */
    public static String unZip(String path) {
        int count = -1;
        String savePath = "";

        File file = null;
        InputStream is = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        //保存解压文件目录
        savePath = path.substring(0, path.lastIndexOf(".")) + File.separator;
        //创建保存目录
        new File(savePath).mkdir();
        ZipFile zipFile = null;

        try {
            //解决中文乱码问题
            zipFile = new ZipFile(path, Charset.forName("GBK"));
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                byte buf[] = new byte[buffer];
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String filename = entry.getName();
                boolean ismkdir = false;
                //检查此文件是否带有文件夹
                if (filename.lastIndexOf("/") != -1) {
                    ismkdir = true;
                }
                filename = savePath + filename;
                //如果是文件夹先创建
                if (entry.isDirectory()) {
                    file = new File(filename);
                    file.mkdirs();
                    continue;
                }
                file = new File(filename);
                //如果是目录先创建
                if (!file.exists()) {
                    if (ismkdir) {
                        //目录先创建
                        new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs();
                    }
                }
                //创建文件
                file.createNewFile();

                is = zipFile.getInputStream(entry);
                fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos, buffer);

                while ((count = is.read(buf)) > -1) {
                    bos.write(buf, 0, count);
                }
                bos.flush();
                bos.close();
                fos.close();
                is.close();
            }
            zipFile.close();
        } catch (IOException ioe) {
            log.error(ioe.getMessage() + ioe.getStackTrace());
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (is != null) {
                    is.close();
                }
                if (zipFile != null) {
                    zipFile.close();
                }
            } catch (Exception e) {
                log.error(e.getMessage() + e.getStackTrace());
            }
        }

        return savePath;
    }

    /**
     * 使用7za解压文件
     *
     * @param path
     * @return
     */
    public static String unzip7za(String path) {
        //判断操作系统类型
        String osName = System.getProperties().getProperty("os.name");
        if (osName.startsWith("Windows") || osName.startsWith("windows")) {
            if (path.startsWith("/") || path.startsWith("\\")) {
                path = path.substring(1);
            }
        }

        String root = FileUtil.getAbsolutePath("");
        String path7za = "";
        if (osName.startsWith("Windows") || osName.startsWith("windows")) {
            path7za = root + "7za" + File.separator + "7za.exe";
        }

        return unzip7za(path7za, path, osName);
    }

    /**
     * 使用7za解压文件
     *
     * @param path7za
     * @param filePath
     * @return
     */
    public static String unzip7za(String path7za, String filePath, String osName) {
        Process process = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String unzipgoal = null;

        try {
            // 取得解压缩文件的目录
            unzipgoal = getUnzipPath(filePath);
            StringBuilder cmd = new StringBuilder();
            Runtime rt = Runtime.getRuntime();

            if (osName.startsWith("Windows") || osName.startsWith("windows")) {
                if (path7za.startsWith("/") || path7za.startsWith("\\")) {
                    path7za = path7za.substring(1);
                }
                cmd.append("\"").append(path7za.replace("/", File.separator)).append("\"");// 目录前后添加双引号避免包含空格
                cmd.append(" x \"").append(filePath.replace("/", File.separator)).append("\"");
                cmd.append(" -o\"" + unzipgoal.replace("/", File.separator) + "\"").append(" -y");
                log.info("cmd:" + cmd);
                //执行解压
                process = rt.exec(new String[]{"cmd", "/C", cmd.toString()});
            } else {
                cmd.append("7za");
                cmd.append(" x '").append(filePath).append("'");
                cmd.append(" -o'" + unzipgoal + "' -y");

                process = rt.exec(new String[]{"/bin/sh", "-c", cmd.toString()});
            }
            log.info("cmd: " + cmd.toString());

            String s;
            inputStreamReader = new InputStreamReader(process.getInputStream(), "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((s = bufferedReader.readLine()) != null) {
                if (log.isDebugEnabled()) {
                    log.debug(s);
                }
            }

            //从Process中获取执行结果
            int value = process.waitFor();
            if (value == 0) {
                log.info("decompression file success and file path is:{}", filePath);
            } else {
                log.error("decompression file error and file path is:{} and runtime return module is:{}", filePath, value);
                throw new ProgramException(PackageErrorCode.DECOMPRESSION_FILE_ERROR, "unzip file has a exception and file path is:" + filePath);
            }

            //执行编码转换
            if (!osName.startsWith("Windows") && !osName.startsWith("windows")) {
                cmd = new StringBuilder();
                cmd.append("convmv -f utf8 -t gbk --notest -r").append(" '.").append(File.separator + unzipgoal).append("'");
                process = rt.exec(new String[]{"/bin/sh", "-c", cmd.toString()});
            }
            log.info("cmd: " + cmd.toString());

            inputStreamReader = new InputStreamReader(process.getInputStream(), "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((s = bufferedReader.readLine()) != null) {
                log.trace(s);
            }

        } catch (Exception e) {
            File zipFile1 = new File(filePath);
            if (zipFile1.exists()) {
                FileUtil.deleteFolder(filePath);
            }
            if (StringUtils.isNotBlank(unzipgoal)) {
                FileUtil.deleteFolder(unzipgoal);
            }
            log.error(e.getMessage(), e);
            throw new ProgramException(PackageErrorCode.DECOMPRESSION_FILE_ERROR, "runtime get error and file path is:" + filePath);
        } finally {
            IOUtils.closeQuietly(inputStreamReader);
            IOUtils.closeQuietly(bufferedReader);
            if (process != null) {
                process.destroy();
            }
        }
        return unzipgoal + File.separator;
    }

    /**
     * 函数功能描述:压缩文件
     *
     * @param filePath 倍压缩文件路径
     * @throws Exception
     */
    public static String zip(String filePath) {
        log.info("link[130] zip filePath : " + filePath);
        String path7za = getPath7za();
        log.info("link[146] zip path7za : " + path7za);
        String zipFilePath = getZipPath(filePath);
        return zip(path7za, filePath, zipFilePath);
    }

    /**
     * 函数功能描述:压缩文件
     *
     * @param filePath 倍压缩文件路径
     * @param zipFileName 指定压缩文件名称
     * @throws Exception
     */
    public static String zipFileName(String filePath, String zipFileName) {
        log.info("link[130] zip filePath : " + filePath);
        String path7za = getPath7za();
        log.info("link[146] zip path7za : " + path7za);
        String zipFilePath = getZipFileNamePath(filePath, zipFileName);
        return zip(path7za, filePath, zipFilePath);
    }

    /**
     * 函数功能描述:压缩文件
     *
     * @param zaPath      压缩命令前段
     * @param filePath    要压缩的文件路径
     * @param zipFilePath 压缩文件存放路径（包括文件名）
     * @throws Exception
     */
    public static String zip(String zaPath, String filePath, String zipFilePath) {
        String osName = System.getProperties().getProperty("os.name");
        if (osName.startsWith("Windows") || osName.startsWith("windows")) {
            if (filePath.startsWith("/") || filePath.startsWith("\\")) {
                filePath = filePath.substring(1);
            }
        }
        log.info("link[164] zip zaPath : " + zaPath);
        log.info("link[165] zip filePath : " + filePath);
        log.info("link[165] zip zipFilePath : " + zipFilePath);

        Process process = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            Runtime rt = Runtime.getRuntime();
            StringBuilder cmd = new StringBuilder();
            if (osName.startsWith("Windows") || osName.startsWith("windows")) {
                if (zaPath.startsWith("/") || zaPath.startsWith("\\")) {
                    zaPath = zaPath.substring(1);
                }
                cmd.append("\"").append(zaPath.replace("/", File.separator)).append("\"");// 目录前后添加双引号避免包含空格
                cmd.append(" a -tzip ").append(zipFilePath);
                cmd.append(" " + filePath + "/* ");
                log.info("cmd:" + cmd);
                process = rt.exec(new String[]{"cmd", "/C", cmd.toString()}); //执行压缩过程
            } else {
                cmd.append("." + File.separator + "7za" + File.separator + "7za");
                cmd.append(" a -tzip '.").append(File.separator).append(zipFilePath).append("'");
                cmd.append(" '." + File.separator + filePath + "/*' ");
                log.info("cmd:" + cmd);
                process = rt.exec(new String[]{"/bin/sh", "-c", cmd.toString()}); //执行压缩过程
            }
            String s;
            inputStreamReader = new InputStreamReader(process.getInputStream(), "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((s = bufferedReader.readLine()) != null) {
                if (log.isDebugEnabled()) {
                    log.debug("s:{}", s);
                }
            }

            //从Process获取执行结果
            int value = process.waitFor();
            if (value == 0) {
                log.info("compression file success and file path is:{}", filePath);
            } else {
                log.error("compression file error and file path is:{} and runtime return module is:{}", filePath, value);
                throw new ProgramException(PackageErrorCode.COMPRESSION_FILE_ERROR, "compression file has a exception and file path is:" + filePath);
            }
        } catch (Exception e) {
            File zipFile1 = new File(zipFilePath);
            if (zipFile1.exists()) {
                FileUtil.deleteFolder(zipFilePath);
            }
            log.error(e.getMessage(), e);
            throw new ProgramException(PackageErrorCode.COMPRESSION_FILE_ERROR, "runtime get error and file path is:" + filePath);
        } finally {
            IOUtils.closeQuietly(inputStreamReader);
            IOUtils.closeQuietly(bufferedReader);
            if (process != null) {
                process.destroy();
            }
        }
        return zipFilePath;
    }

    /**
     * 函数功能描述:取得压缩文件的目录
     *
     * @param zipPath
     * @return
     * @throws Exception
     */
    private static String getZipPath(String zipPath) {
        return zipPath + ".zip";
    }

    /**
     * 函数功能描述:取得压缩文件的目录
     *
     * @param zipPath
     * @return
     * @throws Exception
     */
    private static String getZipFileNamePath(String zipPath, String zipFileName) {
        return zipPath.substring(0, zipPath.lastIndexOf(File.separator)) + File.separator + zipFileName;
    }

    /**
     * 根据操作系统获取对应的7za压缩命令前段
     * @return
     */
    private static String getPath7za() {
        String root = FileUtil.getAbsolutePath("");
        String osName = System.getProperties().getProperty("os.name");
        if (osName.startsWith("Windows") || osName.startsWith("windows")) {
            if (root.startsWith("/") || root.startsWith("\\")) {
                root = root.substring(1);
            }
        }
        String path7za = "";
        if (StringUtils.isBlank(osName)) {
            path7za = root + "7za" + File.separator + "7za";
        } else if (osName.startsWith("Windows") || osName.startsWith("windows")) {
            path7za = root + "7za" + File.separator + "7za.exe";
        } else {
            path7za = root + "7za" + File.separator + "7za";
        }
        return path7za;
    }

    /**
     * 函数功能描述:取得解压缩文件的目录
     *
     * @param zipPath
     * @return
     * @throws Exception
     */
    private static String getUnzipPath(String zipPath) {
//        String path = zipPath.substring(0, zipPath.lastIndexOf("."));
        String path = zipPath.substring(0, zipPath.lastIndexOf(File.separator)) + File.separator + UUID.randomUUID().toString() + File.separator
                + zipPath.substring(zipPath.lastIndexOf(File.separator) + 1, zipPath.lastIndexOf("."));
        File file = new File(path);
        if (!file.exists() && !file.mkdirs()) {
            log.error("get decompression file has a exception and compression file path is:{}", zipPath);
            throw new ProgramException(PackageErrorCode.FILE_PATH_ERROR, "get decompression file path error and compression file path is:" + zipPath);
        }
        return path;
    }

    /**
     * 无需解压直接读取Zip文件和文件内容
     * @param file
     * @throws Exception
     */
    public static void readZipFile(String file) throws Exception {
        ZipFile zf = new ZipFile(file);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.isDirectory()) {
            } else {
//                System.err.println("file - " + ze.getName() + " : "
//                        + ze.getSize() + " bytes");
                long size = ze.getSize();
                if (size > 0) {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(zf.getInputStream(ze)));
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                    br.close();
                }
                System.out.println();
            }
        }
        zin.closeEntry();
    }

}
