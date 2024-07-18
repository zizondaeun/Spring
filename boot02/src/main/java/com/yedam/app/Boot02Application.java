package com.yedam.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //@SpringBootConfiguration /@EnableAutoConfiguration자동설정 / @ComponentScan
@MapperScan(basePackages = "com.yedam.app.**.mapper") //db다루는 애,꼭 해주기,기능 상관없이 모든 mapper 스캔하겠다.
public class Boot02Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot02Application.class, args);
	}

}
