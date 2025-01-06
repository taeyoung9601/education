package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;

@Value
public class EmpVO {   //Read-only, Immutable
	Integer empno;     ///PK
	String ename;
	String job;
	Integer mgr;		// FK for emp table
	
	// 컬럼명이 2개 이상의 복합단어인 경우, Canel 기법으로 선언(***)
	java.util.Date hireDate;
//	java.sql.Date hireDate;
//	Timestamp hireDate;
//	java.time.LocalDateTime hireDate;
	
	
	Double sal;
	Double comm;
	Integer deptno;		//FK for dept table.
	
	
}
