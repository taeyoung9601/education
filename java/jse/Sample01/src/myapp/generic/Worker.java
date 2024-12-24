package org.zerock.myapp.generic;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString(callSuper = true)

public class Worker extends Person {
	
	public Worker(String name) {
		super(name);
		
		log.debug("Worker({})invoked.",name);
	}// constructor

}// end class
