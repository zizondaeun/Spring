package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@Controller //웹과 관련된 Bean이라는 의미
public class TestController {
	
	@RequestMapping("test") //모든 경로 다 허용해서 get,post,put 다 허용하는거
	@ResponseBody
	public String userTest(String keyword) {
		return "Server Response : " + keyword;
	}
	
	//@RequestMapping(path="/sample", method=RequestMethod.GET) 
	@GetMapping("sample")
	@ResponseBody
	public String urlGetTest(String keyword) {
		return "Server Response : SELECT - " + keyword;
	}
	
	//@RequestMapping(path="/sample", method=RequestMethod.POST) 
	@PostMapping("sample")
	@ResponseBody
	public String urlPostTest(String keyword) {
		return "Server Response : INSERT - " + keyword;
	}
	
	@GetMapping("param")
	@ResponseBody
	public EmpVO paramGetTest(EmpVO empVO) {
		return empVO;
	}
}
