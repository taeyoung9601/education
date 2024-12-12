package org.zerock.myapp.inherit;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString

@Log4j2

//하위(자식) 클래스가 상위(부모) 클래스를 extends 받는다! (현업 용어)
// 수많은 OOP 지원 언어중에, 자바 언어는 "단일 상속"만 허용(부모클래스 1개만 허용)
public class DmbCellPhone extends CellPhone { //자식 클래스
	
	// 1. 필드 선언
	int channel;
	
	
	//2. 생성자
	DmbCellPhone(int channel, String model, String color){
//		The constructor CellPhone(String, String) is undefined
		super(model,color);
		
		log.debug("DmbCellPhone({},{},{}) invoked.", channel,model,color);
		
		this.channel = channel;
		
		// 상속받은 부모필드의 초기화 수행
		// 부모객체로부터 상속받은 인스턴스 멤버(필드+메소드)
		// 자식꺼입니다 그러니, 상속받은 멤버는 내꺼처럼 사용하시면 됩니다.
		// 하지만, 아래와 같이, 물려받은 부모 객체의 필드르 초기화 해주는 코드는
		// 바람직 하지 않습니다.
		// 왜? 상속 관계에 있다고 해서, 부모 객체의 모든 필드가 상속되는게 아니라,
		// (1) 부모 객체의 필드 중에, private 접근 제한을 가지는 필드는 상속에서 제외
		// (2) 만일, 부모와 자식이 서로 각각 다른 패키지에 있는 경우,
		// 		부모 객체의 필드 중에, default 접근 제한을 가지는 필드 + 메소드도
		//		상속에서 제외
		// 올바른 부모 객체의 필드를 제대로 초기화 해주려면, 부모 객체의 생성자를
		// 직접 호출해줘야 한다.. 누가? 자식 생성자 안에서.. 어떻게? super() 문법을
		// 통해서( super() 문법: 부모 객체의 생성자 호출 문법)
		
		//부모 객체의 필드를 우선 초기화 해주는 코드로는 적합하지 않습니다.
		// 대신, 올바른 방법은 super() 구문으로 명시적으로 부모 생성자 호출을
		// 하셔야 합니다. 그래야 상속 제한에 걸려 상속되지 않는 필드도 잘 초기화
		// 되기 때문이죠..
//		this.model = model;
//		this.color = color;
		
//		Constructor call must be the first statement in a constructor
//		super();  // XX
		

		log.info("=====================");
		
		log.info("\t+this:{}",this); // 자식 객체 출력 with this keyword
//		log.info("\t+super:{}",super); // 부모 객체 출력 with super keyword
		log.info("\t+super.model:{}",super.model);
		log.info("\t+super.color:{}",super.color);
	}
	
	
	
	
	// 3. 메소드 선언 - 지상파 방송(DMB)에 대한 기능들
	void turnOnDmb() {
		log.debug("turnOnDmb() invoked.");
	}
	
	void turnOffDmb() {
		log.debug("turnOffDmb() invoked.");
	}
	
	void changeChannelDmb(int channel) {
		log.debug("changeChannelDmb({}) invoked.", channel);
		
		this.channel = channel;
	}	
	
	
	
	
	
	
	
	
}// end class