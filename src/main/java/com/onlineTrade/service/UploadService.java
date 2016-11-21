package com.onlineTrade.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传类
 * 
 * @author kilido
 *
 */
public interface UploadService {
	/**
	 * 将上传的文件路径保存
	 * 
	 * @param file
	 * @param request
	 */
	void upload(MultipartFile file, HttpServletRequest request);

	/**
	 * 获取存储的路径
	 * 
	 * @return
	 */
	String getPath();
}
