package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Value;

// VO 패턴으로 Value Object 생성
// (1) 모든 필드는 은닉화(캡슐화) 되어야 한다 - private final 필드
// (2) 모든 필드에 값이 한번 설정되면, 수정불가하게 만들어야 한다. (읽기전용)
// (3) 2단계 중복판정 알고르즘 대응이 되어 있어야한다
// (4) 기본적으로 자바빈 이어야 한다.
// (5) optional) Serializable 해야 한다.
// (6) VO 객체는, 1개의 테이블의 행 데이터를 온전히 보관가능하게 필드를
//		가져야 합니다.
// (7) 선언할 필드는 반드시, 해당 테이블의 스키마 순서대로 필드의 순서/타입/이름
// 	   을 맞게 선언해야합니다.
// 	   (주의사항: VO/DTO 필드 모두 반드시 Wrapper Type 사용)

@Value
public class EmpVO2 implements Serializable{
	
	@Serial private static final long serialVersionUID = 1L;
	
	Integer empno;
	String ename;
	String job;
	Integer mgr;
	
	Date hiredate;	// java.util
//	Date hiredate;	// java.sql
// 	TimeStamp hiredate;
//	LocalDateTime hiredate;
	Double sal;
	Double comm;
	Integer deptNo;
	
	
}// end class
