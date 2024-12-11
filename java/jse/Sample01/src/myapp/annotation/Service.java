//@MyAnnotation														// T9 : XX
package	// @MyAnnotation XX 
org.zerock.myapp.annotation;

//@MyAnnotation														// T10 : XX - 임포트 구문에는 허용불가
import java.time.LocalDate;

import java.util.Date;

//@MyAnnotation														// T1 : OK
//public @MyAnnotation											// T1-1 : OK
@MyAnnotation public												// T1-2 : OK
class /* @MyAnnotation - XX */ Service {	// class + 클래스명은 `한몸`입니다.
	
	// 1. 인스턴스필드 선언
//	@MyAnnotation													// T2 : OK
	private @MyAnnotation String name;					// T2-1: OK
	
//	@MyAnnotation 
	private int /* @MyAnnotation - XX */ duration;				// T3 : OK
	
	// 2. 정적필드 선언
//	@MyAnnotation														// T4 - OK
	public /* @MyAnnotation */									    // T4-1 : OK
	static /* @MyAnnotation */									    // T4-2 : OK
	final   /* @MyAnnotation */										// T4-3 : OK
	double /* @MyAnnotation - XX */
	PI = 3.14159;
	
	
	// 3. 정적블록(static initializr) 선언 - 어노테이션 붙일 수 없음
	//     왜? JVM이 수행시키는 블록이기 때문에, 어떤 어노테이션이든
	//     만들어 지기도 전에 수행되기 때문에, 붙일 수 없다!
//	@MyAnnotation														// T5 : XX
	static	/* @MyAnnotation */										// T5-1 : XX 
	{
		// The final field Service.PI cannot be assigned
		
		// 소위 JODA TIME으로 구현된, 자바표준라이브러리의
		// 클래스입니다: JODA TIME에는 아래의 3가지 클래스가
		// 핵심입니다: (1) LocalDate   (2) LocalTime  (3) LocalDateTime
		LocalDate now = LocalDate.now();
		
		Date now2= new Date();
		
	} // static initializr
		
	// 4. 생성자 선언
//	@MyAnnotation													// T6 : OK
	public @MyAnnotation											// T6-1 : OK
	Service(
//		@MyAnnotation												// T11 : OK
		@MyAnnotation String name, 							// T11-1 : OK
		
		@MyAnnotation int duration
	) {
		;;
	} // default constructor
	
	// 5. 인스턴스 메소드 선언
//	@MyAnnotation														// T7 : OK
	public // @MyAnnotation											// T7-1 : OK
	void /* @MyAnnotation - XX */ 
	instanceMethod1() {
		try {} catch(Exception e) {}	 // try-catch
	} // instanceMethod1
	
	@MyAnnotation
	public void instanceMethod2(
//		@MyAnnotation													// T13-3: OK 
		final @MyAnnotation 												// T13-4: OK
		int age /* 매개변수의 상수화 */) {
		;;
	} // instanceMethod2
	
	// 6. 정적메소드 선언
//	@MyAnnotation														// T8 : OK
	public // @MyAnnotation												// T8-1 : OK 
	static // @MyAnnotation												// T8-2 : OK
	void   // @MyAnnotation											// T8-3 : XX
	staticMethod(
//		@MyAnnotation													// T12 : OK	
		String name, 
		@MyAnnotation int duration									// T12-1 : OK
	) {
		// 13. 지역변수
//		@MyAnnotation													// T13 : OK
		@MyAnnotation String temp = "";
		
		int	/* @MyAnnotation - XX */ 
		age = 23;
		
	} // staticMethod

} // end class
