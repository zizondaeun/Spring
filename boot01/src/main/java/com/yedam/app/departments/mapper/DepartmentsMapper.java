package com.yedam.app.departments.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.departments.service.DepartmentsVO;

public interface DepartmentsMapper {
	//전체조회
	public List<DepartmentsVO> selectDeptAll();
	//단건조회
	public DepartmentsVO selectDeptInfo(DepartmentsVO deptVO);
	//등록
	public int insertDeptInfo(DepartmentsVO deptVO);
	//수정
	public int updateDeptInfo(@Param ("id") int departmentId, @Param ("dept") DepartmentsVO deptVO);
	//삭제
	public int deleteDeptInfo(int departmentId);
}
