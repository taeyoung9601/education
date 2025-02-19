package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import lombok.Data;

@Data
@Entity
@EntityListeners(CommonEntityLifecycleListener.class)

public class Product1 implements Serializable{
	@Serial private static final long serialVersionUID = 1L;
	@SequenceGenerator(name = "ProductGenerator", sequenceName = "seq_product1", initialValue = 1, allocationSize = 1)
	// 1. PK 속성 선언
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "ProductGenerator")
	@Column(name = "product_id")
	private Long id;
	
	// 2. 일반 속성 선언
	@Column(nullable = false)	// Not Null Constraint
	private String name;
	
	// 3. 연관관계매핑
	// N:N, 이진 다대다 관계, Uni-direction, (R2)
}
