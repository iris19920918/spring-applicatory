package com.wang.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {

	private final Logger logger = LoggerFactory.getLogger(WebController.class);

	/**
	 * 上传文件
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(ModelMap model) {
		logger.info("跳转到首页");
		return "index";
	}

	/**
	 * 上传文件
	 * @param model
	 * @return
     */
	@RequestMapping("/upload")
	public String upload(ModelMap model) {
		logger.info("跳转到上传文件页面");
		return "upload";
	}
	/**
	 * 下载文件
	 * @param model
	 * @return
	 */
	@RequestMapping("/download")
	public String download(ModelMap model) {
		logger.info("跳转到下载文件页面");
		return "download";
	}
}
