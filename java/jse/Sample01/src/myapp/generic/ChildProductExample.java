package org.zerock.myapp.generic;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2

public class ChildProductExample {
	
	//목표 : 제네릭 클래스를 상속받은 자식 제네릭클래스의 사용 방법을 통해
	//		 제네릭 타입의 상속 방법을 알자!
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		ChildProduct<Television,String,String>product = new ChildProduct<>();
		product.setKind(new Television());
		product.setModel("SAMSUNG Smart TV 52 inch");
		product.setCompany("SAMSUNG");
		
		log.info("\t + product:{}", product);
		
	}// main
	
}// end class
