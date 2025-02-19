package org.zerock.myapp.model;

import java.io.Serial;
import java.util.Hashtable;
import java.util.Map;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// MVC 패턴대로, 각각의 참조타입이 나오게 되는데,
// 현재 Model과 viewName을 저장할 별도의 참조타입이 없습니다.
// 때문에, 명시적으로 우리만의 모델과 뷰이름을 저장할 참조타입 클래스를
// 만드는 것이 당연히 좋고, 스프링(부트) 역시 자기만의 참조타입을 가지고 있습니다.
// 지금 우리가 구현하는 이 모델타입은 스프링(부트)와 거의 대동소이합니다.

@Slf4j
@NoArgsConstructor

public class ModelAndView<K,V> extends Hashtable<K,V> implements Map<K,V>{

	@Serial private static final long serialVersionUID = 1L;
	
	public void addAttribute(K key,V value) {
		log.debug("addAttribue({},{}) invoked,", key,value);
		
		//--1---
		super.put(key,value);
		
		//--2---
		// 공유 데이터 영역 중, Request Scope에 모델 데이터를 공유 속성으로
		// 넣는 작업은 이미 ViewResolver 에서 하고 있습니다만,
		// 스프링(부트)는 이 modelAndM
		
	}// addAttribute

}// end class
