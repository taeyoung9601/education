package org.zerock.myapp.lambda;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MyFunctionalInterface3Example {

	public static void main(String[] args) {
		log.debug("main({}) invoked", Arrays.toString(args));
		
		MyFunctionalInterface3 mfi3 = n -> n.toString();
		
		String result = mfi3.convertNumberToString(100);
		log.info("\t + result: {}", result);
		
	}// main
	
	
}// end class
