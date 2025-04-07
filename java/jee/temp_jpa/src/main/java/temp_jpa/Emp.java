package temp_jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the emp database table.
 * 
 */
@Entity
@Table(name="emp")
@NamedQuery(name="Emp.findAll", query="SELECT e FROM Emp e")
public class Emp extends org.zerock.myapp.domain implements Serializable {
	private static final long serialVersionUID = 1L;

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
	@JoinColumn(name="DEPTNO")
	private Dept dept;

	public Emp() {
	}

	public int getEmpno() {
		return this.empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public double getComm() {
		return this.comm;
	}

	public void setComm(double comm) {
		this.comm = comm;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Date getHiredate() {
		return this.hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return this.mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public double getSal() {
		return this.sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

}