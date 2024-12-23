package org.zerock.myapp.generic;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class FruitBoxExample {

	// 목표: 제네릭타입 클래스로, 특정 과일전용의 상자객체를 만들어 보자!!
	public static void main(String[] args) {
		// 1. 애플 전용상자
		// T (일반화시킨 과일타입) -> Apple (구체적인 과일타입)
//		FruitBox<Apple> fruitBox = new FruitBox<Apple>();
		FruitBox<Apple> fruitBox = new FruitBox<>();
		
		fruitBox.set(new Apple());
		log.info("fruitBox: {}", fruitBox);
		log.info("get: {}", fruitBox.get());
		
		log.info("-".repeat(30));
		
		// 2. 바나나 전용상자
//		FruitBox<Banana> fruitBox2 = new FruitBox<Banana>();
		FruitBox<Banana> fruitBox2 = new FruitBox<>();
		fruitBox2.set(new Banana());
		log.info("fruitBox2: {}", fruitBox2);
		log.info("get: {}", fruitBox2.get());
		
	} // main

} // end class

