package org.zerock.myapp.intf;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2

@NoArgsConstructor

public class Driver {

	//메소드 1개 선언
	public void drive(Vehicle vehicle) {
		log.debug("drive({}) invoked.",vehicle);
		
		//다형성
		vehicle.run();
	
	
	}
	
}// end class
