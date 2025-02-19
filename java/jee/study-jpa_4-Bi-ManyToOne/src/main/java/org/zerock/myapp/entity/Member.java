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
import lombok.extern.slf4j.Slf4j;

@Data

@Entity
@EntityListeners(CommonEntityLifecycleListener.class)
@Slf4j
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
	// N(Member, FK) : 1(Team , PK), Bi-direction (Member -> Team)
	
	@ManyToOne				// FK 속성 선언
	@JoinColumn(name = "my_team")	// 매핑될 테이블의 FK 컬럼명을 지정 , 기본 값으론 부모테이블이름_참조한 PK컬럼이름
	private Team myTeam;		// FK , getMyteam() 을 하면 조인을 하여 정보출력
						
	// 연관관계 편의 메소드 선언
	public void setMyTeam(Team newTeam) {
		log.debug("setMyTeam() invoked.");
		
		// step1. 현재 회원의 소속팀을 getter로 얻어내고
		Team oldTeam = this.getMyTeam();		// 현재 소속된 팀을 얻어내고
		
		// step2. 얻어낸 소속팀이 있다면
		if(oldTeam != null) {				// 이미 소속된 팀이 있으면
			// 기존 소속팀에서 이 팀원을 제거하고
			boolean isRemoved = oldTeam.getMembers().remove(this);		// (1) 이전팀에서 현재 회원 삭제
			log.info("\t + isRemoved from old team? : {} ", isRemoved);
			log.info("oldTeam: {}", oldTeam.getMembers());
			
		}
		
		// step3. 새로운팀으로 설정
		this.myTeam = newTeam;
		// step4. 새로운팀의 멤버로 등록
		this.myTeam.getMembers().add(this);					// (2) 새로운 팀으로 현재 회원 추가
		log.info("newTeam:{}",this.myTeam.getMembers());
	}
	
}// end class
