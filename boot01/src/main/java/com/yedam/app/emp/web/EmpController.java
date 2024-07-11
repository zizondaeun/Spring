package com.yedam.app.emp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller //사용자의 요청(endpoint)에 대한 처리를 정의
			// => url + method => service랑 view를 연결시키는 작업
public class EmpController {
	//해당 컨트롤러에서 서비스를 추가
	@Autowired
	EmpService empService;
	
	//get,post방식으로 /rest방식 x(rest는 페이지서버를 제공하지 x) 
	//GET  : 조회, 빈 페이지
	//POST : 데이터 조작(등록, 수정, 삭제)
	
	//전체조회 /컨트롤러가 하나
	@GetMapping("empList")
	public String empList(Model model) { //Model = Request + Response
		//1)기능 수행
		List<EmpVO> list = empService.empList();
		//2)클라이언트에 전달할 데이터 담기(기능을 model에 담아)
		model.addAttribute("empList", list);
		//3)데이터를 출력할 페이지 결정
		return "emp/list";
	}
	
	//단건조회
	@GetMapping("empInfo") //커맨드 객체 => application/x-www-form-urlencoded
	public String empInfo(EmpVO empVO, Model model) { //(커맨드객체 , )
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("empInfo", findVO);
		return "emp/info";
	}
	
	//등록 - 페이지 /페이지 요청 /컨트롤러 보통 두개
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
	}
	
	//등록 - 처리(연산, submit(=form, form은 JSON을 다룰수가 x)) //커맨드 객체 or JSON이나 form은 JSON형태로 보낼수 x -> 커맨드 객체로
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.empInsert(empVO); 
		String url = null;
		if( eid > -1 ) {
			//정상적으로 등록된 경우
			url = "redirect:empInfo?empid=" + eid; //경로 재요청/redirect:로 시작되면 컨트롤러는 시작페이지로..?/redirect는 무조건 ?로 보내
		}else {
			//정상적으로 등록되지 않은 경우
			url = "redirect:empList";
		}
		return url;
	}
	
	//수정 - 페이지 /컨트롤러 보통 두개
	//@PostMapping("empUpdate")
	
	//수정 - 처리(연산, AJAX => QueryString 기반)
	
	//수정 - 처리(연산, AJAX => JSON 기반)
	
	//삭제 - 처리 / 컨트롤러 하나(페이지가 따로 필요 x)
	
}
