package com.wang.web.file;

import com.wang.config.ParamsConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import util.FileUtil;

@Controller
@RequestMapping("/upload")
public class FileUploadController {
	
	private static Logger logger = Logger.getLogger(FileUploadController.class);

	@Autowired
	private ParamsConfiguration paramsConfiguration;

	@RequestMapping("/uploadExcel")
	@ResponseBody
	public void uploadExcel(@RequestParam("file")MultipartFile file, @RequestParam("orgId") Long orgId){
		try {
			/*
			上传文件
			 */
			String fileName = file.getOriginalFilename();
			String filePath = paramsConfiguration.getUploadPath();
			byte[] bytes = file.getBytes();
			String fullFilePath = FileUtil.saveFile(filePath, fileName, bytes);
			System.out.println("File path: " + fullFilePath);
			logger.info("File upload success.");
		} catch (Exception e) {
			logger.warn("File upload fail.", e);
			e.printStackTrace();
		}
	}

}
