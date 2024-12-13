package org.zerock.myapp.intf;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2

@NoArgsConstructor


public class Taxi implements Vehicle {  

	@Override
	public void run() { 				// 인터페이스 상속
		log.debug("run() invoked.");
		log.info("택시가 달립니다");
		
	}

}
