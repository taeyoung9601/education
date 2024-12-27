package org.zerock.myapp.util;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
//@ ToString
@NoArgsConstructor

public class Calculator implements AutoCloseable {
	
	public int add(final int num1, final int num2) {
		log.debug("add({},{}) invoked.", num1, num2);
		return num1 + num2;
	}
	public int sub(final int num1, final int num2) {
		log.debug("sub({},{}) invoked.", num1, num2);
		return num1 - num2;
	}
	public int mul(final int num1, final int num2) {
		log.debug("mul({},{}) invoked.", num1, num2);
		return num1 * num2;
	}
	public double div(final int num1, final int num2) {
		log.debug("div({},{}) invoked.", num1, num2);
		return num1 / (num2 * 1.) ;
	}
	
	@Override
	public void close() throws Exception {
		log.debug("close() invoked.");
	}
	
	
	
}//end class
