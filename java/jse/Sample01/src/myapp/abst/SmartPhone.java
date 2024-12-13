package org.zerock.myapp.abst;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString(callSuper = true)
@Log4j2

public 
//abstract   // 오버라이딩 안할 때 클래스 앞에 추가
class SmartPhone extends Phone {   // called, Concrete(실체) Class
//	The type SmartPhone must implement the inherited abstract method Phone.bell()

	public SmartPhone(String owner) {
		super(owner);
		
		log.debug("SmartPhone({}) invoked", owner);
		
	}

	public void webSearch() {
		log.debug("webSearch() invoked.");
	}
	
	
	
	@Override
	public void bell() {  // 부모가 물려준 대출금 갚아아죠(이게 메소드 오버라이딩)
		log.debug("bell() invoked.");

	}

}//end class