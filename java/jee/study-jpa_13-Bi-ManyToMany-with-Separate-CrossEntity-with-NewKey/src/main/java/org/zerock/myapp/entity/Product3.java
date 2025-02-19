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
public class Product3 implements Serializable{
	@Serial static final long serialVersionUID = 1L;
	
	// 1. PK 속성 선언
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	
	// 2. 일반 속성 선언
	@Basic(optional = false)			// Not Null Constraint
	private String name;
	
	
	// 3. 연관관계매핑
	// N:M , Bi-direction, One(1)
	// 계속 중요: 양방향일 때에는, 부모 엔티티에 "연관관계 주인설정" 필요
	@ToString.Exclude
	@OneToMany(mappedBy = "productFK", targetEntity = Orders.class)	// 속성값 : 자식 (many)엔티티 클래스의 FK필드명 설정
	private List<Orders>myOrders = new ArrayList<>();
	
	
	
}//end class
