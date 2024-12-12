package org.zerock.myapp.overriding;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString(callSuper = true)
@Log4j2

@NoArgsConstructor

public class Computer extends Calculator {

	//Do Focus On method Overriding(= Overwriting)
	@Override  // 이 어노테이션을 붙이면, 아래의 메소드의 오버라이딩을
			   // 자바언어 문법에 맞게 했는지를, 자바 컴파일러가 컴파일 타임 시에 체크
	
	double areaCircle(double r) {  // 부모가 상속해준 이 메소드를 다시 구현(=재정의)
		log.debug("areaCircle({}) invoked.",r);
		
		//원의 면적을 주어진 반지름으로 구하여 반환
		return Math.PI * r * r;
	}

}
