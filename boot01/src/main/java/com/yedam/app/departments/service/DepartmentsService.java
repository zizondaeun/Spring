package com.yedam.app.departments.service;

import java.util.List;
import java.util.Map;

public interface DepartmentsService {
	//전체조회
	public List<DepartmentsVO> deptList();
	
	//단건조회
	public DepartmentsVO deptInfo(DepartmentsVO deptVO);
	
	//등록
	public int deptInsert(DepartmentsVO deptVO);
	
	//수정
	public Map<String, Object> deptUpdate(DepartmentsVO deptVO);
	
	//삭제
	public Map<String, Object> deptDelete(DepartmentsVO deptVO);
}
