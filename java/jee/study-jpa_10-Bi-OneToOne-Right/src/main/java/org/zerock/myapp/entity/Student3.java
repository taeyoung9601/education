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
import javax.persistence.OneToOne;

import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Data

@Entity
@EntityListeners(CommonEntityLifecycleListener.class)

//@Table(name)
public class Student3 implements Serializable {			//Main, 1, NO FK
	@Serial private static final long serialVersionUID = 1L;
	
	// 1. PK 속성 선언
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long id;
		
	// 2. 일반 속성 선언
	@Basic(optional =false)
	private String name;
	
	// 3. 연관관계 매핑
	// 1:1 (OneToOne), Bi-direction , Main (No FK)
//	@ToString.Exclude
	@OneToOne(mappedBy = "student")		// mappedBy 설정으로 연관관계 주인 설정
	private Locker3 locker;
	
}// end class
