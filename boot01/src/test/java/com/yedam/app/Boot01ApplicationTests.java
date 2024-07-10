package com.yedam.app;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
class Boot01ApplicationTests {
	
	@Autowired
	EmpMapper empMapper;
	
	//@Test
	void contextLoads() {
		assertNotNull(empMapper);
	}
	
	//@Test
	void selectEmpAll() {
		//전체조회
		List<EmpVO> list = empMapper.selectEmpAll();
		assertTrue(!list.isEmpty()); //list 타입은 널인 경우가 없기때문에 list가 비었는지를 확인하면 됨 -> ! -> 값이 있다
	}
	
	//@Test
	void selectEmpInfo() {
		//단건조회
		EmpVO empVO = new EmpVO();
		empVO.setEmpid(115);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals(findVO.getEmpname(), "Alexander");
	}
	
	@Test
	void insertEmpInfo() {
		//등록
		EmpVO empVO = new EmpVO();
		empVO.setEmpname("Kim");
		empVO.setSal(1000);
		empVO.setDeptid(40);
		
		int result = empMapper.insertEmpInfo(empVO);
		//assertEquals(result, 1); //반환되는 값이 1이면 성공
		assertEquals(empVO.getEmpid().intValue(), 2);
	}
	
	//@Test
	void updateEmpInfo() {
		//수정 1.단건조회 => 2.업데이트
		//1-1) 조회 조건
		EmpVO empVO = new EmpVO();
		empVO.setEmpid(2);
		
		//1-2) 실제 조회
		EmpVO findVO = empMapper.selectEmpInfo(empVO);//1.여기까지 조회
		findVO.setEmpname("Kang");//2.여기부터 업데이트 / 가져오겠다(set)
		findVO.setMgr(114);
		
		//2.업데이트
		int result = empMapper.updateEmpInfo(findVO.getEmpid(), findVO);
		assertEquals(1, result);
	}
	
	//@Test
	void deleteEmpInfo() {
		//삭제
		int result = empMapper.deleteEmpInfo(2);
		assertEquals(1, result);
	}
}
