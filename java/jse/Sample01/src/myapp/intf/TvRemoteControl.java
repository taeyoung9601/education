package org.zerock.myapp.intf;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString
@Log4j2
@NoArgsConstructor

public class TvRemoteControl 
	implements RemoteControl { // 구현도 반드시 " 상속" 이란 것을 잊지 말라!

	// -- 상속된 추상메소드(=강제규격)를 반드시 구현해야 함(메소드 오버라이딩)
	@Override
	public void turnOn() {
		log.debug("turnOn() invoked.");
		
	}

	@Override
	public void turnOff() {
		log.debug("turnOff() invoked.");
	}

	@Override
	public void setVolume(int volume) {
		log.debug("setVolume({}) invoked.", volume);
	}

}
