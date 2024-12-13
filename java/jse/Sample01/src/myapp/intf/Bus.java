package org.zerock.myapp.intf;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2

@NoArgsConstructor


public class Bus implements Vehicle {     // 인터페이스 상속

	@Override
	public void run() {
		log.debug("run() invoked.");
		log.info("버스가 달립니다");

	}

}
