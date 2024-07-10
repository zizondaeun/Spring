package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	//Mapper는 보통 테이블을 기준
	//전체조회 - 여러건 조회이기 때문에 list
	public List<EmpVO> selectEmpAll();
	//단건조회 - 1건이기 때문에 / 매개변수로 EmpVO받아
	public EmpVO selectEmpInfo(EmpVO empVO);
	//등록
	public int insertEmpInfo(EmpVO empVO);
	//수정 - 매개값이 2개일 경우 mybatis는 하나만을 인식하기때문에 @Param을 이용하여 찾을 수 있음
	public int updateEmpInfo(@Param ("id") int empId, @Param ("emp") EmpVO empVO);
	//삭제 - 굳이 EmpVO일 필요 x 
	public int deleteEmpInfo(int empId);
}
