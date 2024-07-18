package com.yedam.app.upload.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UploadController {
	@Value("${file.upload.path}")
	private String uploadPath;
	//= "D:/upload"; 
	
	//1) submit으로 파일 업로드한거(formUpload.html)
	@GetMapping("getPath")
	@ResponseBody
	public String getPath() {
		return uploadPath;
	}
	
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
			
			//3)*파일 작성(파일 업로드)
			try {
				image.transferTo(savePath); //*실제 경로 지정 /Path는 경로/transferTo=햇살
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return "formUpload";
	}
	
	//2) AJAX로 파일 업로드한거 화면에 이미지 보이고 upload에 저장됨(실제 우리가 올려야할 것들 다 집어넣은거)
	//(upload.html)
	@GetMapping("upload")
	public void uploadPage() {}
	
	@PostMapping("/uploadsAjax")
	@ResponseBody
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles) {
	    
		List<String> imageList = new ArrayList<>();
		
	    for(MultipartFile uploadFile : uploadFiles){
	    	if(uploadFile.getContentType().startsWith("image") == false){ //개별 파일 종류(이미지,pdf인지 등을 구분할수있음 = getContentType)
	    		System.err.println("this file is not image type"); //이미지만 등록가능 /startsWith = image로 시작하는 경우
	    		return null;
	        }
	  
	        String originalName = uploadFile.getOriginalFilename(); //파일 이름 가져오는거
	        String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
	        
	        System.out.println("fileName : " + fileName);
	    
	        //날짜 폴더 생성
	        String folderPath = makeFolder();
	        //UUID (고유 식별자) - 식별자 역할을 하는 고유한 값 
	        String uuid = UUID.randomUUID().toString();
	        //저장할 파일 이름 중간에 "_"를 이용하여 구분
	        
	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
	        
	        String saveName = uploadPath + File.separator + uploadFileName;
	        
	        Path savePath = Paths.get(saveName);
	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
	        System.out.println("path : " + saveName);
	        try{
	        	uploadFile.transferTo(savePath);
	            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
	        } catch (IOException e) {
	             e.printStackTrace();	             
	        }
	        // DB에 해당 경로 저장
	        // 1) 사용자가 업로드할 때 사용한 파일명
	        // 2) 실제 서버에 업로드할 때 사용한 경로
	        imageList.add(setImagePath(uploadFileName));
	     }
	    
	    return imageList;
	}
	
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); //오늘 날짜 기준으로 자동생성
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator); // "/"를 경로로 인지할수있도록 바꿔주는거 (자바는 /를 \로 인식하기때문)
		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) { //폴더 자동으로 생성하게끔
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}
	
	private String setImagePath(String uploadFileName) { // "/"를 \로 원상복구
		return uploadFileName.replace(File.separator, "/");
	}
	
}
