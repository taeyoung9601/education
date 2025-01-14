package org.zerock.myapp.lambda;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MyFunctionalInterfaceExampleWithLambda2 {


	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		//생략 문법- 최대한 간결한 코드를 만드는 것이 람다식의 "최대 목적" 이다!
		// (1) 매개변수 선언부의 각 매개변수의 타입은 무조건 생략 가능하다.
		//     이 때, 매개변수 이름은 관례상 최대한 짧게 재명합니다.
		// (2) 메소드 중괄호 블록안에 실행문장이 단 1개라면, 중괄호 기호도 생략가능함
		// (3) 생략문법2에서, 1개의 실행문장이 return문 이라면, 반드시 return 키워드도 생략해야한다.
		// (4) 매개변수 선언부의 매개변수의 개수가 단 1개이면, 소괄호 기호도 생략
		// (메소드 참조) 람다식의 끝판왕이라 불리는, "Method Reference( 메소드 참조, 예 : log::info)
		//				 이것은 람다식이 아닌, 다른 문법입니다.

//		MyFunctionalInterface2 mfi = (int number1, int number2) -> {
//			log.debug("lambda");
//			return number1 - number2;
//		};
//	
//		int result = mfi.sub(200,100);
//		log.debug("\t + result : {} ", result);
		//--------------------------sub--------------------------------
//		MyFunctionalInterface2 mfi = (double number1, double number2) -> {
//			log.debug("mul");
//			return number1 * number2;
//		};
//		
//		double result = mfi.mul(1.4, 3);
//		log.debug("\t + result : {} ", result);
		//----------------------------mul--------------------------------
//		MyFunctionalInterface2 mfi = (double number1, double number2) ->{
//			log.debug("div");
//			return number1 / number2;
//		};
//		
//		double result = mfi.div(100., 10.)
//		log.debug("\t + result : {} ", result);
		//-----------------------------div-------------------------------
//		MyFunctionalInterface2 mfi = (int number1, int number2) -> {}
		MyFunctionalInterface2 mfi = (n1, n2) ->  n1 % n2; // 실행문장이 하나일대 중괄호 기호생략가능 - return 키워드도 생략
	
		
		int result = mfi.mod(4,3);
		log.debug("\t + result : {} ", result);
		
		//-----------------------------mod----------------------------------
		
		
	}// main

}// end class
