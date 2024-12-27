package org.zerock.myapp;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

//import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

//@TestInstance(Lifecycle.PER_METHOD)  // 1st . JUnit4와 비슷
@TestInstance(Lifecycle.PER_CLASS)   // 2nd. 기본생성자 1번생성

@TestMethodOrder(OrderAnnotation.class) // 단위 테스트 메소드의 실행순서를 @Order 어노테이션으로 제어

@Log4j2
//@NoArgsConstructor

public class JUnit5Template {
	
	public JUnit5Template() {
		log.debug("Default Constructor invoked.");
	}
	//JUnit 4와 동일하게, 테스트 클래스는 POJO이어야 합니다.
	// 선택적으로 java.io.Serializable 인터페이스를 구현할 수도 있습니다.
	// 클래스 선언부 위에, 2개의 어노테이션으로,
	// 가. 테스트 인스턴스 생성전략 설정 
	// 나. 1개 이상의 단위 테스트 메소드의 실행순서를 제어 전력을 설정(@TestMethodOrder)
	
	
	/*
	 * 아래 어노테이션들의 수행순서:
	 *  1. 처음 수행시: @BeforeAll ->@BeforeEach ->@Test ->@AfterEach -> @AfterAll 
	 *  2. 2번째 수행시 부터: @BeforeEach -> @Test ->@AfterEach
	 * @BeforeAll //1회성 전처리
	 * 
	 * @BeforeEach //단위 테스트 메소드마다 수행되는 전처리
	 * 
	 * @Test //단위 테스트 메소드
	 * 
	 * @AfterEach //단위 테스트 메소드마다 수행되는 후처리
	 * 
	 * @AfterAll
	 */			//1회성 후처리
	
	//각 어노테이션 별 메소드는, 아래와 같이, 리턴 값 없고 매개변수 없는것은 JUnit 4와 동일
	// (주의)하지만, 더이상 무조건 public 접근 제한자를 가질 필요는 없습니다.
	
	@BeforeAll 
//	static                                // if only 
	void beforeAll() {
		log.debug("beforeAll() invoked.");
	}
	
	@BeforeEach void beforeEach() {
		log.debug("beforeEach() invoked.");
	}
	
//	@Disabled  // 테스트에서 제외 (비활성화)
	@Tag("이체")  // 테스트에 태깅(비행기탈 때, 여행 가방에 붙이는 태그와 비슷)
	@Order(1)  //테스트 순서 결정
	@Test // 단위 테스트 메소드임을 설정
//	@RepeatedTest(3) // 단위 테스트의 반복 수행 횟수 지정 가능
	@DisplayName("Step1. 본인확인인증 ") // JUnit view 에 표시될 테스트 명 설정
	@Timeout(value=1L, unit=TimeUnit.SECONDS) // 타임 아웃시간 설정(단위까지 정밀하게)
	void contextLoad1() {
		log.debug("contextLoad1() invoked.");
	}
	
//	@Disabled  // 테스트에서 제외 (비활성화)
	@Tag("이체")  // 테스트에 태깅(비행기탈 때, 여행 가방에 붙이는 태그와 비슷)
	@Order(2)  //테스트 순서 결정
	@Test // 단위 테스트 메소드임을 설정
//	@RepeatedTest(3) // 단위 테스트의 반복 수행 횟수 지정 가능
	@DisplayName("Step2. 계좌유효성 검증") // JUnit view 에 표시될 테스트 명 설정
	@Timeout(value=1L, unit=TimeUnit.HOURS) // 타임 아웃시간 설정(단위까지 정밀하게)
	void contextLoad2() {
		log.debug("contextLoad2() invoked.");
	}
	
//	@Disabled  // 테스트에서 제외 (비활성화)
	@Tag("이체")  // 테스트에 태깅(비행기탈 때, 여행 가방에 붙이는 태그와 비슷)
	@Order(3)  //테스트 순서 결정
	@Test // 단위 테스트 메소드임을 설정
//	@RepeatedTest(3) // 단위 테스트의 반복 수행 횟수 지정 가능
	@DisplayName("Step3. 계좌잔고 검증 ") // JUnit view 에 표시될 테스트 명 설정
	@Timeout(value=1L, unit=TimeUnit.SECONDS) // 타임 아웃시간 설정(단위까지 정밀하게)
	void contextLoad3() {
		log.debug("contextLoad3() invoked.");
	}
	
//	@Disabled  // 테스트에서 제외 (비활성화)
	@Tag("이체")  // 테스트에 태깅(비행기탈 때, 여행 가방에 붙이는 태그와 비슷)
	@Order(4)  //테스트 순서 결정
	@Test // 단위 테스트 메소드임을 설정
//	@RepeatedTest(3) // 단위 테스트의 반복 수행 횟수 지정 가능
	@DisplayName("Step4. 계좌이체수행 ") // JUnit view 에 표시될 테스트 명 설정
	@Timeout(value=1000L * 2, unit=TimeUnit.MILLISECONDS) // 타임 아웃시간 설정(단위까지 정밀하게)
	void contextLoad4() {
		log.debug("contextLoad4() invoked.");
	}
	
	
	@AfterEach void afterEach() {
		log.debug("afterEach() invoked.");
	}
	
	@AfterAll 
//	static
	void afterAll() {
		log.debug("afterAll() invoked.");
	}
	
	
	
	
}//end class
