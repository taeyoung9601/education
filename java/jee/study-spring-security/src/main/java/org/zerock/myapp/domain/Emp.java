package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
@Data
@Entity
@Table(name="emp")
@NamedQuery(name="Emp.findAll", query="SELECT e FROM Emp e")
public class Emp implements Serializable {
	@Serial private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int empno;

	private double comm;

	@Column(length=10)
	private String ename;

	@Temporal(TemporalType.TIMESTAMP)
	private Date hiredate;

	@Column(length=9)
	private String job;

	private int mgr;

	private double sal;

	//bi-directional many-to-one association to Dept
	@ManyToOne
	@JoinColumn(name="deptno")
	private Dept dept;


}