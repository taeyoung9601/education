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
import javax.persistence.ManyToOne;

import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import lombok.Data;

@Data

@Entity
@EntityListeners(CommonEntityLifecycleListener.class)
public class Member implements Serializable{
	@Serial static final long serialVersionUID = 1L;
	
	// 1. PK 속성 매핑
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;
	
	// 2. 일반 속성 매핑
	@Basic(optional = false)
	private String name;	
	
	// 3. 연관관계 매핑
	// N(Member, FK) : 1(Team , PK), Uni-direction (Member -> Team)
	
	@ManyToOne				// FK 속성 선언
	@JoinColumn(name = "my_team")	// 매핑될 테이블의 FK 컬럼명을 지정 , 기본 값으론 부모테이블이름_참조한 PK컬럼이름
	private Team team;		// FK , getMyteam() 을 하면 조인을 하여 정보출력
						
}
