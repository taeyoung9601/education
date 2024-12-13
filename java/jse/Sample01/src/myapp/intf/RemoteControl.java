package org.zerock.myapp.intf;

import org.zerock.myapp.libs.Utils;

//import lombok.extern.log4j.Log4j2;

// 주의: lombok 의 어노테이션은, 클래스 선언할 때에만 가능합니다.
//		 인터페이스 선언시에는 사용할 수 없습니다.

//Error: Log4j2 is legal only on classes and enums.
//@Log4j2 // XX

public interface RemoteControl {
	
	// 1. 진리 값(static final) 상수 선언
	//    여러분이 아래 MAX_VOLUME과 같이,
	//	  타입 앞에 public static final 이란 3개의 키워드를 생략 가능합니다.
	//	  하지만, 이 3개 키워드를 생략하시면, 자바 컴파일러가
	//    개입해서, 컴파일 타임시에 3개 키워드를 강제로 다시 넣어버립니다.
	
	public static final int MIN_VOLUME = 0;  
//	static final int MAX_VOLUME = 10; 
//	final int MAX_VOLUM = 10;
	int MAX_VOLUME = 10;   
	
	//--------------------
	// 2. 추상 메소드-> 어떤 회사가 만들든, 공통으로 반드시 지켜야 할 객체의 규격을 정하는 것
	//    => 객체가 가지고 있어야 할 "규격"을 정하는 것이다!
	// public abstract 2개의 키워드 생략 가능 -> 하지만 절대 생략하지마라!
	//--------------------
	public abstract void turnOn();   // 규격1 - 강제할 수 있도록 추상 메소드로 선언
	void turnOff();					 // 규격2
	void setVolume(int volume);		 // 규격3
	
	//-------------------
	// 3. 기본 인스턴스 메소드
	// ------------------
	//객체의 강제 규격을 정하는 인터페이스에, 갑자기 쌩뚱맞게 객체없이는 사용할 수 없는
	// 인스턴스 메소드가 신규 멤버로 선언 가능 (@since 8)
//	public  // ok
// 	default // ok
//	private // xx :Illegal combination of modifiers for the private interface method setMute;
//  protected // xx 
	// 			   additionally only one of static and strictfp is permitted
	default void setMute(boolean isMute) {  // 음소거 기능 수행하는 인스턴스 메소드
		Utils.formatToConsole("setMute({0}) invoked.", isMute);
		
		var output = (isMute)? "Muted" : "Un-muted";
		Utils.printToConsole(output);
	}
	
	//----------------
	// 4. 정적 메소드 선언 가능
	//----------------
	//다시 한번 말씀드리자면, 인터페이스는 모델링한 타겟 객체의 강제 규격을 정하는게
	// 원래의 목적입니다. 그런 목적에 맞지 않는 위의 default 메소드와 더불어, 아래의 정적 메소드까지
	// 선언 가능하게 되었다.
	
	
	//	public  // ok
	// 	default // ok
	//	private // ok
	//  protected // xx 
	public static void changeBattery() {
		Utils.printToConsole("changeBattery() invoked.");
		
	}
	
	//----------------
	// 5. private 인스턴스 메소드 선언 가능 (@since 9)
	//----------------
	//	public  // xx
	// 	default // xx
	//	private // ok 
	//  protected // xx 
	@SuppressWarnings("unused")
	private static void privateMethod() {
		Utils.printToConsole("privateMethod() invoked.");
	}
	
	//----------------
	// 6. private static 정적 메소드 선언 가능 (@since 9)

	private static void privateStaticMethod() {
		Utils.printToConsole("privateStaticMethod() invoked.");
	}
	
	
	

}//end interface
