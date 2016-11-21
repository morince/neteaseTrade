package com.onlineTrade.service.impl;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onlineTrade.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

	private String storePath;

	public void upload(MultipartFile file, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("/") + "images//";
		// String path1 = request.getRequestURL().toString();
		// String path1 = "http://localhost:8080/images//";
		String path2 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ "/images/";
		System.out.println(path2);
		long time = System.currentTimeMillis();
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path, time + file.getOriginalFilename()));
			this.storePath = path2 + time + file.getOriginalFilename();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String getPath(){
		return storePath;
	}
}