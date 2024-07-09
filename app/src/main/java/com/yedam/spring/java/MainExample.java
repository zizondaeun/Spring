package com.yedam.spring.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExample {
	public static void main(String[] args) {
		ApplicationContext ctx //즉시로딩
		= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		Restaurant res = (Restaurant) ctx.getBean(Restaurant.class);
		res.run();
	}
}
