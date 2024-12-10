package org.zerock.myapp.libs;
import java.util.Arrays;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

//콘솔에 출력하는 메소드를 가지고 있는 Logger 타입의 객체가 필드로 만들어진다!!
@Log4j2  // Logger log 란 필드가 자동생성
@Slf4j

public //모든 클래스에서 이 클래스를 사용하셔도 됩니다. -> 즉 객체 생성에 활용 가능하세요!!
class TotalClassSyntax {
	//대전제: 필드의 타입과 필드명은 한 몸이다! => 필드의 타입과 필드 명 사이에 public,static 같은
	//	                                           다른 자바 언언의 키워드가 와서는 안된다!
	
	//1. 인스턴스 필드 선언
	public String 인스턴스필드1;
	public int 인스턴스필드2;
	
	//2. 정적 필드 선언
	public static boolean 정적필드1 = false;
	static public double 정적필드2;
	
	
	// -- 위의 필드 선언부와 생성자 사이에는 보통 2칸의 공백 행을 넣어 구분한다!
	public TotalClassSyntax() {  // 기본 생성자: 매개변수가 하나도 없는 생성자
		log.debug("Default Constructor invoked.");
		
		//Logger 객체가 제공하는 모든 로그레벨(log-level) 별 메소드를 배우자1
		log.trace("1.TRACE LEVEL"); // 가장 상세한 레벨의 로그 출력
		log.debug("2.DEBUG LEVEL"); // 디버그용 로그 출력
		log.info("3.INFO LEVEL");  // 일반적인 로그 출력
		log.warn("4.WARNING LEVEL");  // 경고성 로그 출력
		log.error("5.ERROR LEVEL");  // 일반적인 모드 오류 로그 출력
		log.fatal("6.FATAL LEVEL");  // 아주 치명적인 오류 로그 출력

	
		//@Slf4j lombok annotation 이 제공하는 Logger.
		//Logger 객체가 제공하는 모든 로그레벨(log-level) 별 메소드를 배우자!
//		log.trace("1.TRACE LEVEL"); // 가장 상세한 레벨의 로그 출력
//		log.debug("2.DEBUG LEVEL"); // 디버그용 로그 출력
//		log.info("3.INFO LEVEL");  // 일반적인 로그 출력
//		log.warn("4.WARNING LEVEL");  // 경고성 로그 출력
//		log.error("5.ERROR LEVEL");  // 일반적인 모드 오류 로그 출력
//		log.fatal("6.FATAL LEVEL");  // 이 로그 레벨은 없습니다.
	}
	
	//3. 생성자 선언 -> 인스턴스 필드 초기화, 생성자도 얼마든지 오버로딩 가능
	public // -> 다른 코드에서 얼마든지, new 연산자의 피연산자로 생성자 사용해서 객체 생성가능
	TotalClassSyntax(String 값1) {
		log.debug("TotalClassSyntax({})invoked.",값1);
		//인스턴스 필드의 초기화 -> 모든 필드를 초기화 할 필요는 없다 -> 초기화가 필요한 필드만 한다!
		this.인스턴스필드1 = 값1;
		log.info("\t + 정적필드1:{}", TotalClassSyntax.정적필드1);
	}
	
	public 
	TotalClassSyntax(int 값2, double 값3) {  // 오버로딩된 생성자 - (매개변수의 개수,순서는 같지만, 타입이 틀리니
		log.debug("TotalClassSyntax({},{})invoked.",값2,값3);
		
		this.인스턴스필드2 = 값2;
		
		this.인스턴스메소드1();
		
		TotalClassSyntax.정적메소드1();
	}
	
	//대전제: static initializer 는 2개의 목적으로 사용:
	// (1) 모든 정적 필드의 초기화 - 물론, 초기화가 필요한 필드만..
	// (2) JVM이 수행하고, 단 1번만 호출되기 때문에 이런 성질을 이용하는 주요 로직 구현에도 사용
	// 특징 : JVM이 수행하는데다가, 개발자가 클래스를 사용하기도 전에, 단 한번 수행되기 때문에
	// 	  	  아주 중요한 "초기 준비 작업" 수행에 적합
	
	
	static { // 역할: 정적 필드의 초기화 수행
		log.debug("----------1----------");
//		this.인스턴스필드1 = "문자열"; // this 사용불가: 인스턴스 필드1
		
		// 대전제: 정적 멤버는 정적 멤버답게, '참조 타입명.필드, 참조타입명.메소드'으로 사용하라!
		TotalClassSyntax.정적필드1 = false;
		
//		this.인스턴스메소드1();  // XX: this 사용불가
//		인스턴스메소드1();		// XX : 대전제 위반 사용불가
	} // static initializer
	
	static {  // static initializer 는 0개 이상 얼마든지 선언가능합니다! -> 물론, 컴파일하면, 하나로 합쳐
		// 복잡한 연산이나, 외부 데이터를 끌어와서 초기화하는 경우도 있기 때문에, 가독성을 위해서라도
		// 여러 개의 static initializer 선언을 가능하게 했어요..
		log.debug("----------2----------");
		TotalClassSyntax.정적필드2 = 3.14;	
	}
	
	static {
		log.debug("----------3----------");
	}
	
	
	//인스턴스 메소드 선언 - 객체가 생성되어야 호출 가능한 메소드
	public
	void 인스턴스메소드1() {
		log.debug("인스턴스메소드1() invoked.");
		
		log.info("\t+인스턴스필드1:{}", this.인스턴스필드1);
		
		this.인스턴스메소드2();
		
		log.info("\t+정적필드1:{}", TotalClassSyntax.정적필드1);
		
		TotalClassSyntax.정적메소드1();
	}
	
	// 인스턴스 메소드 오버로딩: 매개변수의 개수/순서/타입 중 하나라도 틀리면 허용
	public
	void 인스턴스메소드2() {
		log.debug("인스턴스메소드2() invoked.");
		
		this.인스턴스메소드3();
	}
	
	public
	void 인스턴스메소드3() {
		log.debug("인스턴스메소드3() invoked.");
	}
	
	
	//정적 메소드 선언 - static 키워드가 붙은 메소드
	//					 객체하고는 무관하다! => 정적 멤버답게 호출하라!
	
	public  // 누구든지 사용(호출)하세요. 
	static
	void 정적메소드1() {
		log.debug("정적메소드1() invoked.");
		
		TotalClassSyntax.정적메소드2();
	}
	
	
	public static void 정적메소드2() {
		log.debug("정적메소드2() invoked.");
		
		//자기 자신을 다시 호출하는 행위 => 재귀호출 => 무한 루프
//		TotalClassSyntax.정적메소드2();  // Stack Overflow -> 오류 출력, JVM terminated.
	}
	
	
	
	

	
	
	
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args)); 

//		TotalClassSyntax obj = new TotalClassSyntax();
//		TotalClassSyntax obj = new TotalClassSyntax();
		TotalClassSyntax obj = new TotalClassSyntax(23,3.14);
		log.info("\t+ obj:{}", obj);
		
	}// main
	
	static {
		log.debug("----------4----------");
	}
	
}//end class
