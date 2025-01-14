package org.zerock.myapp.lambda;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MyFunctionalInterfaceExampleWithLambda {

	//목표 : 같은 함수적 인터페이스 ( 즉, 람다식의 타켓타입)을 이전 실행클래스에서는
	// 		 "익명 구현 객체 코딩 기법"으로 자식 구현 객체(익명 구현 객체)를 생성했었다면,
	//		 이번에는 같은 목표(익명 구현 객체)를 "람다 표현식 (Lambda Expression)" 으로 생성
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		// Step1. 람다식의 타겟타입이 무엇인지 식별하라!
		// Step2. 람다식의 타겟타입(즉, 함수적 인터페이스)에 선언된 단 1개의 추상 메소드를
		// 		  그대로 가져와서, 아래 행의 주석으로 넣어놓아라!
		// Step3. Step2에서 가져온 추상메소드 시그니쳐에서, public abstract을 떼어내라
		// Step4. Step3의 결과에서, 추상메소드의 이름/매개변수선언부/리턴타입을 식별하라!
		// Step5. Step4의 결과에서, 추상메소드의 이름을 삭제하라!
		//		  추상메소드에서 남는 것은, (1) 리턴타입 (2) 매개변수 선언부 ('()')만 남음
		// --람다식 구현 시작--
		// Step6. Step5에서 남은 것 중에, (2)매개변수 선언부를 그대로 화살표기호 (->) 왼쪽에 배치
		// Step7. Step6의 화살표 기호(->) 오른쪽에는 메소드 블록({}) 배치하라!
		// -- 아무것도 안하는 가장 간단한 람다식 구현 끝 --
		
		//public void abstractMethod();   				// Step2
		// => void abstractMethod() 	  				// Step3,4
		// =>  void ();					 				// Step5 : 람다식으로 구현할 때, 추상메소드 이름은 전혀 사용안함.
//		 MyFunctionalInterface mfi = () -> {			// Step6,7 람다식의 골격: <매개변수선언부> -> <메소드 중괄호 블록>
//			log.debug("lambda expression invoked.");
//		 };  		
//		log.info("\t + mfi : {} ", mfi);
//		+ mfi : org.zerock.myapp.lambda.MyFunctionalInterfaceExampleWithLambda$$Lambda/0x0000019e9f100000@293a5bf6 
//										-------	(1) 실행 클래스----------      -------(2) 익명함수타입----
		
//		mfi.abstractMethod();	// 추상 메소드 호출
		
		MyFunctionalInterface mfi = (int number1, int number2) -> {
			log.debug("add({},{}) invoked.", number1, number2);
			return number1 + number2;
		}; // 추상메서드를 재정의하는 문법, 람다식으로 표현
		
		int result = mfi.add(100, 200);
		
		log.info("\t + result : {}", result);
		
	}// main

}// end class
