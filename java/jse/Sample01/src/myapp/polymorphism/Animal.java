package org.zerock.myapp.polymorphism;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class Animal {

	
	public void soundVoice() {
		log.debug("soundVoice() invoked.");
	}

	public static void main(String ...args) {
		Animal animal = new Animal();  		// 1st try : 부모 객체 생성 및 대입 ( 다형성 -1 아님)
		animal.soundVoice();
		
		//----------
		log.info("-".repeat(20));
		
		animal = new Human();				// 2nd try : 자식객체를 부모타입 참조변수에 대입( 다형성 -1)
		animal.soundVoice();
		
		Human extractedHuman = (Human)animal;
		log.info("\t+extractedHuman:{}",extractedHuman);
		//----------
		
		log.info("-".repeat(20));
		
		animal = new Dog();					// 3rd try : 자식객체를 부모타입 참조변수에 대입( 다형성 -1)
		animal.soundVoice();
		
		Dog extractedDog = (Dog)animal;
		log.info("\t+extractedDog:{}",extractedDog);
		//----------
		log.info("-".repeat(20));
		
		animal = new Cat();					// 4th try : 자식객체를 부모타입 참조변수에 대입( 다형성 -1)
		animal.soundVoice();
		
		
		//----------
		log.info("-".repeat(20));
		
		//Type mismatch: cannot convert from Animal to Cat
//		Cat extractedCat = animal;
		Cat extractedCat = (Cat)animal;
		log.info("\t+extractedCat:{}",extractedCat);
		
	}
	
	
	
}//end class

@Log4j2
@NoArgsConstructor
class Human extends Animal{
	
	@Override
	public void soundVoice() {  //메소드 재정의(Method Overriding)
		log.debug("soundVoice() invoked.");
		log.info("\t+엉엉---");
	}
	
	
}//end class


@Log4j2
@NoArgsConstructor
class Dog extends Animal{		// 두번째 자식 클래스(동물의 한 종류)
	
	@Override
	public void soundVoice() {  //메소드 재정의(Method Overriding)
		log.debug("soundVoice() invoked.");
		log.info("\t+멍멍---");
	}
	
	
}//end class

@Log4j2
@NoArgsConstructor
class Cat extends Animal{  		//세 번째 자식 클래스(동물의 한 종류)
	
	@Override
	public void soundVoice() {  //메소드 재정의(Method Overriding)
		log.debug("soundVoice() invoked.");
		log.info("\t+야옹---");
	}
	
	
}//end class