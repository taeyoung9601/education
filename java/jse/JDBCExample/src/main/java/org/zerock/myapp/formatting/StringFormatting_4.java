package org.zerock.myapp.formatting;

import java.text.MessageFormat;
import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class StringFormatting_4 {

	//목표 : 문자열 포맷팅 방법 -4번째
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));

		//@Since 15, Text Blocks - 여기서, Text Block 이란 '다중행 문자열', with """lines""".
		String template = """
				1. name : %s,
				2. age : %d,
				3. weight : %f
				""";
		
		String name = "Tae";
		int age = 26;
		double weight = 65.1;
		
		String formattedStr = String.format(template, name,age,weight);
		
		log.info(formattedStr);
		System.out.println(formattedStr);
		
		
	}//main

}//end class
