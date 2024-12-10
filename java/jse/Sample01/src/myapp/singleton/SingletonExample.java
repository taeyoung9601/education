package org.zerock.myapp.singleton;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SingletonExample {

	//목표: 특정 참조타입의 싱글톤 객체를 사용하는 방법을 익히자!
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		// 싱글톤 빵이 필요하게 되었어요. 어떻게 얻을 수가 있을까?
		// 직접 생성은 안되니까(private 으로 생성자를 막아버렸죠)
		
		for(var index = 0; index <5; ++index) {
		Singleton obj = Singleton.getInstance();	//정적 메소드 호출
		log.info("\t + obj:{}", obj);
		}
		
	}
	
}//end class
