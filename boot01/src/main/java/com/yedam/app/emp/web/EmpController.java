package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@GetMapping("empList") //얘는 /있어도 상관없는데
	public String empList(Model model) { //Model = Request + Response /파라미터 필요 x, model(resp)을 담음
		//1)기능 수행
		List<EmpVO> list = empService.empList();
		//2)*클라이언트에 전달할 데이터 담기(기능을 model에 담아) /*어떤 이름("empList")(배열-반복문)으로 보내는지 주의깊게 보기
		model.addAttribute("empList", list);
		//3)데이터를 출력할 페이지 결정
		return "emp/list"; //얘는 앞에 /없어야함
		//타임리프
		//classpath:/templates/	emp/list	.html
		//prefix 				return		suffix
		// => classpath:/templates/emp/list.html
	}
	
	//단건조회
	@GetMapping("empInfo") //커맨드 객체 => application/x-www-form-urlencoded
	public String empInfo(EmpVO empVO, Model model) { //(커맨드객체 , ) /(파라미터 처리, model)
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("empInfo", findVO); //정보는 여기에 담겨있음 위에는 그냥 문패
		return "emp/info";
		//경로
		//classpath:/templates/	emp/info	.html
		// => classpath:/templates/emp/info.html
	}
	
	//등록 - 페이지 /페이지 요청 /컨트롤러 보통 두개
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
		//경로
		//classpath:/templates/	emp/insert	.html
		// => classpath:/templates/emp/insert.html
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
	
	//수정 - 페이지 => 단건조회 /컨트롤러 보통 두개
	@GetMapping("empUpdate")
	public String empUpdateForm(@RequestParam Integer empid, Model model) {
		EmpVO empVO = new EmpVO();
		empVO.setEmpid(empid);
		
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("empInfo", findVO);
		
		return "emp/update";
		//경로
		//classpath:/templates/	emp/update	.html
		// => classpath:/templates/emp/update.html
	}
	
	//수정 - 처리(연산, AJAX => QueryString 기반) /페이지 요청 x(데이터를 가져오는것) ,페이지를 일부분만 수정하는것(통신량을 줄이는 방식):ajax
	//@PostMapping("empUpdate")
	@ResponseBody // => AJAX로 보낼때 반드시 써야할 @
	public Map<String, Object> empUpdateAJAXQueryString(EmpVO empVO){
		return empService.empUpdate(empVO);
	}
	
	//수정 - 처리(연산, AJAX => JSON 기반 : @RequestBody)
	@PostMapping("empUpdate")
	@ResponseBody // => AJAX로 보낼때 반드시 써야할 @(=서버가 돌려보내주는게 page냐 "data"냐)
	public Map<String, Object> empUpdateAJAXJSON(@RequestBody EmpVO empVO){ //@RequestBody = json포맷을 쓰겠다는 선언
		return empService.empUpdate(empVO);
	}
	
	//삭제 - 처리 / 컨트롤러 하나(페이지가 따로 필요 x) /삭제는 ajax 보통 안씀. 데이터가 지워졌는데 다시 보여질 필요가 없기때문에
	@GetMapping("empDelete")
	public String empDelete(EmpVO empVO) {
		empService.empDelete(empVO);
		return "redirect:empList";
	}
}
