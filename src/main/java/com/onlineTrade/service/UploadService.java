package com.onlineTrade.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	void upload(MultipartFile file, HttpServletRequest request);
}
	