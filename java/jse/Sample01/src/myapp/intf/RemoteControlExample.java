package org.zerock.myapp.intf;

import java.util.Arrays;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@Log4j2

public class RemoteControlExample {

	// 목표: 인터페이스에 대한 구현 클래스 작성법 이해 &
	// 		 인터페이스 기반의 다형성 구현(****)
	public static void main(String[] args) {
		log.debug("main({}) invoked.",Arrays.toString(args));
		
		RemoteControl rc;  // 부모 (=인터페이스) 타입의 변수
		
		// 다형성-1 : 부모(=조상까지 포함해서) 타입의 참조 변수에는,
		//			  모든 자식(= 맨 아래 후손까지 포함해서) 타입의 객체가 대입 가능한 성질
		rc = new TvRemoteControl();		// 다형성-1
		log.info("\t1. rc:{}", rc);
		
		// 다형성-2 : 부모타입에 선언된 메소드 호출 시, 다형성-1 상태로, 
		//			부모타입참조변수에 저장되어 있는 자식 객체가
		//			호출된 부모타입의 메소드를 재정의(overriding)하고 있다면,
		//			무조건 재정의된 메소드가 호출되는 성질 => 이를 통해서,
		//			다형성-1로 어떤 자식 객체가 대입되었느냐에 따라, 같은 메소드 호출이라도
		//			각 자식객체별로 다른 기능(형태)가 나오는 성질 => 바로, 다형성
		rc.turnOn();     	//다형성-2
		rc.setVolume(7); 	//다형성-2
		rc.turnOff();		//다형성-2
		
		
		rc = new AudioRemoteControl();	// 다형성-1
		log.info("\t2. rc:{}", rc);
		rc.turnOn();     	//다형성-2
		rc.setVolume(7); 	//다형성-2
		rc.turnOff();		//다형성-2
		
		
	} // main

}// end class
