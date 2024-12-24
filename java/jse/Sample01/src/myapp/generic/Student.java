package org.zerock.myapp.generic;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString(callSuper = true)

public class Student extends Person {

	public Student(String name) {
		super(name);  				//반드시 첫번째 실행문으로 와야함
		
		log.debug("Student({})invoked.",name);
	}//constructor

}// end class
