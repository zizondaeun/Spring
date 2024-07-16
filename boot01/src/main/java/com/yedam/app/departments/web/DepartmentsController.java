package com.yedam.app.departments.web;

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

import com.yedam.app.departments.service.DepartmentsService;
import com.yedam.app.departments.service.DepartmentsVO;

@Controller
public class DepartmentsController {
	@Autowired
	DepartmentsService deptService;
	
	//전체조회
	@GetMapping("deptList")
	public String deptList(Model model) {
		List<DepartmentsVO> list = deptService.deptList();
		model.addAttribute("deptList", list);
		return "dept/list";
	}
	
	//단건조회
	@GetMapping("deptInfo")
	public String deptInfo(DepartmentsVO deptVO, Model model) {
		DepartmentsVO findVO = deptService.deptInfo(deptVO);
		model.addAttribute("deptInfo", findVO);
		return "dept/info";
	}
	
	//등록 - 페이지
	@GetMapping("deptInsert")
	public String deptInsertForm() {
		return "dept/insert";
	}
	
	//등록 - 처리
	@PostMapping("deptInsert")
	public String deptInsertProcess(DepartmentsVO deptVO) {
		int did = deptService.deptInsert(deptVO);
		String url = null;
		if(did > -1) {
			url = "redirect:deptInfo?departmentId=" + did;
		}else {
			url = "redirect:deptList";
		}
		return url;
	}
	
	//수정 - 페이지 => 단건조회/컨트롤러 보통 두개
	@GetMapping("deptUpdate")
	public String deptUpdateForm(@RequestParam Integer departmentId, Model model) {
		DepartmentsVO deptVO = new DepartmentsVO();
		deptVO.setDepartmentId(departmentId);
		
		DepartmentsVO findVO = deptService.deptInfo(deptVO);
		model.addAttribute("deptInfo", findVO);
		
		return "dept/update";
	}
	
	//수정 - 처리(연산, AJAX => QueryString 기반)
	//@PostMapping("deptUpdate")
	@ResponseBody
	public Map<String, Object> deptUpdateAJAXQueryString(DepartmentsVO deptVO){
		return deptService.deptUpdate(deptVO);		
	}
		
	//수정 - 처리(연산, AJAX => JSON 기반)
	@PostMapping("deptUpdate")
	@ResponseBody
	public Map<String, Object> deptUpdateAJAXJSON(@RequestBody DepartmentsVO deptVO){
		return deptService.deptUpdate(deptVO);
	}
	
	//삭제 - 처리 / 컨트롤러 하나(페이지가 따로 필요 x)
	@GetMapping("deptDelete")
	public String deptDelete(DepartmentsVO deptVO) {
		deptService.deptDelete(deptVO);
		return "redirect:deptList";
	}
}
