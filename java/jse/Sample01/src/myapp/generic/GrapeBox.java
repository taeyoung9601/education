package org.zerock.myapp.generic;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Log4j2

@ToString
@NoArgsConstructor
public class GrapeBox {	// 사과전용 상자
	private Grape obj;
	
	
	public void set(Grape grape) {
		log.debug("set({}) invoked.", grape);		
		this.obj = grape;
	}
	
	public Grape get() {
		log.debug("get() invoked.");		
		return this.obj;
	}
} // end class
