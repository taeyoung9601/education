package org.zerock.myapp.formatting;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class StringFormatting_1 {

	//목표 : 문자열 포맷팅 방법 -1번째
	public static void main(String[] args) {
		log.debug("main({}) invoked.",Arrays.toString(args));
		
//		System.out.println(format,Object..args);
		String name = "Tae";
		int age = 23;
		System.out.printf("name: %s, age: %d", name,age);
	}//main

}//end class
