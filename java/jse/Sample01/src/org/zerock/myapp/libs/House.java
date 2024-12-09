package org.zerock.myapp.libs;
import lombok.NoArgsConstructor;

@NoArgsConstructor

//단독주택 설계도(클래스)
public class House {
	
	//아래 4개의 필드 ( =즉, 객체인 집의 속성)는, 이 클래스(설계도)대로
	//여러 채의 집을 지어도 다 똑같은 값을 가지기 때문입니다.
	
	static int 방개수 = 4;
	static int 화장실개수 = 2;
	static double 총평수 = 34;
	static int 층수 = 2;
	
	//인스턴스 필드
	String 외벽색상;
	String 조명색상;
	
	// 현재까지 이 설계도 대로 지은 집의 개수
	static int 몇채;
	
	//각 집(객체)안에서 설치된 보일러를 on시키는 메소드 => So, 인스턴스 메소드
	void 보일러on() {
		
	}
	
	//각 집(객체)안에서 설치된 보일러를 off시키는 메소드 => So, 인스턴스 메소드
	void 보일러off() {
		
	}
	
	static
	int return현재까지지어진집의갯수() {
		return 몇채;
	}
	
	
}//end class
