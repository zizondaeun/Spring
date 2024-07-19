package com.yedam.app.emp.mapper;

import java.util.List;

import com.yedam.app.emp.service.EmpReqVO;
import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	public EmpVO getEmp(EmpVO vo);
	public List<EmpVO> getEmpList(EmpReqVO vo);
	public List<EmpVO> getEmpByDept(String[] dept);
	int insert(EmpVO vo);
}
