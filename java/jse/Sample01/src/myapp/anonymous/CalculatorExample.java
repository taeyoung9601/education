package org.zerock.myapp.anonymous;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2

public class CalculatorExample {
	
	
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		iCalculator calc = new iCalculator() {
			
			// 1. 이 익명 구현 클래스 중괄호 블럭 내에서도,
			//	 일반적인 클래스와 동일하게 필드를 선언하고,
			//	 아래 메소드에서 사용가능한가?
//			The method dou(int, int) is undefined for the type iCalculator
			
			// 2. 일반 클래스처럼, "생성자" 선언이 가능한가?
			//	  우리는 참조변수를 출력해보아서, 익명구현클래스 이름이
			//	  어떻게 만들어지는지 아니까, 선언할 수 있지 않을까요?
			//	  그러면, 위1에 선언된 필드도 초기화가 가능하지 않을까요? 가능
			
			// 3. 정적 블록(Static Initializer)도 똑같이 선언하고 사용가능할까요?

			// 4. 인터페이스의 추상메소드 오버라이딩 말고도,
			// 	  다른 인스턴스/ 정적 메소드도 선언 가능하지 않을까요?
			// 	  그렇다면, 아래 코드의 참조 변수인 calc1.메소드명으로
			// 	  사용가능하지 않을까요? 위 1의 필드도 선언이 가능하다면
			// 	  동일하게 calc1. 필드명으로 사용할 수 있지 않을까요?
//			Cannot make a static reference to the non-static field calculatorexample3
			
			
			//----------------1-------------
			int number1 = 100;
			int number2 = 20;
			
		
			public int dou(int number1, int number2) {
				log.debug("곱하기({},{}) invoked.", number1, number2);
				return number1 * number2;
			}
			//-----------------------------------
			
			@Override
			public int add(int number1, int number2) {
				//:: - C++ 에서, "객체 소속 멤버" 연산자
				log.debug("iCalculator::add({},{}) invoked.", number1, number2);
				return number1 + number2;
			}

			@Override
			public int sub(int number1, int number2) {
				log.debug("iCalculator::sub({},{}) invoked.", number1, number2);
				return number1 - number2;
			}
			
			//-------------2----------------
			CalculatorExample calculatorexample = new CalculatorExample();
			
			public void calculatorexample(int number1, int number2) {
				this.number1 = number1;
				this.number2 = number2;
			}
			//------------------------------
			
			//--------------3----------------			
			static  {
				int number3 = 1;
				
			}
			//---------------------------------------
			CalculatorExample calculatorexample3 = new CalculatorExample();
			
			static void calculatorexample3() {
				calculatorexample3.number1 = number1;
				calculatorexample3.number2 = number2;
			}
			//----------------4---------------------
			
			
			
			
			
		}; // 익명 구현 객체(Anonymous Implementation Object)
		
		log.info("\t+calc:{}",calc);
		log.info("\t\t -add :{}",calc.add(100, 200));
		log.info("\t\t -sub :{}",calc.sub(100, 200));
		log.info("\t\t -a :{}",calc.dou(100, 200));
		
		

	} // main

}// end class
