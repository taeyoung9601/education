package org.zerock.myapp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//내가 선언한 어노테이션을 참조 타입의 어디에만 붙일 수 있는지를 제약하려면,
// 아래와 같이, 표준 라이브러리가 제공하는 @Target 어노테이션을 붙이셔야 합니다.
@Target(value= {
		ElementType.FIELD,
		ElementType.CONSTRUCTOR,
//		ElementType.TYPE,  				//모든 참조 타입 선언부에 붙이게 함
										// 하지만, 모든 참조타입이 아니라, 어노테이션 타입선언부에만
										// 붙일 수 있도록 제약할 때에는 ANNOTATION_TYPE 열거 상수만 지정하셔야 합니다.
		ElementType.ANNOTATION_TYPE,
		ElementType.LOCAL_VARIABLE,  // 지역변수에 붙일 수 있다.
		ElementType.PARAMETER,  		// 모든 매개변수에 붙일 수 있다!(생성자/메소드 등)
		ElementType.METHOD,				//
})  // ok : 정석

// 내가 만든 어노테이션이 언제까지 유지될 것인지를 정하자!(Retention Policy)
// 수단: 자바 표준 라이브러리가 제공하는 역시 어노테이션으로 @Retention 을
//		 이용하여 내가 만든 어노테이션의 


@Retention (RetentionPolicy.RUNTIME)  // 90%이상 대두분의 어노테이션 설정 값


//어노테이션은 아래와 같이 가로로 배열해서 붙여도 허용됩니다.
@MyAnnotation2 @MyAnnotation

public @interface MyAnnotation2 {

	// Illegal modifier for the annotation attribute MyAnnotation
	// only public & abstract are permitted.
	
	
	//public    // ok
	//default  // ok
	//private  // xx
	// protected // xx
	String value() default "";
	
	public int number() default 15;
	String[] hobby() default {};
	
}
