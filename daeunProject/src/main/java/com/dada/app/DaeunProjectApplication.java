package com.dada.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.dada.app.**.mapper")
public class DaeunProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaeunProjectApplication.class, args);
	}

}
