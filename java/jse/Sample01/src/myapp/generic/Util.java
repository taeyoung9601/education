package org.zerock.myapp.generic;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class Util {
	
	// If a type parameter <T> is attached the method,
	// This method is called, "Generic Method.
	public static <T> Box<T> boxing(T obj) {	// Generic Method
		log.debug("boxing({}) invoked.", obj);
		
		// 제네릭타입 클래스/인터페이스가 아래와 같이
		// 제네릭 메소드에서 "사용되면", "구체타입"을 지정하는게 아니라,
		// 제네릭 메소드의 타입파라미터에 지정될 "구체타입"으로 자연스럽게
		// 지정되도록 합니다.
		
		Box<T> box = new Box<>();
		box.set(obj);
		
		return box;
	} // boxing

} // end class
