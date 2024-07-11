package com.yedam.app.departments.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.departments.mapper.DepartmentsMapper;
import com.yedam.app.departments.service.DepartmentsService;
import com.yedam.app.departments.service.DepartmentsVO;

@Service
public class DepartmentsImpl implements DepartmentsService{
	
	@Autowired
	private DepartmentsMapper deptMapper;
	
	@Override
	public List<DepartmentsVO> deptList() {
		return deptMapper.selectDeptAll();
	}

	@Override
	public DepartmentsVO deptInfo(DepartmentsVO deptVO) {
		return deptMapper.selectDeptInfo(deptVO);
	}

	@Override
	public int deptInsert(DepartmentsVO deptVO) {
		int result = deptMapper.insertDeptInfo(deptVO);
		return result == 1 ? deptVO.getDepartmentId() : -1;
	}

	@Override
	public Map<String, Object> deptUpdate(DepartmentsVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = deptMapper.updateDeptInfo(deptVO.getDepartmentId(), deptVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", deptVO);
		
		return map;
	}

	@Override
	public Map<String, Object> deptDelete(DepartmentsVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		
		int result =  deptMapper.deleteDeptInfo(deptVO.getDepartmentId());
		
		if(result == 1) {
			map.put("departmentId", deptVO.getDepartmentId());
		}
		return map;
	}

}
