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

import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import lombok.Data;
@Data

@Entity
@EntityListeners(CommonEntityLifecycleListener.class)

//@Table(name)
public class Locker1 implements Serializable{			//Target, 1, Parent
	@Serial private static final long serialVersionUID = 1L;

	// 1. PK 속성 선언
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "locker_id")
	private Long id;
	
	// 2. 일반 속성 선언
	@Basic(optional = false)
	private String name;	
	
	// 3. 연관관계 매핑
	// 1:1 (OneToOne), Uni-direction , Target
	
}// end class
