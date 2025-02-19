package org.zerock.myapp.di;

/**
 * 스프링 컨테이너에 클래스를 빈으로 등록시켜주는 일련의 어노테이션들을
 * 스테레오타입 어노테이션이라 하고, 아래의 것들이 해당됩니다:
 * 더불어서, 해당 클래스의 역할까지 정의하게 됩니다.
 * 
 * @Component
 * @Controller
 * @RestController
 * @Service
 * @Respository
 * @Confiuration
 */

import lombok.Data;

@Data

//@Component
public class Animal {		// 스프링 컨테이너에 등록안된 빈
	;;
}
