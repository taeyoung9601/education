package org.zerock.myapp.libs;
import lombok.extern.log4j.Log4j2;

@Log4j2

//Illegal modifier for the class Dog; only public, abstract & final are permitted
public 		 // OK : 추가로 default 도 허용되지만, 패키지 배우고 나서하자!
//private    // XX : 허용되지않음
class Dog {	 // 허용되는 접근 제한자 : public , default 2가지

	//필드에 public or private 접근 제한자를 적용해보자!
//	private        // 이 클래스 내부에서만 접근 가능, 외부에서 절대 접근 불가
	String name;
	
	public            // 누구든지 이 생성자와 new 연산자 이용해서, 인스턴스 생성 가능
//	private			  // 이 클래스의 외부에서,누구든지 인스턴스 생성 못한다!
	Dog(String name){
		log.debug("Dog({}) invoked.", name);
		
		this.name = name;
	}
	
	public
//	private          // 이 클래스의 외부에서,누구든지 인스턴스 생성 못한다!
	String getName() {
		log.debug("getName() invoked.");
		String name = "Tae";
		
		return this.name;
	}
	
}// end class
