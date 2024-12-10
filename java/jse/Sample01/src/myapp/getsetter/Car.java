package org.zerock.myapp.getsetter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString

//@Getter
//@Setter  == lombok 을 이용해 Getter/ Setter 선언

@NoArgsConstructor
public class Car {
	private int maxSpeed;
//	@Getter @Setter
	private int speed; // 상태속성
	private boolean stop; // 상태속성
	
//	Getter 메소드 선언
	public int getSpeed(){
		log.debug("getSpeed() invoked.");
		return this.speed;
	}
	
	//Setter 메소드 선언
	public void setSpeed(int speed) {
		log.debug("setSpeed({}) invoked.", speed);
		this.speed = speed;
	}
	
	//Getter 메소드 명의 예외 규칙:
	// 은닉화된 필드의 타입이 boolean (논리타입)인 경우,
	// 접두사로 'get'안쓰고, 'is' + 필드 명
	public boolean isStop() {
		log.debug("isStop() invoked.");
		return this.stop;
	}
	
	//Setter 메소드 선언 : 논리 타입에 대한 Setter 메소드의 접두사는 'set'입니다.
	public void setStop(boolean stop) {
		log.debug("setStop({}) invoked." , stop);
		this.stop = stop;
	}

	
	// Getter 메소드의 존재 이유: 필드의 값을 단순 반환할 때에도 사용하지만
	// 							  단위 변환이 필요한 경우를 대비하셔도 사용됩니다.
	//							  (예: 시속 -> 마일 변환)
	public double getMaxSpeed() {
		log.debug("getMaxSpeed() invoked.");
//		return this.maxSpeed;
		return this.maxSpeed*0.6;
	}

	//Setter 메소드의 존재 이유: 해당 필드에 유효한 값만 설정해서
	//							 무결성을 유지하는데 있다!
	
	public void setMaxSpeed(int maxSpeed) {
		log.debug("setSpeed({}) invoked." , speed);
		
		if(maxSpeed != 0 && maxSpeed <= 350 ) {
			this.maxSpeed = maxSpeed;
		}else { // 예외 강제 발생
			throw new IllegalStateException("maxSpeed value is invalid.");
		}
	}
	
}// end class
