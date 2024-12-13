package org.zerock.myapp.intf;

import org.zerock.myapp.libs.Utils;

//@FunctionalInterface - 추상 메소드가 단 1개인 인터페이스를 컴파일러가 확인
//						 인터페이스의 나머지 5개 멤버는 있든/없든 안따짐
// 이렇게 추상 메소드가 단 1개인 인터페이스를 ,"Functional Interface"라고 하고
// 이 인터페이스가 람다식의 Target Type 이 됩니다.

@FunctionalInterface
public interface Vehicle { // 모든 차량의 종류를 의미하는 부모타입
	
	//상수명 규칙: PASCAL 표기법을 따름(모든 단어는 대문자, 복합단어는 '_'로 연결
	public static final String MODEL = "전기자동차"; // 진리 상수(임의대로 선언)
	public static final double MAX_SPEED = 220;  //버스/택시 가릴거 없이 모든 차량의 최고 속도
	
	
	public abstract void run(); // 강제구격
	
	
	public default void defaultMethod() {  // 기본 인스턴스 메소드
		// 객체없이는 사용불가 - 인스턴스 메소드이니까.
		Utils.printToConsole("defaultMethod() invoked.");
		
		//(대전제): 인스턴스 멤버는 다른 인스턴스 멤버 + 다른 정적 멤버 모두 사용가능

		Vehicle.privateInstanceMethod();  // private 인스턴스 메소드 호출
	}
	//인터페이스에 private 인스턴스 메소드가 나온 이유는, 바로 위의 다른 멤버인
	// default 인스턴스 메소드 구현시, 구현 로직이 복잡한 경우, 아래의 private 인스턴스 메소드로
	// 나누어서 구현할 수 있도록 추가된 것입니다. 그전에는 아무리 복잡한 로직이서
	// 실행문장이 수백줄이 되어도 그냥 통짜로 구현했어야 했다. 하지만, 이제
	// 아래와 같이 인터페이스 내에서도 private 정적 메소드 선언이 가능해짐에 따라,
	// 수백줄 코드를 여러 개의 아래 단위 메소드로 구현할 수 있게 되었습니다.
	
	private static void privateInstanceMethod() {
		Utils.printToConsole("privateInstanceMethod() invoked.");
	}
	
	public static void staticMethod() {
		Utils.printToConsole("staticMethod() invoked.");
		
		Vehicle.privateStaticMethod();
	}
	
	
	//인터페이스에 private 정적 메소드가 나온 이유는, 바로 위의 다른 멤버인
	// 정적 메소드 구현시, 구현 로직이 복잡한 경우, 아래의 private 정적메소드로
	// 나누어서 구현할 수 있도록 추가된 것입니다. 그전에는 아무리 복잡한 로직이서
	// 실행문장이 수백줄이 되어도 그냥 통짜로 구현했어야 했다. 하지만, 이제
	// 아래와 같이 인터페이스 내에서도 private 정적 메소드 선언이 가능해짐에 따라,
	// 수백줄 코드를 여러 개의 아래 단위 메소드로 구현할 수 있게 되었습니다.
	private static void privateStaticMethod() {
		Utils.printToConsole("privateStaticMethod() invoked.");
	}
	
	
//	this.privateInstanceMethod();
}// end interface
