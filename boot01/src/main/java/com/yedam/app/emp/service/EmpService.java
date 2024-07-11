package com.yedam.app.emp.service;

import java.util.List;
import java.util.Map;

//사용자에게 제공하는 기능(@따로 필요x)
public interface EmpService {
	//전체 사원정보 조회
	public List<EmpVO> empList();
	
	//사원정보 조회
	public EmpVO empInfo(EmpVO empVO);

	//사원정보 등록(기능에서는 int일 필요 x)
	public int empInsert(EmpVO empVO);
	
	//사원정보 수정
	public Map<String, Object> empUpdate(EmpVO empVO);
	
	//사원정보 삭제
	public Map<String, Object> empDelete(EmpVO empVO);
	
}
