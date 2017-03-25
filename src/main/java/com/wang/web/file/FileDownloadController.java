package com.wang.web.file;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Created by WANGDD on 2017/3/25.
 * 文件下载
 */
@Controller
@RequestMapping("/download")
public class FileDownloadController {

    /**
     * 下载文件系统里面的资源
     * @param filePath
     * @return
     * @throws IOException
     */
    @RequestMapping("/downloadByFileSys")
    public ResponseEntity<InputStreamResource> downloadByFileSys(String filePath) throws IOException {

        System.out.println("start download file:" + filePath);

        FileSystemResource file = new FileSystemResource(filePath);
        String fileName = new String(file.getFilename().substring(13).getBytes("gb2312"), "ISO8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));

    }
}
