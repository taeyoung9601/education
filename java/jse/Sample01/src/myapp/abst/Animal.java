package org.zerock.myapp.abst;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@Log4j2
@NoArgsConstructor

// 부모로서의 역할만 하는 추상 클래스로써
// 반드시 자식 클래스에서 구현해야 할 메소드의 골격만 잡아주자!
// (즉, 추상 메소드 선언)
public abstract class Animal extends 조상{
	
	//모든 동물들은 자기의 소리를 내도록 "강제"하자!
	public abstract void soundVoice();  // 추상 메소드
	
	
	
}//end class
