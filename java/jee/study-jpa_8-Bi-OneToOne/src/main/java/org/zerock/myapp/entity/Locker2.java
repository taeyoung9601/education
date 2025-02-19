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
@Data

@Entity
@EntityListeners(CommonEntityLifecycleListener.class)

//@Table(name)
public class Locker2 implements Serializable{			//Target, 1, Parent
	@Serial private static final long serialVersionUID = 1L;

	// 1. PK 속성 선언
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "locker_id")
	private Long id;
	
	// 2. 일반 속성 선언
	@Basic(optional = false)
	private String name;	
	
	// 3. 연관관계 매핑
	// 1:1 (OneToOne), Bi-direction , Target
	
//	@OneToOne						// 1 : 연관관계 주인설정을 안함 => 내가 연관관계의 주인 ( 즉 ,fk를 가진 child입니다)
									// => xx
	@OneToOne(mappedBy= "locker")	// 2 : 양방향일 때에는, 반드시 역방향에서 연관관계 주인을 설정
	private Student2 student;		// 역방향 정보를 저장할 필드
	
}// end class
