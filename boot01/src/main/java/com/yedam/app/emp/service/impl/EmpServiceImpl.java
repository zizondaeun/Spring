package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Service //비지니스 로직 담당 /AOP가 적용될 유일한 Bean /트랜젝션 제어
public class EmpServiceImpl implements EmpService{
	//테이블이 필요해서 접근
	@Autowired //3.필드 주입 방식(1.setter/2.생성자/3.필드)
	private EmpMapper empMapper; //강제성 주입(권하는 방식이 아님)
		
	@Override
	public List<EmpVO> empList() {
		return empMapper.selectEmpAll();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		return result == 1 ? empVO.getEmpid() : -1;
	}

	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) { //재활용할 필요없을때 map을 이용함
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = empMapper.updateEmpInfo(empVO.getEmpid(), empVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", empVO);
		
		/*
		 * {
		 * 		"result" : true,
		 * 		"target" : {
		 * 					
		 * 					}
		 * }
		 * */
		
		return map; //map타입은 map을 리턴함. 맵을 리턴 먼저해두기!
	}

	@Override
	public Map<String, Object> empDelete(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		
		int result = empMapper.deleteEmpInfo(empVO.getEmpid());
		
		if(result == 1) {
			map.put("empid", empVO.getEmpid());
		}
		return map;
	}
	
}
