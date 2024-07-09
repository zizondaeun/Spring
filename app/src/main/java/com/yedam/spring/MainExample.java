package com.yedam.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExample {
	
	public static void main(String[] args) {
		ApplicationContext ctx //즉시로딩
		= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		TV tv = (TV) ctx.getBean("tv"); //new 연산자 없이 클래스 생성
		tv.turnOn();
		
		TV subTv = (TV) ctx.getBean(TV.class); //TV.class는 클래스 자체를 불러온거
		subTv.turnOn();
		
		if(tv == subTv) { //둘은 같은 객체를 참조하고 있음
			System.out.println("같은 인스턴스 입니다.");
		}else {
			System.out.println("다른 인스턴스 입니다.");
		}
	}
}
