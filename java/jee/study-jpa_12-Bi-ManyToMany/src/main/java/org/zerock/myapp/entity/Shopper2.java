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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Data
@Slf4j

@Entity
@EntityListeners(CommonEntityLifecycleListener.class)
public class Shopper2 implements Serializable{
	@Serial private static final long serialVersionUID = 1L;
	@SequenceGenerator(name = "ShopperGenerator", sequenceName = "seq_shopper1", initialValue = 1, allocationSize = 1)
	
	// 1. PK 속성 선언
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "ShopperGenerator")
	@Column(name = "shopper_id")
	private Long id;
	
	// 2. 일반 속성 선언
	@Basic(optional = false)	// Not Null Constraint
	private String name;
	
	// 3. 연관관계매핑
	// N:N, 이진 다대다 관계, Bi-direction, (R1)
	
	@ManyToMany(targetEntity = Product2.class, fetch = FetchType.LAZY)	// 지연로딩
	@JoinTable(
			// 자동으로 만들어진 교차 테이블의 이름 지정
		name = "shopper2_product2",					// 관계타입명: "주문" 또는 "구매"
		// R1의 PK를 FK로 가져옴
		joinColumns = { @JoinColumn(
			name = "shopper_id", 					// R1 PK를 참조하는 FK 컬럼명 지정
			referencedColumnName = "shopper_id",	// R1 PK 컬럼명 지정
			nullable = false
		)},
		// R2의 PK를 FK로 가져옴
		inverseJoinColumns = {@JoinColumn(
			name = "product_id", 					// R2 PK를 참조하는 FK 컬럼명 지정
			referencedColumnName = "product_id",	// R2 PK 컬럼명 지정
			nullable = false
		)}
	)
	List<Product2> products = new ArrayList<>();
	
	
	// 연관관계 매핑 편의 메소드 선언
	public void addOrder(Product2 newProduct) {
		log.debug("addOrder({}) invoked.", newProduct);
		
		// 양방향일 때에는, 양방향 관계를 모두 커버하게
		// 만들어야 합니다. -> 주의 : 그런데, 다대다 관계에서는 이게 과연 맞을까요? ***
		// 다대다 양방향 매핑일 때, 다른 연관관계 매핑과 다르게
		// 다대다 단방향 매핑만 고려해도, 교차 테이블에 양쪽 릴레이션 정보가 
		// 잘 들어가기 때문에, 굳이 양방향을 고려해서 편의 메소드를 만들지 않아도 됩니다.
		if(Objects.nonNull(newProduct)) {
			this.products.add(newProduct);			
		}else return;
	}
	
}
