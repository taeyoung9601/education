package org.zerock.myapp.exec;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Log4j2
//@Slf4

public class Sample48 {
	Dog dog;		// OK : public class Dog
	
	Sample48(Dog dog){ // OK : public class Dog
		
	}
	
	// 목표: 접근 제한자(4가지 중에 먼저 2가지: public/private)를 이해 및 활용하자!
	public static void main(String[] args) {
		Dog dog = new Dog("Coca");   // OK : if public class Dog
		
//		log.info("My dog's name:{}", dog.name);
		log.info("My dog's name:{}", dog.getName());
	}//main

	public static void staticMethod(Dog dog) {
		
	}
	
	
}// end class
