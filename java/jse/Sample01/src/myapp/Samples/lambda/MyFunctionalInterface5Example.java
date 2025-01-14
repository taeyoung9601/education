package org.zerock.myapp.lambda;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class MyFunctionalInterface5Example {

	//목표 : "클로져" 문제에 대한 이해와 이 문제를 자바언어는 어떻게 해결하고 있는지를 정확히 알자!
	public static void main(String[] args) {
		
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		UsingLocalVariable ulv = new UsingLocalVariable();
		MyFunctionalInterface5 fi5 = ulv.instanceMethod("Tae");
		
		// 다시 한번 람다식의 익명구현객체의 재 정의된 메소드 호출
		// 이 때에는 더 이상 아래 메소드 내에서 사용된 지역변수가 파괴도ㅣ고
		// 없는 상태임 -> 그럼, 지역변수는 어떻게 출력하지? (클로져 문제)
		fi5.method();
		
	}// main

}// end class
