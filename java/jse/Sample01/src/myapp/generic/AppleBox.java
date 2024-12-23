package org.zerock.myapp.generic;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Log4j2

@ToString
@NoArgsConstructor
public class AppleBox {	// 사과전용 상자
	private Apple obj;
	
	
	public void set(Apple apple) {
		log.debug("set({}) invoked.", apple);		
		this.obj = apple;
	}
	
	public Apple get() {
		log.debug("get() invoked.");		
		return this.obj;
	}
} // end class
