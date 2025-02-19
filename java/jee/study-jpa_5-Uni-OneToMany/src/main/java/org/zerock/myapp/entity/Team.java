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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import lombok.Data;

@Data

@Entity
//@Entity(name = "Team")

@EntityListeners(CommonEntityLifecycleListener.class)

@Table(name = "team")
public class Team implements Serializable{	// Parent, (1)
	@Serial private static final long serialVersionUID = 1L;
	
	// 1. PK 속성 선언
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "team_id")
	private Long id;
	
	// 2. 일반 속성 선언
//	@Column(nullable = false)
	@Basic(optional = false)	// Not Null Constraint
	private String name;
	
	// 3. 연관 관계 매핑
	// 1: N, OneToMany, Uni-directional Mapping
	
	// JPA 는 연관관계 매핑에 의해서 나올 수 밖에 없는 필드 중에,
	// 아래와 같은 Many에 해당되는 컬렉션을 자동생성해주지 않고,
	// 그 안의 멤버도 자동으로 관리해주지 않습니다.
	// 전적으로 아래 컬렉션은, 개발자가 알아서 연관관계에 맞게 직접
	// "편의 메소드"를 통해서 관리해야 합니다.
	// 예외) 지금 연관관계는 JPA 구현체가 요소는 관리해야 합니다.
	
//	@OneToMany(mappedBy= "연관관계주인의 필드명 설정") // 양방향일 때만 지정
//	@OneToMany
	@OneToMany(targetEntity = Member.class)
	
//	@JoinColumn
//	@JoinColumn(name = "my_team")
	@JoinColumn(name = "my_team", referencedColumnName="team_id")
	private List<Member> members = new ArrayList<>();
	
	
	// 회원의 소속팀이 언제든 변경될 수 있다는 것을 전제로 깔아놓고,
	// 팀에 새로운 회원을 저장하는 "연관관계 편의메소드"를 직접 선언해 보세요!
	// 필요한 로직 : 이전 팀 소속에서 제거 => 새로운 팀의 소속으로 삽입
	// 연관관계 편의 메소드 선언
	public void set() {
		// 1. 이전 소속이 있다면 제거
		// 2. 새로운 팀의 소속으로 삽입
	}
	
} // end class
