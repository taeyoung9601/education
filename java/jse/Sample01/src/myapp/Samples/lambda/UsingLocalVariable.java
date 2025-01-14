package org.zerock.myapp.lambda;

import java.util.Arrays;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class UsingLocalVariable {

	//메소드의 매개변수들은 메소드 블록 내에서 선언된 지역변수로 간주된다!
	MyFunctionalInterface5 instanceMethod(final String name) {
		log.debug("instanceMethod() invoked.");
		
//		name = "trinity"; // 수정불가 ->
		
		// 지역변수 ( Local Variable)는 메소드 블록이 끝나면ㅡ 곧바로 파괴된다.
		final int age = 27;
//		age= 27; // xx :수정불가
		MyFunctionalInterface5 fi5 = () -> { 
			// 람다식의 중괄호 블럭 내에서 바깥쪽 메소드의 지역변수를 사용하는 순간
			// 클로저 란 문제가 발생한다 ( 왜? 지역변수와 람다식이 만든 익명구현객체의
			// 생명주기가 틀리기 때문에.. 더 쉽게하면, 지역변수는 금방 파괴되고 사라지는데,
			// 익명구현객체는 계속 힙에 살아있으니깐
	
			// 람다식에 의해, JVM Heap area 에 익명구현객체 생성 -> 생명주기가 지역변수와 다르다!
			// 그러면, 이 람다식에 의해 구현된(재정의된) 메소드가 다시 호출될 때에는,
			// 과연 아래쪽에 사용된 지역변수(이미 파괴되고 없는)의 값은 어떻게 취급될까?
			// 이렇게 생명주기가 틀린 객체의 메소드 내부에서, 생명주기가 짧은 지역 변수와의
			// 관계로 인해서 발생되는 문제를 "클로져(Closure)"라고 한다.
			log.debug("method() invoked.");
			// 바깥쪽 main 메소드의 지역변수 사용
//			log.info("\t + name: {} , age: {}" , name, age);	//Closure 
//			log.info("\t + name : {}, age: {}", "Tae", 23); 	//@Until 7			
			log.info("\t + name: {} , age: {}" , name, age); // Since 8 : 사용된 지역변수를 final 상수로 변경
			
			
		};// 익명구현 객체 -> 힙에 생성 -> 생명주기가 당연히 지역변수와 틀리다!
		
		fi5.method(); //익명함수호출
		
		return fi5;

	} // instanceMethod
	
}// end class
