package org.zerock.myapp.intf;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2

public class DriverExample {

	public static void main(String[] args) {
		log.debug("main({}) invoked.",Arrays.toString(args));
		
		Driver driver = new Driver();
		
		driver.drive(new Bus());        // 다형성-1 & 다형성-2
		log.info("=".repeat(20));
		driver.drive(new Taxi());        // 다형성-1 & 다형성-2
		
		// ------여기서터는, 인터페이스 나머지 5개 멤버 사용을 통해
		// 		 인터페이스의 멤버를 어떻게 사용하는지 / 그리고 의미와 용도를 알자!
		
		//(1) 진리 상수 사용(public static final 상수)
		//(대전제) 정적 멤버는 정적 멤버답게 사용한다!
		log.info(Vehicle.MODEL); // 전역상수(=진리상수): 모든 객체에서 사용 가능
		log.info(Vehicle.MAX_SPEED);  // 전역상수(=진리상수)사용
		
		//(2) default 인스턴스 메소드 사용
//		Vehicle.defaultMethod();  // XX : 객체 없이 사용불가
		Bus bus = new Bus();  	  // 첫번째 방법: 자식 객체 생성
		bus.defaultMethod();  	  // OK : 마치 원래부터 자식 객체에 선언된 메소드처럼 사용가능
								  //      이게 바로 default 메소드의 사용법
		
		Vehicle vehicle = new Bus();  // 다형성-1 이용
		vehicle.defaultMethod(); 	  //ok : 두번째 사용법
		
		//(3) public static 정적 메소드 사용법
		// 	  (대전제를 항상 잊지말자!):정적 멤버는 정적 멤버답게 사용하라!
		Vehicle.staticMethod();  // ok
		
		//(4) private 인스턴스와 정적메소드 사용법
		//	  (중요): 이 private 메소드들은 모두 접근 제한자가 private 이기 때문에
		//			  절대 외부 클래스에서 접근 불가 => 즉, 인터페이스 내에서
		//			  만 사용 가능한 메소드
		
		
		
		
		
		
	}
	
	
}
