package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Data;

@Data
public class EmpDTO {
	
	private Integer empno;
	private String ename;
	private String job;
	private	Integer mgr;
	private	Date hireDate;
	private Double sal;
	private Double comm;
	private Integer deptno;
	
} // end class
