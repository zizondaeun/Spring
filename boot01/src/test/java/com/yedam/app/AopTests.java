package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yedam.app.aaa.service.AaaService;

@SpringBootTest
public class AopTests {
	//@Autowired
	AaaService aaaService;
	
	//@Test
	public void transactional() {
		aaaService.insert();
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void testEncoder() {
		String password = "1234"; //사용자가 입력
		
		//DB에 저장된 암호화된 비밀번호
		String enPwd = passwordEncoder.encode(password); //encode가 중요
		System.out.println("enPwd : " + enPwd);
		
		boolean matchResult = passwordEncoder.matches(password, enPwd); //matches는 1234랑 인코딩된 암호화랑 같은지 매치하는거/enPwd는 암호화되어있음
		System.out.println("match : " + matchResult);
	}
}
