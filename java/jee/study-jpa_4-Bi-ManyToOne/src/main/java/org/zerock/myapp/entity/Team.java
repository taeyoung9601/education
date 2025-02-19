package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import lombok.Data;
import lombok.ToString;

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
	// N(Member, FK) : 1(Team , PK), Bi-direction (Member -> Team)
	// If bi-directional mapping, set the owner of the association definitely with mappedBy property
	
	// mappedBy = "자식엔티티의 FK필드명" 설정으로 , "연관관계주인설정"
	// (1) JPA 구현체가 아래 컬렉션 객체를 자동으로 만들어 주지 않고
	// (2) 또한, 한 팀에 속한 모든 회원 엔티티 객체를 컬렉션에 넣어주지도 않습니다! (***)
	// 	   준비해야 합니다. 이 메소드를 JPA에서는 " Association Convenient Method" (편의 메소드)라고 합니다.
	@OneToMany(mappedBy = "myTeam")						// Parent(N) -> Child(Many) 매핑
	@ToString.Exclude
	private List<Member> members = new ArrayList<>();
	
}// end class
