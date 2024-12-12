package org.zerock.myapp.inherit;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@Log4j2

public class People {
	String name; 		// 자식이 다른 패키지에 있는 경우에만, 상속에서 제외
	
	@SuppressWarnings("unused")  // -> 쓰는이유 궁금
	@Getter private String ssn;  // 상속에서 무조건 제외   
	
	People(String name, String ssn){
		log.debug("People({},{}) invoked.",name,ssn);
		
		this.name = name;
		this.ssn = ssn;
		
	}
	
	
}// end class
