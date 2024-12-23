package org.zerock.myapp.formatting;

import java.text.MessageFormat;
import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class StringFormatting_3 {

	//목표 : 문자열 포맷팅 방법 -3번째
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		//MessageFormat.format
//		MessageFormat.format(template, Object...args);
//		이 정적 메소드에 사용되는 포멧팅 기호는, 이전과 틀리게, lombok @Log4j2 와 비슷
//		포맷팅 기호: {index} - index: 0부터 시작
		
		String name = "Tae";
		int age = 23;
		double height = 171.2;
		
		String formattedStr = 
				MessageFormat.format("name: {0}, age:{1}, height:{2} " , name,age,height);
		
		log.info(formattedStr);
		
	}//main

}//end class
