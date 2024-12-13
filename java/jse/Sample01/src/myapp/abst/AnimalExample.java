package org.zerock.myapp.abst;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2

public class AnimalExample {
	
	// 목표: 추상클래스와 콘크리트 클래스 기반의 다형성 예
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));;
		
//		Animal animal = new Human();   // 다형성-1
//		Animal animal = new Dog(); 
		Animal animal = new Cat(); 
		log.info("\t+animal:{}",animal);
		
		// 부모 타입의 추상 메소드 호출
//		animal.soundVoice();  // 다형성-2 : (1)다형성-1 +메소드오버라이딩
		
		//-----------------
		// 다형성-1은 오직 직계부모타입에만 자식객체가 대입가능한게
		// 아니라, 부모의 부모, 부모의 부모의 부모 등, 위졲 상속계층구조
		// 에서도 동일하게 적용됩니다.
		
		// 앞으로 여러분들이 만드실 코드는 바로 아래와 같은,
		// 다형성-1에 기반한 코드가 상당히 많습니다.
		조상 ancestor1 = animal;
		조상 ancestor2 = new Dog();
		조상 ancestor3 = new Human();
		조상 ancestor4 = new Cat();
		
		
	}

}
