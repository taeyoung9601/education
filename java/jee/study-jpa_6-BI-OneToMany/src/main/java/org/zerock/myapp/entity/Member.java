package org.zerock.myapp.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
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
	// 읽기 전용이 되어야 된다.
	// 양방향에서, 원방향은 엔티티 수정이 가능하나, 추가되는 반대 방향은 무조건 "읽기 전용"이 되어야 합니다! (***)
	
	//중요 : 유일하게 이 어노테이션에만 mappedBy 속성이 없음
	// 		이는 양방향일 때, 연관관계의 주인을 밝힐 수 없다!! 가 아니라,
	//		"밝힐 이유가 없다!!" 왜? 내가 FK(연관관계의 주인)이니까..
	
	@ManyToOne(
			// (1) 주어진 FK 관계가, " 필수 "(x>=1) 이냐 "선택" (x>=0) 인지
			// 즉, FK 필드의 값에 NULL을 허용할건지 안할건지 설정
			optional= true,
			// (2) 연관관계의 상대편 엔티티 타입정보 설정
			targetEntity = Team.class,
			// (3) 이 엔티티를 em.find로 조회했을 떄, 아래 필드의 값을 바로 채워넣을까요? 예스!
			// 즉시로딩 (Eager Loading) <-> 지연 로딩 (Lazy Loading)
			fetch = FetchType.EAGER,
			// (4) 부모 엔티티가 삭제될 때, 이 자식 엔티티 같이 "연쇄삭제"시킬게요
			cascade = CascadeType.ALL
			)
	@JoinColumn(
			// FK 컬럼 선언
			name= "my_team", 
			// 부모의 pk컬럼
			referencedColumnName = "team_id", 
			//읽기전용
			insertable= false, updatable = false)
	private Team myTeam;	// FK : 읽기전용으로 만들어야 함

	
	
}// end class
