package com.wang.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by WANGDD on 2017/3/21.
 * 可配置的参数
 */
@Configuration
@ConfigurationProperties("params")
public class ParamsConfiguration {
    /**
     * 文件上传路径
     */
    private String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
