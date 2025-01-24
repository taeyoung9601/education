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
import javax.persistence.Table;

import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data

// 엔티티 이름은 
//		(1) 생성될 테이블명이 되고, 
//		(2) JPA의 JPQL이란 SQL언어와 상당히 비슷한 문장에 사용됨.

// Important : entity name "member" must be unique in the persistence unit.
@Entity(name = "member3")
@EntityListeners(CommonEntityLifecycleListener.class)

@Table(name = "MyUser3")
public class Member3 implements Serializable {
	@Serial private static final long serialVersionUID = 1L;
	
	// 1. PK 속성 매핑
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)	// strategy : 키 자동생성 전략 설정(기본: AUTO)
	@Column(name = "seq")
	private Long id;
	
	// 2. 일반속성 매핑
	@Basic
	private String name;
	@Column
	private Integer age;
	
	private Double weight;
	
	
	// 3. 다른 엔티티 간의 연관관계 매핑
	//     (1:1, 1:N, N:1, N:N)
	
	
} // end class


