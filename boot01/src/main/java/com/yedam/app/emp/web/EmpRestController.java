package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

//@CrossOrigin("*") //cors
//@CrossOrigin(origins = "http://localhost:5000") //특정 하나만 test할때 이런식
@RestController //=> @Controller + 모든 메소드에 @ResponseBody 선언하겠다
public class EmpRestController {
	//내부 컨트롤러가 전부 AJAX 전용
	
	@Autowired
	EmpService empService;
	//REST : GET, POST, PUT, DELETE 메소드 사용
	
	//전체조회
	@GetMapping("emps") //보통 복수로 선언
	public List<EmpVO> empList(){ //*Rest방식은 model 필요x(page를 생성하지않기때문에)
		return empService.empList();
		//http://localhost:8080/yedam/emps
	}
	
	//단건조회
	@GetMapping("emps/{empid}") //자원/행위
	public EmpVO empInfo(@PathVariable Integer empid) { //Integer empid는 requestparam로 인식하기때문에 requestbody로 바꿔줘야해, @PathVariable는 VO못받아서 선언해줘 
		EmpVO empVO = new EmpVO();
		empVO.setEmpid(empid);
		return empService.empInfo(empVO);
		//http://localhost:8080/yedam/emps/115
	}
	
	//등록 - EmpController는 화면단이 구성되어있으니까 get,post였지만 이거는 화면단이 아니라서 메소드 1개
	@PostMapping("emps")
	public int empInsert(@RequestBody EmpVO empVO) {
		return empService.empInsert(empVO);
	}
	
	//수정 - 단건조회(의 경로,메소드는 바꿔주고) + 등록(의 처리방식,+empid 매개)과 비슷 (원래항목과 수정된거 합쳐서 넘어옴)=> Patch(수정하고자 하는 항목만 넘어옴) 다이나믹으로 처리해야함
	@PutMapping("emps/{empid}") //단건조회
	public Map<String, Object> empUpdate(@PathVariable Integer empid, @RequestBody EmpVO empVO) { //등록
		empVO.setEmpid(empid);
		return empService.empUpdate(empVO);
	}
	
	//삭제
	@DeleteMapping("emps/{empid}") //단건조회랑 메소드로 구분ㅇㅇ
	public Map<String, Object> empDelete(@PathVariable Integer empid) {
		EmpVO empVO = new EmpVO();
		empVO.setEmpid(empid);
		return empService.empDelete(empVO);
	}
}
