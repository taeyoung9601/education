package org.zerock.myapp.abst;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@Log4j2

//POJO : Plain Old Java Object - 아주 아주 평범한 클래스에서 생성한 객체
public 
abstract 
//The type Phone must be an abstract class to define abstract methods
class Phone {

	private String owner;    // 전화기 소유자
	
	
	public Phone(String owner) {
		log.debug("Phone({}) invoked.",owner);

		this.owner = owner;
	}//Constructor
	
	public void turnOn() {
		log.debug("turnOn() invoked.");
	}
	
	public void turnOff() {
		log.debug("turnOff() invoked.");
	}
	
	// 추상메소드 선언 - 메소드 시그니쳐만 가짐
	public abstract void bell();  // 중괄호 블록(즉, 기능이 구현 안된)없음
	
	
	
	
}//end class