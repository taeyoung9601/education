package org.zerock.myapp.exception;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class NumberFormatExample {

	// 목표: 4번째로 가장 많이 발생하는 Runtime 예외로,
	//       NumberFormatException 이 의미하는 바와 어떤 이유로 발생하는지를
	//       이해한다.
	// 의미: 숫자형태의 문자열 => 진짜 숫자로 변환할 때 발생하는 예외
	// 발생원인: 문자열 데이터 안에, 변환하고자 하는 타입에 맞지 않는
	//           노이즈 문자가 끼어있을때
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));

		// 모든 각각의 기본타입별 Wrapper Type Class 에는
		// 문자열데이터를 해당하는 기본타입으로 변환해주는 2가지의 메소드가
		// 공통으로 들어있다.
		//        (1) WrapperType.valueOf(String) returns a WrapperType Object.
		//        (2) WrapperType.parseXXX(String) returns a primitive type value.
		//            (Where, XXX is the name of a primitive type)
		
		double doubleValue = Double.parseDouble(args[0]);
		log.info("\t+ doubleValue: {}", doubleValue);
//		log.info("\t1. Int: {}", firstNumber);
//		log.info("\t2. Float: {}", Float.parseFloat("3.14"));
//		log.info("\t3. Double: {}", Double.parseDouble("3.14a"));
//		log.info("\t4. Boolean: {}", Boolean.parseBoolean("true"));
//		log.info("\t5. Long: {}", Long.parseLong("3"));
	}
	
}
