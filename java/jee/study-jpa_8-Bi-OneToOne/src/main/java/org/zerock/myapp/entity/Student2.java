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
import javax.persistence.OneToOne;

import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Data

@Entity
@EntityListeners(CommonEntityLifecycleListener.class)

//@Table(name)
public class Student2 implements Serializable {			//Main, 1, FK(Child)
	@Serial private static final long serialVersionUID = 1L;
	
	// 1. PK 속성 선언
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id")
	private Long id;
		
	// 2. 일반 속성 선언
	@Basic(optional =false)
	private String name;
	
	// 3. 연관관계 매핑
	// 1:1 (OneToOne), Bi-direction , Main
	@ToString.Exclude
	
//	@OneToOne															// 1
	@OneToOne(targetEntity = Locker2.class) 							// 2
//	@OneToOne(targetEntity = Locker2.class, optional = false ) 			// 3 : 필수참여 (즉, Null 허용안됨)
//	@OneToOne(targetEntity = Locker2.class, cascade =CascadeType.ALL) 	// 4
	
//	@JoinColumn															// 1
//	@JoinColumn(name = "my_locker") 									// 2
//	@JoinColumn(name = "my_locker", referencedColumnName = "locker_id")	// 3
	@JoinColumn(name = "my_locker", referencedColumnName = "locker_id", 
				unique = true)	// 4 : FK 값이 고유해야 함
	private Locker2 locker;		// FK
	
	// 연관관계 편의 메소드 선언
	public void setLocker(Locker2 locker) {
		log.debug("setLocker({}) invoked.", locker);
		
		if(locker != null) {
			this.locker = locker;
			
			// 역방향의 학생 필드에 현재 학생 설정
			// 즉, 사물함을 사용하는 학생 설정
			this.locker.setStudent(this);
		}
	}
	
}// end class
