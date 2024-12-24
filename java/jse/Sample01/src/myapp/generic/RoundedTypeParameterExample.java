package org.zerock.myapp.generic;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2

public class RoundedTypeParameterExample {

	//목표: 구체 타입의 범위가 제약된 제네릭 메소드 사용을 통해
	//		extends 키워드의 의미를 알자!
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));

		// Case1 - 두 정수 비교
		int result1 = Util.<Integer>compare(10,20);
		log.info("\t+result1:{}", result1);
		
		// Case2 - 두 실수 비교
		int result2 = Util.<Double>compare(10.4,20.8);
		log.info("\t+result2:{}", result2);
				
		
	}

}
