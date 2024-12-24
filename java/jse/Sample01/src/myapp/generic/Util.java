package org.zerock.myapp.generic;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

public class Util {
	
	//If a type 
	
	public static <T>Box<T>boxing(T obj){
		log.debug("boxing({}) invoked.", obj);
		
		//제네릭 타입 클래스/인터페이스가 아래와 같이
		//제네릭 메소드에서 "사용되면","구체타입"을 지정하는게 아니라,
		//제네릭 메소드의 타입파라미터에 지정될 "구체타입"으로 자연스럽게
		//지정되도록 합니다.
		
		Box<T> box = new Box<>();
		box.set(obj);
		
		return box;
		
	} // boxing
	
	//두개의 매개변수에 전달된 인자 값 ( 전달 인자) 비교하는 메소드 선언
	// 타입 파라미터 T에 대소 비교가 가능한 참조 타입만 지정 가능하도록
	// 구체 타입의 범위를 제약할 필요가 있다 => "Bounded Type Parameter";
	public static <T extends Number>int compare(T t1, T t2) {
		log.debug("compare({},{}) invoked.", t1, t2);
			
		//자바 언어에서는, 어떤 표준 참조 타입에는, 지금 우리가 선언한 비교 메소드를
		// 요구 할 때가 있다. 이 때 두 숫자의 대소 비교 값을 아래와 같이 반환하도록
		// 표준으로도 정해져 있고, 관례상 특정 숫자를 반환하도록 되어있습니다:
		// *연산 : 기준 값 - 비교 값
		//
		// (1) 기준 값 == 비교 값 => 0을 반환
		// (2) 기준 값 < 비교 값 => -1을 반환(기준 값< 비교 값 의미)
		// (3) 기준 값 > 비교 값 => +1을 반환(기준 값 > 비교 값 의미)
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		//1st. method - 개발자가 직접 비교식과 결과 반환
//		if (v1 == v2 ) return 0;
//		else if ( v1 > v2 ) return +1;
//		else if ( v1 < v2 ) return -1;
		
		//2nd. method - 정수/실수 관련 모든 Wrapper Type Class 에는,
		//				이미 자바 표준에서 요구하는 비교 메소드가 구현되어 있습니다.
		return Double.compare(v1, v2);
		
	}// compare
	
	
	
	
} // end class
