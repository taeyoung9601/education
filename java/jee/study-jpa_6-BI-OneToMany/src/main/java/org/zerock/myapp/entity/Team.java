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
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Data

@Entity
//@Entity(name = "Team")

@EntityListeners(CommonEntityLifecycleListener.class)

@Table(name = "team")
public class Team implements Serializable{	// Parent, (1)
	@Serial private static final long serialVersionUID = 1L;
//	@SequenceGenerator(name = "MySequenceGenerator", sequenceName = "seq_team", initialValue =1, allocationSize =1)
	
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
	@ToString.Exclude // stackoverflow 방지
	@OneToMany
//	@OneToMany(targetEntity = Member.class)
	
//	@JoinColumn 													// 1. Not Recommended : FK가 2개 생김
//	@JoinColumn(name = "my_team")									// 2. Required
//	@JoinColumn (referencedColumnName = "team_id") 					// 3. Not Recommended : FK가 2개 생김
	@JoinColumn(name = "my_team", referencedColumnName = "team_id") // 4. Not Recommended : FK가 2개 생김
	private List<Member> members = new ArrayList<>();

	public void addMember(Member newMember) {
		log.debug("addMember({}) invoked.", newMember);
		
		// Step1. 이전 팀에서 소속 제거
		// List는 *중복허용*, 즉, 같은 회원이 2번 이상 추가될 수 있음.
		this.members.remove(newMember);
		
		// Step2. 새로운 팀으로 소속 시킴
		this.members.add(newMember);
		
		// Step3. 자식(팀원)의 FK필드에 현재 팀 설정 (FK필드가 있어야 함)
		// 새로운 팀원(Member)가 생성되어, 팀에 소속될 대, 자기의 팀(FK필드)을
		// 아래와 같이 설정해줘야, 이 팀원을 em에 persist(저장)할 때, 소속팀 설정을
		// 다시 할 필요가 없죠
		newMember.setMyTeam(this);
	}
	
} // end class
