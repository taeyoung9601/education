package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class EmpDTO implements Serializable{
	@Serial private static final long serialVersionUID = 1L;
	
	// DTO의 필드는 실제 전송된 모든 요청 파라미터들을 저장할 수 있는
	// 필드가 선언되어야 합니다. 이 때, 어느 특정 테이블을 고려해서 만드는게
	// 아니란 것을 기억하시기 바랍니다. (***)
	
	private Integer empno;
	private String ename;
	private Double sal;
	private Integer deptno;
	
}// end class
