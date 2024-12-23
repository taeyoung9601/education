package org.zerock.myapp.generic;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Log4j2

@ToString
@NoArgsConstructor
public class Box<T> {		// 자동/강제 형변환은 OOP기반 프로그램의 성능을 대폭 깍아먹는 요인
	private T obj;
	
	
	public void set(T obj) {
		log.debug("set({}) invoked.", obj);		
		this.obj = obj;
	}
	
	public T get() {
		log.debug("get() invoked.");		
		return this.obj;
	}

} // end class
