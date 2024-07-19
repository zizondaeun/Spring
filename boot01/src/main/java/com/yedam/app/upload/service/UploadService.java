package com.yedam.app.upload.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	public String imageUpload(MultipartFile uploadFile);
}
