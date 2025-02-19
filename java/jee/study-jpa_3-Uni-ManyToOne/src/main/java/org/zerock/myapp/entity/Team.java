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
public class Team implements Serializable{

	@Serial private static final long serialVersionUID = 1L;
	
	// 1. PK 속성 매핑
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
	private Long id;
	
	// 2. 일반 속성 매핑
	@Basic(optional = false)	// Not Null Constraint
	private String name;
	
	
	// 3. 연관관계 매핑
	// N(Member, FK) : 1(Team , PK), Uni-direction (Member -> Team)
	
	
}// end class
