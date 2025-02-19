package org.zerock.myapp.domain;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

// 읽기 전용 객체를 만들 수 있는 참조 타입(!= 클래스)

@Slf4j

// 읽기전용 객체를 만들 수 있는 참조타입(!= 클래스)
// lombok의 @Value 어노테이션과 100% 동일한 멤버를 만들어 냅니다! (***)
// private final 필드, 모든 필드의 순서대로 매개변수로 가지는 생성자,
// @ToString, @EqualsAndHashcode, @Getter, @AllArgsConstructor



public record EmpVO(
		// 여기도 모든 읽기 전용 필드를 선언합니다.
		// 접근제한자를 붙이면 안됩니다. ->레코드가 자동으로 private final 로 붙임
		Integer empno,
		String ename,
		String job,
		Integer mgr,
		Date hiredate,	// java.util
//		Date hiredate,	// java.sql
//	 	TimeStamp hiredate,
//		LocalDateTime hiredate,
		Double sal,
		Double comm,
		Integer deptno

		) {
	/*
	 * Compact Constructor
	 *1. 이 레코드 타입은 소위 "Compact Constructor" (압축 생성자)를 선언할 수 있습니다.
	 *2. 이 생성자는, 일반 클래스의 기본생성자와 다르게 반드시 매개변수 선언부를 가져서는 안됩니다(즉, 매개변수 선언부가 없습니다)(**)
	 *3. 이 생정자 안에서는, 위의 읽기전용(private final)필드를 초기화해서는 안됩니다.
	 *4. 선언 문법: 
	 *			public 레코드명 {}
	*/
	public EmpVO{
		log.debug("Compact Constructor invoked.");
		
//		this.empno=1; // 위의 3번 규칙 위반 <-- ***
		
		log.info("empno({}) ename({}) job({}) mgr({}) hiredate({}) sal({}) comm({}) deptno({})",
				empno, ename, job, mgr,hiredate, sal, comm, deptno);
	}// Compact Constructor
	
	public String getMyname() {
		return this.ename;
	}// 인스턴스 메소드
	
	// 스태틱 변수
    static int staticVariable;
    
    // 스태틱 이니셜라이저
    static {
        System.out.println("Static initializer block executed!");
        staticVariable = 10; // staticVariable 초기화
    }
	
	
	static void staticMethod() {
		log.info("heoll");
	}// 정적 메소드 
	
	public static void main(String[] args) {
		EmpVO vo = new EmpVO(1000,"tae", "zz",20,new Date(), 1000., 100., 30);
		log.info("vo:{},{}", vo,vo.getMyname());
		EmpVO.staticMethod();
		System.out.println("Static variable value: " + staticVariable);
	}// main
	
}// end record
