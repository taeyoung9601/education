package org.zerock.myapp.service;

import java.util.Map;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.exception.BizException;

//@FunctionalInterface
// 비지니스 계층의 모든 서비스가 반드시 구현해야할 규약 선언
public interface CommandService {	// Command Pattern
	
	public static final String MODEL_KEY = "_MODEL_";
	public static final String VIEW_KEY = "_VIEW_NAME_";

	// 각각의 command를 처리할 서비스 객체가 공통으로 반드시
	// 구현해야 할 메소드의 시그니처 정의 (Command Pattern)
	// 이 때, 각 서비스마다 자신의 비지니스 의미에 맞는 예외를 throw 할 수 있도록
	// 상위 비지니스 예외를 throw 하도록 합니다 (다형성 -1 을 이용)
	// 리턴타입도 마찬가지로 다형성-1을 이용해서, 그 어떤 Map타입도 반환할 수 있도록 합니다.
	public default Map<String, Object> execute(EmpDTO dto) throws BizException {return null;}
	
	
}// end interface
