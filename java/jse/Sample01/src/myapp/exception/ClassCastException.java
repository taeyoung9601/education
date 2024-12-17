package org.zerock.myapp.exception;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class ClassCastException {

	
	// 목표: 이번에는 3번째로 가장 자주 발생하는 Runtime 예외인,
	//       ClassCastException 의 의미와 언제발생가능한지를 이해한다.
	//       바로, 다형성-1 에서, 자식 객체를 부모품에서 강제로 빼낼때..
	public static void main(String[] args) {
		log.debug("main({}) invoked.",Arrays.toString(args));
		
		Animal animal = new Dog();	// 다형성-1
		log.info("\t1. animal: {}", animal);

		Cat cat2 = null;
		Dog dog2 = null;
		
		if(animal instanceof Cat cat) {
			cat2 = cat;

		} else if (animal instanceof Dog dog) {
			dog2 = dog;
		}
		
		log.info("\t2. cat: {}", cat2);
		log.info("\t1. dog: {}", dog2);
		
	}

}

abstract class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}