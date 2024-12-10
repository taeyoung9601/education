package org.zerock.myapp.libs;


import lombok.NoArgsConstructor;



@NoArgsConstructor
public class Calculator {
	//int result; // 인스턴스 필드: 연산 결과를 저장하는 필드
		

	//객체의 기능이 구현되는데에는, 대부분 필드에 저장된 데이터를 
	//사용하는 경우가 실제 대부분입니다. 하지만, 우리가 지금 만든
	//전자계산기는 필드의 필요성이 전혀 없죠.. 이럴 때에는
	//당연히 필드도 억지로 선언할 필요가 없고, 필드가 없으니 생성자도
	//선언할 필요가 없습니다. 하지만... 이 전자계산기 클래스로부터
	//전자계산기 객체가 만들어 질려면, 최소한 기본 생성자(즉, 매개변수가
	// 하나도 없는 생성자)는 있어야 하기 때문에, 현업은 lombok의 위
	// 어노테이션을 정말 많이 사용합니다.
	
	//메소드 문법
	// returnType methodName(parameters){...}
	
	//정수 사칙연산 메소드 선언
	void powerOn() {
		Utils.printToConsole("power()On invoked.");
	}

	void powerOff() {
		Utils.printToConsole("power()Off invoked.");
	}
	
	//정적 필드: 연산 결과를 저장하는 필드
	// 			 이 클래스에서 찍어낸 모든 객체 사이에서 공유되는 데이터를 저장하는 필드이다!
	static int result;
	
	
	//called, '정적 메소드(static method)' <-> '동적 메소드 (instance method)'
	// 정적 메소드 : 인스턴스 필드의 데이터가 필요없이, 
	//				 매개변수에 전달된 인자(전달인자,Arguments)만으로
	//				 메소드의 기능이 100% 구현되는 메소드
	static int add(int num1, int num2){
		Utils.printToConsole("add(num1,num2) invoked");
		return num1 + num2;
	}
	
	int minus(int num1, int num2) {
		Utils.printToConsole("minus(num1,num2) invoked.");
		return num1 - num2;
	}
	
	int 곱셈(int num1, int num2) {
		Utils.printToConsole("곱셈(num1,num2) invoked.");
		return num1 * num2 ;
	}
	
	int 나눗셈(int num1, int num2) {
		Utils.printToConsole("나눗셈(num1,num2) invoked.");
		return num1 / num2 ;
	}
	
	
}//end class
