package org.zerock.myapp.annotation;

@MyAnnotation2

// 우리가 직접 만든 어노테이션
// 아직은 아래와 같이, 어노테이션 중괄호 블록에
// 아무런 멤버도 없지만, 오히려, 상당수의 어노테이션이
// 아래와 같이 만들어 집니다.

// 어노테이션을 붙일 때에는 중간해 빈행이 아무리 많이 있어도 적용됨
//@MyAnnotation(value="", number=100) // 표준방법
//@MyAnnotation(value="Tae")// T17 : OK//@MyAnnotation									// T17 : OK
@MyAnnotation("Tae")  // OK: 생략문법은 항상 기본 속성인 'value'에 한해서
//@MyAnnotation public 							// T17-1: OK
public @interface /* @MyAnnotation - XX : 부부싸움 */ MyAnnotation {	// 어노테이션명: @MyAnnotation

	//속성 (element)선언
	// (1) 필수 속성
	// (2) 선택 속성
	// default 구문의 유무에 따라, 필수/선택이 됩니다.
	
	String value() default ""; // 기본속성: 왜? 이름이 value인 속성을 기본 속성
	int number() default 15;
	
	
} // end annotation
