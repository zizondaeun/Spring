package com.yedam.spring.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExample { //1번째
	public static void main(String[] args) {
		ApplicationContext ctx //즉시로딩
		= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		Restaurant res = (Restaurant) ctx.getBean(Restaurant.class);
//		Chef chef = new Chef();
//		res.setChef(chef);
		res.run();
	}
}
