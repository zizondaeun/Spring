package com.yedam.app.test.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@Controller
public class ParamController {
	//QueryString(질의문자열) : key=value&key=value&..
	//method는 상관없음
	//Content-type : application/x-www-form-urlencoded
	
	//QueryString => 커맨드 객체 : 클래스 타입으로만 데이터를 받음	
	@RequestMapping(path="comobj",
			method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "path : / comobj\n";
		result += "\t empid : " + empVO.getEmpid();
		result += "\t empname : " + empVO.getEmpname();
		return result;
	}
		
	//QueryString => @RequestParam : 기본 타입, 단일 값
	@RequestMapping(path="reqparm",
			method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requestParam(
			String empname, //생략가능(필수 x)
			@RequestParam Integer empid, //필수
			@RequestParam(name = "message", //name 속성이 있으니까 name을 넣어서 보내야함 변수명(msg)이 아니라
							defaultValue = "No message", 
							required = true) String msg) {
		String result = "";
		result += "path : /reqparm \n";
		result += "\t empid : " + empid;
		result += "\t empname : " + empname;
		result += "\t message : " + msg;
		return result;
	}
	
	//@PathVariable : 경로에 값을 포함하는 방식, 단일 값
	//method는 상관없음
	//Content-type도 상관없음
	@RequestMapping(path = {"path/{id}/{pwd}", "path/{id}"}) //path/Hong/1234, path/1234/1234
	@ResponseBody
	public String pathVariable(@PathVariable String id,
			@PathVariable(name = "pwd", required = false) String password) {
		//기본값 설정
		if(password == null) password = "1234";
		
		String result = "";
		result += "path : /path/{id}/{pwd} \n";
		result += "\t id : " + id;
		result += "\t pwd : " + password;
		return result;
	}
	
	//@RequestBody : JSON 포맷, 배열 or 객체
	//method : POST, PUT
	//Content-type : application/json
	//배열 - content-type만 달라짐
	@PostMapping("resbody")
	@ResponseBody
	public String requestBody(@RequestBody EmpVO empVO) {
		String result = "";
		result += "path : / resbody\n";
		result += "\t empid : " + empVO.getEmpid();
		result += "\t empname : " + empVO.getEmpname();
		return result;
	}
	
	//JSON타입으로 배열을 처리할때 코드
	@PostMapping("resbodyList")
	@ResponseBody
	public String requestBodyList(@RequestBody List<EmpVO> list) {
		String result = "path : /resbodyList \n";
		for(EmpVO empVO : list) {
			result += "\t empid : " + empVO.getEmpid();
			result += "\t empname : " + empVO.getEmpname();
			result += "\n";
		}
		return result;
	}
	
}
