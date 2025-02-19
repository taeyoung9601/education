package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
@Data

@Slf4j
@Entity
@EntityListeners(CommonEntityLifecycleListener.class)

//@Table(name)
public class Locker3 implements Serializable{			//Target, 1, FK(Student)
	@Serial private static final long serialVersionUID = 1L;

	// 1. PK 속성 선언
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "locker_id")
	private Long id;
	
	// 2. 일반 속성 선언
	@Basic(optional = false)
	private String name;	
	
	// 3. 연관관계 매핑
	// 1:1 (OneToOne), Bi-direction , Target (FK)
	@ToString.Exclude
	@OneToOne
	@JoinColumn(name = "my_student")
	private Student3 student;	// FK
	
	// Association Convenient Method
	public void setStudent(Student3 newStudent) {
		log.debug("setStudent({}) invoked.", newStudent);
		
		// 양방향을 고려해서, FK필드의 값만 설정하지 말고
		// 학생(Main)의 사물함(Target) 필드에도 값을 함께 설정해주는게
		// 대부분의 "연관관계 편의 메소드"의 역할입니다.
		if (Objects.nonNull(newStudent)) {
		this.student = newStudent;		//
		this.student.setLocker(this);	// Set Value of Main(Student3) Field
		}
	}
	
}// end class
