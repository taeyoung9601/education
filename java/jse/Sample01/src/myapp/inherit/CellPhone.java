package org.zerock.myapp.inherit;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString

@Log4j2
//@NoArgsConstructor

// 모바일 폰을 모델링 만든 클래스
public class CellPhone { // 부모 클래스

	// 1. 필드 선언
	String model;
	String color;
	
	
	// 2. 생성자 
	public CellPhone() {
//		this(null,null);  // OK but 무의미하다! 왜? 어차피 model 과 color 필드는
						  // String 참조 타입 필드이기 때문에, 초기화 해주지 않아도
						  // 기본 값인 null 을 가지기 때문에...
		
		log.debug("CellPhone() invoked.");	
		
	}
	
	public CellPhone(String model,String color) {
		log.debug("CellPhone({},{}) invoked.", model,color);
		
		this.model = model;
		this.color = color;
	}
	
	// 3. 메소드
	void powerOn() {
		log.debug("powerOn() invoked.");
	};
	
	void powerOff() {
		log.debug("powerOff() invoked.");
	};
	
	
	void bell() {
		log.debug("bell() invoked.");
	};
	
	
	void sendVoice(String message) {
		log.debug("sendVoice({}) invoked.");
	};
	
	
	void recvVoice(String message) {
		log.debug("recvVoice({}) invoked.");
	};
	
	
	void hangUp() {
		log.debug("hangUp() invoked.");
	};
	
	
	
}// end class
