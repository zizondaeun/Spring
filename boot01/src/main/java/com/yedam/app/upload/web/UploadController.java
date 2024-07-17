package com.yedam.app.upload.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UploadController {
	@Value("${file.upload.path}")
	private String uploadPath = "D:/upload"; 
	
	@GetMapping("formUpload") //경로가 이미 페이지니까 /void는 return할 게 없다
	public void formUploadPage() {} //페이지 요청일뿐
	//classpath:/templates/formUpload.html
		
	@PostMapping("uploadForm")
	public String formUploadFile(@RequestPart MultipartFile[] images) { //*다중업로드면 반드시 []로 등록해줘야함!/여러건이 아니면 [] 빼고 formUpload.html에 multiple 빼
		//log.info(images[0].getOriginalFilename()); //파일 이름만 가져온거
		for(MultipartFile image : images) {
			log.warn(image.getContentType()); //개별 파일의 종류
			log.warn(image.getOriginalFilename()); //사용자가 넘겨준 실제 파일이름
			log.warn(String.valueOf(image.getSize())); //파일크기

			//1)원래 파일이름
			String fileName = image.getOriginalFilename();
			
			//2)실제로 저장할 경로를 생성 : 서버의 업로드 경로 + 파일이름
			String saveName = uploadPath + File.separator + fileName; //""가 /와 같아
			log.debug("saveName : " + saveName);
			
			Path savePath = Paths.get(saveName); //여기에 경로 담았음
			
			//3)파일 작성(파일 업로드)
			try {
				image.transferTo(savePath); //*실제 경로 지정	
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return "formUpload";
	}
	
}
