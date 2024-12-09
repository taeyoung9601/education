package org.zerock.myapp.exec;
import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
//@Slf4j
public class Sample47 {
	
	// 목표: final 키워드의 이해와 사용
	public static void main(String[] args) {
		
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		Person person1 = new Person("Tae");
		log.info("person1:{}",person1);
		Person person2 = new Person("Young");
		log.info("person2:{}",person2);
		Person person3 = new Person("Kim");
		log.info("person3:{}",person3);
		
	}// main
	
}// end class
