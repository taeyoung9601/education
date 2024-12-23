package org.zerock.myapp.generic;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Log4j2

@ToString
@NoArgsConstructor
public class BananaBox {	// 사과전용 상자
	private Banana obj;
	
	
	public void set(Banana banana) {
		log.debug("set({}) invoked.", banana);		
		this.obj = banana;
	}
	
	public Banana get() {
		log.debug("get() invoked.");		
		return this.obj;
	}
} // end class
