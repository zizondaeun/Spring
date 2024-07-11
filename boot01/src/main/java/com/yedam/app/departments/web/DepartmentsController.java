package com.yedam.app.departments.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
}
