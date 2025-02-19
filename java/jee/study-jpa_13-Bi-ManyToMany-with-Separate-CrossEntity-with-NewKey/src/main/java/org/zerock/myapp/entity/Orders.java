package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import lombok.Data;

//ERD에서, 다대다 관계로, "주문하다"에 대응되는 교차 엔티티 클래스

@Data
@Entity
@EntityListeners(CommonEntityLifecycleListener.class)
public class Orders implements Serializable{
	@Serial private static final long serialVersionUID = 1L;
	
	// 1. PK 속성 선언
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 현재 시점에서 가장 좋은 전략
	@Column(name = "orders_id")
	private Long id;
	
	// 2. 교차 테이블이 반드시 가져야 함.
	// 양쪽의 두 테이블의 PK을 가져온 FK가 선언되어야 합니다.
	
	// 2-1. Set T1 FK (M:1)
	// 이 어노테이션만이 유일하게 'mappedBy' 속성이 없습니다.
	// 이유는, 이 어노테이션은 FK필드에 붙이기 때문에
	// FK필드가 이미 연관관게의 "주인"이기 때문에 주인을 밝힐 이유가 없다.
	
	@ManyToOne									// 1. 
//	@ManyToOne(targetEntity = Shopper3.class, optional = false, cascade = CascadeType.ALL)	// 2.
	@JoinColumn(name = "shopper_id")
	private Shopper3 shopperFK;
	
	// 2-2. Set T2 FK (N:1)
	@ManyToOne
//	@ManyToOne(targetEntity = Product3.class, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")			// <--- FK 전용 어노테이션
	private Product3 productFK;
	
	// 2. 일반 속성 선언 - 여기서의 속성은 ERD의 관계타입에서 나오는 속성을 선언합니다.
	private Integer orderAmount;		// 주문 수량
	private Integer orderPrice;			// 총 주문 가격
	
	// 3. 정보통신망법에 따라, 중요 정보를 가진 테이블에는
	//   (1) 최조등록일시
	//   (2) 최종수정일시
	// 	위 두 정보를 저장할 필드가 반드시 있어야 합니다.
	@CreationTimestamp
	@Basic(optional = false)
	private Date createDate;			// 최초등록일시: 반드시 값이 들어가야 합니다. (Not Null)
	
	@UpdateTimestamp
	@Basic(optional = true)
	private Timestamp updateDate;		// 최종수정일시: 수정될 때에만 값이 갱신됩니다 (Null)
	
	
}// end class
