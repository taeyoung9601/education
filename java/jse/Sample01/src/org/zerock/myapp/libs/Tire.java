package org.zerock.myapp.libs;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor // 어노테이션
public class Tire {
	String 제조회사;
	
	Tire(String 제조회사){  //생성자 역할: 객체의 필드의 초기화
		Utils.printToConsole("Tire(%s) invoked.".formatted(제조회사));
		
		this.제조회사 = 제조회사;
		
	}// Constructor
	
	
}// end class
