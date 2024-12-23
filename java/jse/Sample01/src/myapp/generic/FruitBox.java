package org.zerock.myapp.generic;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Log4j2

@ToString
@NoArgsConstructor
// 일반적인 클래스 + 제네릭의 타입파라미터 => 제네릭 타입의 클래스가 된다!!!
public class FruitBox<T> {	// <T> : 제네릭의 타입 파라미터 선언
	private T fruit;

	public void set(T fruit) {
		log.debug("set({}) invoked.", fruit);
		this.fruit = fruit;
	}
	
	public T get() {
		log.debug("get() invoked.");
		return this.fruit;
	}	
} // end class
