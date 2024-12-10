package org.zerock.myapp.libs;
import lombok.AllArgsConstructor;
import lombok.ToString;


@ToString

//@NoArgsConstructor
@AllArgsConstructor

public class Person {
	
	//아래와 같이, 코드에서 사용되는 불변의 상수(진리 값)를 만드실 때에는
	//(1)객체하고는 무관 하게 정적으로 만들어야하고
	// (2)진리값은 수정할 수 있는 값이 아니기 때문에, final 키워드로 상수화
	// 해야만 하는 것 입니다.
	static final int 한계나이 = 120;  // 이게 진짜 모든 객체에 통용되는 진리 값(상수)
	
//	final String name = "TAE";
	final String name;
	String ssn;		   //사회 보장번호: 주민번호 -> Social Security Number
	String nation;    //국적
	
	
//	Person(){}
	
	Person(String name){
		this.name = name;
	}
	
	
	
	
	
	
	
} //end class
