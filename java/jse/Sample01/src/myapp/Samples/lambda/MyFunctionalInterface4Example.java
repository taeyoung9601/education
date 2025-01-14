package org.zerock.myapp.lambda;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MyFunctionalInterface4Example {

	// 람다식 중괄호 블록 내에서, 과연 this 키워드는 어떤 객체의 주소를
	// 가지게 될까?
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
//		MyFunctionalInterface4 fi4 = new MyFunctionalInterface4() {
//			
//			@Override
//			public void method() {
//				log.debug("method() invoked.");
//				log.info("\t + this : {}", this);
//				
//			}// method
//			
//		};
//		
//		log.info("\t + fi4 : {}" ,fi4); // 익명구현객체 저장 참조변수 출력
//		fi4.method();					// 재 정의한 메소드 호출 (익명구현객체의)
//		
//		//---2---------
//		// 람다식
//		
//		fi4 = () -> {
//			log.debug("method() invoked.");
//			log.info("\t + this : {}", this);	//람다식이 구현된 메소드가 main인 정적메소드이면
//												// this 키워드 사용불가(이유 : Cannot use this in a static context)
//		};
//		
//		log.info("\t + fi4 : {}", fi4);			// 익명구현객체 저장 참조변수 출력
//		fi4.method();							// 재 정의한 메소드 호출 (익명구현객체의)
		
		
		//--3-----------
		// 실행클래스의 객체를 만들고, 아래 instanceMethod 메소드 호출
		
		MyFunctionalInterface4Example example = new MyFunctionalInterface4Example(); 
		log.info("\t + example: {}", example);
		
		example.instanceMethod();
	}// main
	
	public void instanceMethod() {
		log.debug("instanceMethod() invoked.");
		
		//여기서 람다식 구현 및 호출
		MyFunctionalInterface4 fi4 = () -> {
			log.debug("method() invoked.");
			// 우리가 알고있는 그 this 가 아니다!
			// 람다식 중괄호블록(즉, 추상메소드의 구현블록) 내에서
			// this 는 더이상, 이 람다식이 구현된 "익명구현객체"의 주소가 아니고
			// 오히려, 이 람다식이 구현된 메소드를 가지고 있는 바깥쪽 객체의
			// 주소(지금 같은 경우는, 실행 클래스 타입의 객체의 주소)를 가지고 있습니다.
			log.info("\t + this: {}" , this);
		};
		
		log.info("\t + fi4 : {}", fi4);
		fi4.method();
	}
		
	
	
}// end class
