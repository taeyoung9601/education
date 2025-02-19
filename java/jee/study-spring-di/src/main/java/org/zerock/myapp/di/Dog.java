 package org.zerock.myapp.di;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

//Generating equals/hashCode implementation but without a call to superclass,
//even though this class does not extend java.lang.Object.
//If this is intentional, add '@EqualsAndHashCode(callSuper=false)' to your type.

// 상속관계에 있는 자식 클래스에 중폭 판정 알고리즘 대응을 위해,
// @EqualsAndHashCode 어노테이션을 붙일 때에는, 아래와 같이,
// Object.equals와 Object.hashCode 부모 메소드를 호출하지 못하도록 하셔야 합니다.
@EqualsAndHashCode(callSuper = false)
@Data

@Component
public class Dog extends Animal {		// 자식타입
	;;
}
