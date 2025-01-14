package org.zerock.myapp.lambda;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MyFunctionalInterfaceExample {
	
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		// 함수적 인터페이스를 " 익명 구현 객체 코딩" 기법으로 구현해보자
		// 즉, 별도의 구현 클래스를 만들지 않고도, 자바 컴파일러로 하여금
		// "익명의 구현 클래스"를 만들게 하고, 이 익명 클래스로부터 객체를
		// 생성하여, "익명 구현 객체"를 만들어, 참조타입 변수에 대입해보자!
		// 그러면, 구현된 추상메소드 호출이 가능하겠죠?
		// 즉, 다형성-1,2 모두 적용
		
		MyFunctionalInterface mfi1 = new MyFunctionalInterface() { 

			@Override
			public void abstractMethod() {	// 함수적 인터페이스 (부모 타입)로부터 상속받은 추상메소드(단 1개)를 재정의해야함
				log.debug("abstractMethod() invoked.");
			}
		};
		
		mfi1.abstractMethod();

		
		log.info("\t + mfi1 : {} ", mfi1);
		
		//---------------------------------------------
		
		MyFunctionalInterface mfi2 = new MyFunctionalInterface() {

			@Override
			public void abstractMethod() {
				log.debug("abstractMethod() invoked.");
				
			}

		
		};
		
		mfi2.abstractMethod();

		log.info("\t + mfi2 : {} ", mfi2);
		
		
	}// main
	
}// end class
