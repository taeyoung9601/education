package org.zerock.myapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)


//@RequiredArgsConstructor
@SpringBootTest
class StudySpringDiApplicationTests {
	
	// 인텔리제이에서 이거만 사용가능.
//	@Setter(onMethod_ = {@Resource})	// Setter메소드의 매개변수에 주입
//	@Setter(onMethod = {@Autowired})		//@Until 7
	@Setter(onMethod_ = {@Autowired})		//@Since 8	: De facto Standard
	
//	@Inject				// XX
//	@Resource(type = Chef.class)				// 사용 가장 좋음!
//	@Autowired(required = false) // Optional?
//	private Chef chef;
//	private Hotel hotel;
	
//	private Car car;		// 스프링 컨테이너에 없는 빈을 주입해달라! NoSearchBeanDefinitionException
	
	// 주입 가능한 빈의 타입(다형성-1) : Animal,Dog,Cat
//	private Animal animal;			// XX : NoUniqueBeanDefinitionException
	
//	@Autowired private Cat cat;		// ok
//	@Autowired private Dog dog;		// ok
	
	@Autowired(required = true)		// 주입할 빈이 없어도 오류없이 널 반환
	private ApplicationContext ctx;	// Spring Container(= Spring Context)
	
//	@Autowired
//	public void setChef() {
//		log.debug("setChef({}) invoked.",chef);
//		
//		this.chef = chef;
//	}
	
//	@Order(1)
//	@Test
//	void contextLoads() {
//		log.debug("contextLoads() invoked.");
//		
//		Objects.requireNonNull(this.animal);
//		log.info("\t + this.hotel : {}", this.animal);
//	}

	@Order(2)
	@Test
	@DisplayName("2. testSpringContext")
	void testSpringContext() {
		log.debug("testSpringContext() invoked.");
		
		assertNotNull(this.ctx);
		log.info("\t + this.ctx:{}", this.ctx);
		
		String[] beanNames = this.ctx.getBeanDefinitionNames();
		Arrays.asList(beanNames).forEach(beanName -> log.info(beanName.toString()));
	}
}
