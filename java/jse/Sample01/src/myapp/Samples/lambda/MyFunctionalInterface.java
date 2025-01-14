package org.zerock.myapp.lambda;

@FunctionalInterface
public interface MyFunctionalInterface {
	public static final double PI = 3.14159; 	// 1. 진리값(상수)
	
//	public abstract void abstractMethod();		// 2. 추상메소드
//	nvalid '@FunctionalInterface' annotation; MyFunctionalInterface is not a functional interface
//	public abstract void abstractMethod2(); 	
	public abstract int add(int number1, int number2);
	
	public default void defaultMethod() {		// 3. 디폴트메소드
		;;
	}
	
	public static void staticMethod() {			// 4. 정적 메소드
		;;
	}
	
	// 위에 선언된 디폴트 인스턴스 메소드의 모듈화를 위해 도입
	private void privateInstanceMethod() {		// 5-1. private 인스턴스 메소드
		;;
	}
	
	// 위에 선언된 정적 메소드의 모듈화를 위해 도입
	private static void privateStaticMethod() {	// 5-2. private 정적 메소드
		;;
	}
	
}// interface
