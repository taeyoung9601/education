package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data

// 이 클래스가 JPA가 매핑할 관계형 테이블로 매핑될 클래스임
//@Entity	// 엔티티 이름을 어노테이션 속성으로 지정하지 않으면, default로 클래스이름이 엔티티의 이름이 됨

// Entity 이름이 생성될 테이블의 이름이 된다!!
// 엔티티의 이름은 여러분이 후에 배우실 JPQL 문 작성에 사용됩니다
// 그러니, 테이블명을 지정하실 때에는 아래와 같이 @Table 어노테이션으로
// 명시적으로 지정하시는게 좋습니다.
@Entity(name = "MyUser")	// 클래스 이름 말고, 직접 엔티티 이름 작성

// 이 엔티티와 매핑되어 실제 생성될 테이블 이름을 설정할 수가 있습니다.
// 이 어노테이션을 사용하지 않으면 기본적으로 엔티티이름이 테이블 명
@Table(name = "member")	// 매핑될 테이블 명 -> MEMBER
//@Table(name = "user")	// 매핑될 테이블 명 -> USER
public class Member implements Serializable{
	@Serial private static final long serialVersionUID = 1L;
	
	// 1. PK 속성 선언 & 매핑
	@Id							// 키 속성 지정 어노테이션
	private String id;
	
	//2. 일반 속성 선언 & 매핑
	private String name;		// 일반 속성1
	private Integer age;		// 일반 속성2
	
	// 3. 연관관계 매핑 선언 (1:1, 1:N , N:1, N:M)
	
	
}// end class
