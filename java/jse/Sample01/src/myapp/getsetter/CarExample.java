package org.zerock.myapp.getsetter;

import java.util.Arrays;

import org.zerock.myapp.libs.Utils;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Log4j2
//@Slf4j    // Spring Boot 가 사용하는 Logger

public class CarExample {

	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		Car myCar = new Car();
		log.info(myCar);
		
		//1. Access to the myCar speed field.
//		Utils.printToConsole(myCar.speed);   // XX:  Why? private field.
//		Utils.printToConsole(myCar.stop);    // XX: Why? private field.
		
		//2. Getter 메소드 이용하여 필드 값 사용
		log.info(myCar.getSpeed());
		log.info(myCar.isStop());
		
		//3. Setter 메소드 이용하여 필드 값 설정
		myCar.setSpeed(110);
		myCar.setStop(false);
		
		//4. Setter 메소드를 이용해서, 자동차의 최고 설정
//		myCar.setMaxSpeed(1000);  // 1000h/ km
		log.info(myCar.getMaxSpeed());
		
		log.info(myCar);
	} // main

} //end class
