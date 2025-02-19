package org.zerock.myapp.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@NoArgsConstructor
public class CommonEntityLifecycleListener {
	
	// 
	// 이 클래스는, Managed 상태의 모든 엔티티의 개별 상태가 변경될 때마다,
	// 이미 정해진 콜백을 수행시키고, 그 때에 상태가 변경된 엔티티 객체를 콜백의
	// 매개변수로 받을 수 있는 리스너입니다.
	// 사용되는 어노테이션은, 개별 엔티티 내부에서 사용된 콜백 어노테이션과 동일한 7개
	// 입니다 : @PostLoad,@PrePersist,@PostPersist,@PreUpdate,@PostUpdate,@PreRemove,@PostRemove
	
	@PostLoad
	void postLoad(Object entity) {
		log.debug("postLoad({}) invoked.", entity);
	}
	
	@PrePersist
	void prePersist(Object entity) {
		log.debug("prePersist({}) invoked.", entity);
	}
	@PostPersist
	void postPersist(Object entity) {
		log.debug("postPersist({}) invoked.", entity);
	}
	
	@PreUpdate
	void preUpdate(Object entity) {
		log.debug("postPersist({}) invoked.", entity);
	}
	@PostUpdate
	void postUpdate(Object entity) {
		log.debug("postUpdate({}) invoked.", entity);
	}
	
	@PreRemove
	void preRemove(Object entity) {
		log.debug("preRemove({}) invoked.", entity);
	}
	@PostRemove
	void postRemove(Object entity) {
		log.debug("postRemove({}) invoked.", entity);
	}
}// end class
