package temp_jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dept database table.
 * 
 */
@Entity
@Table(name="dept")
@NamedQuery(name="Dept.findAll", query="SELECT d FROM Dept d")
public class Dept extends org.zerock.myapp.domain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int deptno;

	@Column(length=14)
	private String dname;

	@Column(length=13)
	private String loc;

	//bi-directional many-to-one association to Emp
	@OneToMany(mappedBy="dept", fetch=FetchType.EAGER)
	private List<Emp> emps;

	public Dept() {
	}

	public int getDeptno() {
		return this.deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return this.dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public List<Emp> getEmps() {
		return this.emps;
	}

	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}

	public Emp addEmp(Emp emp) {
		getEmps().add(emp);
		emp.setDept(this);

		return emp;
	}

	public Emp removeEmp(Emp emp) {
		getEmps().remove(emp);
		emp.setDept(null);

		return emp;
	}

}