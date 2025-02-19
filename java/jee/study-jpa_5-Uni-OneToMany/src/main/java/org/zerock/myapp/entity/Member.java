package org.zerock.myapp.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import lombok.Data;

@Data

@Entity

@EntityListeners(CommonEntityLifecycleListener.class)

public class Member implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// 1. PK 속성 선언
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "member_id")
	private Long id;
	
	// 2. 일반 속성 선언
	@Basic(optional = false, fetch = FetchType.EAGER)	// 즉시모드 : 엔티티 객체 조회 시 바로 채워넣어라
	private String name;
	
	// 3. 연관관계 매핑
	// 1: N, OneToMany, Uni-directional Mapping
	
	// Do Nothing
	// No FK.

	
	
}// end class
