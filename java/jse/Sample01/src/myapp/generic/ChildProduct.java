package org.zerock.myapp.generic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//자식 객체를 출력할 때, 상속된 부모 객체도 함께 super란 이름의 필드로
// 출력이 됩니다.
@ToString(callSuper = true)

@NoArgsConstructor

public class ChildProduct<K,M,C> extends Product<K, M> {
	@Getter @Setter
	private C company; // 제품의 제조회사명
	
	
}//end class
