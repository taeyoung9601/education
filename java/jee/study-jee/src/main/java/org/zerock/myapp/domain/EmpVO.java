package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Value;

// 오직 Emp 테이블의 스키마 순서대로 필드를 선언하시면 됩니다:
// (1) 필드의 이름은, 스키마의 컬럼명과 동일하게 하되,
// 	   컬럼이 복합단어인 경우, 보통 Snake Case => Camel Case로 변환
// (2) 반드시 모든 필드를 초기화 해주는 생성자가 있어야 한다 (읽기 전용이 되어야하니까)
// (3) 모든 필드는 반드시 private으로 은닉화 해야한다.
// (4) 모든 피륻에 대해서, Getter/Setter 중에, 오직 Getter만 제공( 역시 읽기전용이니까)
// (5) 중복 판정 2단계 알고리즘이 구현되어야 합니다.
// (6) (option) Serializable 해야 합니다.
// (7) VO, DTO 패턴 모두 필드의 타입으로는 절대 기본타입을 사용하지 않는다.
// 	  기본 타입이 필요할 때에는, 해당 타입에 대응되는 wrapper type을 사용해야 한다.

//읽기전용의 테이블 데이터를 필요로 하는, 웹3계층의 "비지니스 계층"이나
//UI생성 계층인 "프리젠테이션 계층"에서 필요로 하면, 전달하는 객체가 바로
//VO(Value Object) 입니다.


@Value					// 웹 3계층에서, "영속성 계층(Persistence Layer)"의 클래스
public class EmpVO implements Serializable{	
	@Serial private static final long serialVersionUID = 1L;
	
	// Immutable Object
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	
	private Date hireDate;			// 1st Method : with java.util.Date
//	private Timestamp hireDate;		// 2nd Method : with java.sql.Timestamp
//	private LocalDateTime hireDate;	// 3rd Method : with JODA time
	
	private Double sal;
	private Double comm;
	private Integer deptno;
	
}
