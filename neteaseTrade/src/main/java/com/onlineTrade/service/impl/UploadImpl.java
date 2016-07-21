package com.onlineTrade.service.impl;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onlineTrade.service.UploadService;
@Service
public class UploadImpl implements UploadService {
	
	private MultipartFile file;
	
	private HttpServletRequest request ;
	
	private String storePath;
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getStorePath() {
		return storePath;
	}
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}
	@Override
	public void upload(MultipartFile file, HttpServletRequest request) {
			String path = request.getSession().getServletContext().getRealPath("\\image");
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(),
						new File(path, "\\"+System.currentTimeMillis() + file.getOriginalFilename()));
				storePath = path+"\\"+System.currentTimeMillis() + file.getOriginalFilename();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}