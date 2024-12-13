package org.zerock.myapp.abst;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString(callSuper = true)
@NoArgsConstructor

public class Dog extends Animal {

	//상속관계에서, 메소드 오버라이딩 => 다형성-2의 조건
	@Override
	public void soundVoice() {  // 부모로부터 강제받은 메소드 오버라이딩
		log.debug("soundVoice() invoked.");
		
		log.info("\t+멍멍!");
	}

}// end class
