package org.zerock.myapp.generic;

import java.util.Arrays;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2

//부모타입이 인터페이스이면, 자식객체(=구현객체)생성시
// "부모객체가 먼저 힙에 생성된다"라는
// 대전제는 "성립할 수가 없다"
// 때문에, 부모타입이 인터페이스인 경우에는,
// 자식 구현클래스에 붙인 lombok의 @toString(callSuper =true)는
// 말도 안된다! -> callSuper = true 

@ToString
@NoArgsConstructor

public class OurStorageImpl<T> implements IStorage<T> {

	@Override
	public void add(T item, int index) {
		log.debug("add({},{}) invoked.", item,index);
		
	}

	@Override
	public T get(int index) {
		log.debug("get({}) invoked.", index);
		return null;
	} // main
	
	
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));

	}
}// end class
