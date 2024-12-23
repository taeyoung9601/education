package org.zerock.myapp.generic;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;


@Log4j2

public class UtilBoxingExample {

	// 목표: 제네릭 메소드의 사용법을 알자!!!
	public static void main(String... args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		// Util 클래스의 정적메소드(제네릭 메소드) 사용(=호출)
		Box<Apple> appleBox = Util.<Apple>boxing(new Apple());		// @Until 7
//		Box<Apple> appleBox = Util.boxing(new Apple());		// @Since 8 -> 생략하지 말라!!
		
		log.info("\t+ appleBox: {}", appleBox);
		log.info("\t+ get: {}", appleBox.get());
	} // main

} // end class
