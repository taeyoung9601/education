package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j

@Entity
@EntityListeners(CommonEntityLifecycleListener.class)
public class Shopper3 implements Serializable{
	@Serial static final long serialVersionUID = 1L;
	
	// 1. PK 속성 선언
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shopper_id")
	private Long id;
	
	// 2. 일반 속성 선언
	@Basic(optional = false)			// Not Null Constraint
	private String name;
	
	
	// 3. 연관관계매핑
	// N:M , Bi-direction, One(1)
	// 계속 중요: 양방향일 때에는, 부모 엔티티에 "연관관계 주인설정" 필요
	@ToString.Exclude
	@OneToMany(mappedBy = "shopperFK", targetEntity = Orders.class , fetch = FetchType.LAZY)	// 속성값 : 자식 (many)엔티티 클래스의 FK필드명 설정
	private List<Orders>myOrders = new ArrayList<>();
	
	
	// 연관 관계 편의 메소드(양방향 고려)
	public void addOrder(Orders newOrder){
		log.debug("addOrder({}) invoked.", newOrder);
		
		if(Objects.nonNull(newOrder) && Objects.nonNull(newOrder.getProductFK())
				&& Objects.nonNull(newOrder.getShopperFK())) {
			this.myOrders.add(newOrder);
			
		}
		
	}
	
	
}// end class
