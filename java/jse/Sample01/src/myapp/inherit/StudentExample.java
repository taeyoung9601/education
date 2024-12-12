package org.zerock.myapp.inherit;

import java.util.Arrays;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@Log4j2

public class StudentExample {

	public static void main(String[] args) {
		log.debug("main({}) invoked.",Arrays.toString(args));
		
		Student student = new Student("Tae","1234-1234");
		log.info("\t+student:{}",student);
		
		
		
	}// main

}// end class
