package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;

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
@Data

@Entity
@EntityListeners(CommonEntityLifecycleListener.class)

//@Table(name)
public class Student1 implements Serializable {			//Main, 1, FK(Child)
	@Serial private static final long serialVersionUID = 1L;
	
	// 1. PK 속성 선언
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long id;
		
	// 2. 일반 속성 선언
	@Basic(optional =false)
	private String name;
	
	// 3. 연관관계 매핑
	// 1:1 (OneToOne), Uni-direction , Main
//	@OneToOne															// 1
	@OneToOne(targetEntity = Locker1.class) 							// 2
//	@OneToOne(targetEntity = Locker1.class, optional = false ) 			// 3 : 필수참여 (즉, Null 허용안됨)
//	@OneToOne(targetEntity = Locker1.class, cascade =CascadeType.ALL) 	// 4
	
//	@JoinColumn															// 1
//	@JoinColumn(name = "my_locker") 									// 2
	@JoinColumn(name = "my_locker", referencedColumnName = "locker_id")	// 3
	private Locker1 locker;		// FK
	
}// end class
