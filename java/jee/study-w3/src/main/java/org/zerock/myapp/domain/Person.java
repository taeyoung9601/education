package org.zerock.myapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data

// JPA 처럼, @Entity란 어노테이션은 없습니다.
// 때문에, 아래의 @Table에 지정한 또는 지정하지 않으셔도 기본 테이블명이 클래스명을
// 따라가기 때문에, 먼저 테이블을 생성해 놓으셔야 합니다.(자동 테이블 생성 없음)
@Table(name = "PERSON")
public class Person {
	
	// 1. PK 속성
	@Id						// Set PK
	private Long id;		// generatedValue x => 수동 설정해야합니다.(중간기술이라 그렇습니다) 
	
	// 2. 일반속성
	@Column
	private String name;
	
	@Column
	private Integer age;
}// end class
