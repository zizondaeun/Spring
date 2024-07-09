package com.yedam.spring.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig { //설정을 위한 클래스(설정 등록 여기서)
	//Bean 등록 -> 메소드 활용 (5.세터 인젝션 DI:Java 방식)
	
	@Bean
	public Chef chef() {
		return new Chef();
	}
	
	@Bean
	public Restaurant restaurant(Chef chef) {
		Restaurant res = new Restaurant();
		res.setChef(chef);
		return res;
	}
}
