package org.zerock.myapp.formatting;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class StringFormatting_2 {

	//목표 : 문자열 포맷팅 방법 -2번째
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		//String.format() method 이용
//		String.format(Stirng format,Object ...args) : String
		
		String name = "Tae";
		int age = 26;
		double weight = 65.2;
		
		String formattedStr = String.format("Tae's name:%s,age:%d,weight:%f", name,age,weight);
		
		log.info(formattedStr);
		
	}//main

}//end class
