package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
@Data
@Entity
@Table(name="dept")
public class Dept  implements Serializable {
	@Serial private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int deptno;

	@Column(length=14)
	private String dname;

	@Column(length=13)
	private String loc;

	//bi-directional many-to-one association to Emp
	@OneToMany(mappedBy="dept", fetch=FetchType.EAGER)
	private List<Emp> emps = new Vector<>();

	public Emp addEmp(Emp emp) {
		this.emps.add(emp);
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