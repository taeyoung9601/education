package org.zerock.myapp.annotation;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@MyAnnotation(number=100)
public class Person {
	// xx : 2개 이상의 속성에 데이터 전달 시에는 기본 속성(value)이라도 반드시 이름 기재
//	@MyAnnotation("Young", number=77)
	@MyAnnotation(value="Young", number=77)
	private String name;
	
	//마지막 배열의 원소 끝에 쉼표(,)를 찍어 놓는 것이 일반적입니다.
	//대신, 일반 배열의 문법상으로는 허용되지 않습니다.
	@MyAnnotation2(hobby={"족구","농구","배드민턴",})
//	@MyAnnotation2(hobby = {"족구"})
	private int age;
	
	@MyAnnotation2
	public Person(int age) {
		this.age = age;
		
		int localVariable = 10; // 지역 변수
	}
	
	@MyAnnotation2
	public void instanceMethod(int age) {
		;;
	}
	
	
}// end class
