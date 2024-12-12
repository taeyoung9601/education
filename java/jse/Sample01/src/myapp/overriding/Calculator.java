package org.zerock.myapp.overriding;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString
@NoArgsConstructor

public class Calculator {

	String myName() {
		return "tae";
	};
	
	double areaCircle(double r) {
		log.debug("areaCircle({}) invoked.", r);
		
		// 원의 면적 = 원주율(PI) * 반지름(r)의 제곱
		return 3.14159 *r *r;
	}

	
	
}// end class
