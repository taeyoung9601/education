package org.zerock.myapp.generic;

import java.util.Arrays;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2

public class IStorageExample {
	
	// 목표: 제네릭 타입의 인터페이스 구현(상속)하는 자식 객체를 생성해 보아서,
	//		 제네릭 타입의 인터페이스 구현 시 어떻게 해야 하는지를 이해
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));

		// 제네릭이 당장 이해가 안되면, 제네릭 타입파라미터 선언부 + 타입추론연산자
		// 모두 삭제해도 괜찮다! -> 오류발생안함 
		// -> 대신, Object 타입이 모든 타입파라미터의 구체타입으로 자동 지정된다!
		OurStorageImpl<String> ourStorage = new OurStorageImpl<>();
		log.info("\t+ourStore:{}",ourStorage);
		
		ourStorage.add("Tae", 0);
		String name = ourStorage.get(0);
		log.info("\t + name: {}", name);
	}// main

} // end class
