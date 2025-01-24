package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data

// 엔티티 이름은 
//		(1) 생성될 테이블명이 되고, 
//		(2) JPA의 JPQL이란 SQL언어와 상당히 비슷한 문장에 사용됨.
@Entity(name = "member2")

@Table(name = "MyUser2")
public class Member2 implements Serializable {
	@Serial private static final long serialVersionUID = 1L;
	
	// 1. PK 속성 매핑
	@Id @GeneratedValue
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
	
	// 4. 이 엔티티의 생명주기에 따른 상태변화를 알려주는 자동 콜백 선언
	// 	  (1) @PostLoad 					<-- called back automatically by em.find
//		  (2) @PrePersist (3) @PostPersist 	<-- called back automatically by em.persist
//		  (4) @PreUpdate (5) @PostUpdate 	<-- called back automatically by em.updated
//		  (6) @PreRemove (7) @PostRemove	<-- called back automatically by em.remove

	@PostLoad
	void postLoad() {
		log.debug("postLoad() invoked.");
	}
	
	@PrePersist
	void prePersist() {
		log.debug("prePersist() invoked.");
	}
	
	@PostPersist
	void postPersist() {
		log.debug("postPersist() invoked.");
	}
	
	@PreUpdate
	void preUpdate() {
		log.debug("preUpdate() invoked.");
	}
	
	@PostUpdate
	void postUpdate() {
		log.debug("postUpdate() invoked.");
	}
	
	@PreRemove
	void preRemove() {
		log.debug("preRemove() invoked.");
	}
	
	@PostRemove
	void postRemove() {
		log.debug("postRemove() invoked.");
	}
	
} // end class


