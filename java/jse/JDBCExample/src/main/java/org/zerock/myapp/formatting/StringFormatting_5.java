package org.zerock.myapp.formatting;

import java.text.MessageFormat;
import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class StringFormatting_5 {

	//목표 : 문자열 포맷팅 방법 -5번째 (가장 추천)
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));

		//Using StringBuffer (Thread-safe) or StringBuilder (Thread-unsafe)
		// without any formatting symbols (ex : %s, %d, %f, {0}, {1} ...)
		String name = "Tae";
		int age = 26;
		double height = 171.2;
		
		String formattedStr =
				new StringBuffer()
					.append("1.name:")
					.append(name).append(",")
					.append("2. age:")
					.append(age).append(",")
					.append("3. height")
					.append(height).append(",")
					.toString();
		
		log.info(formattedStr);
		
	}//main

}//end class
