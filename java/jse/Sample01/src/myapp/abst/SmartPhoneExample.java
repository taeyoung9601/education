package org.zerock.myapp.abst;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;


@Log4j2

public class SmartPhoneExample {

	//목표: 추상클래스에 특징을 경험하고 확인하자!
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));

		//1. 추상클래스로부터, 인스턴스 생성
//		Error: Cannot instantiate the type AbstractClass
//		AbstractClass obj = new AbstractClass();  // XX: 인스턴스 생성안됨
		
		//2. 자식 클래스에서 객체 생성
		SmartPhone smartPhone = new SmartPhone("Tae");
		log.info("\t+smartPhone:{}",smartPhone);
		
		smartPhone.turnOn();    // 상속받은 메소드
		smartPhone.turnOff();	// 상속받은 메소드
		smartPhone.webSearch();	// 원래 자식 메소드
		
		
		
		
	}// main

}//end class
