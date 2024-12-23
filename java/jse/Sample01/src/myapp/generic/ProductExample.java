package org.zerock.myapp.generic;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class ProductExample {

	// 목표: Generic Type Class 인 Product<K, M> 를 사용해서,
	//           우리가 지정한 구체타입에 맞는 제품객체를 생성하자!
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
//		Product<Car, String> product1 = new Product<Car, String>();		// @Until 7
		Product<Car, String> product1 = new Product<>();						// @Since 8
		product1.setKind(new Car());
		product1.setModel("Aveo");
		
		log.info("\t1. product1: {}", product1);
		
		log.info("-".repeat(30));
		
		Product<Television, String> product2 = new Product<>();
		product2.setKind(new Television());
		product2.setModel("LG OLED Wide UHD 52inch");
		
		log.info("\t2. product2: {}", product2);

	} // main

} // end class
