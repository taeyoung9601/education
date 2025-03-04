package org.zerock.myapp.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
// 이 어노테이션을 붙여놔야, 테스트 메소드 수행 시,
// 스프링부트 앱이 함께 구동되어, 웹 3계층의 요소를 테스트할 수 있게 합니다
@SpringBootTest
//public : public 접근제한자가 붙으면 파일명과 같은 클래스만 허용
class DataSourceTests {
	@Autowired(required = false)
	private javax.sql.DataSource dataSource;
	
	@BeforeAll
	void beforeAll() throws SQLException {		// 테스트 시 try-catch로 직접 오류 처리하지말고 throws 사용
		log.debug("beforeAll() invoked.");
		
		assertNotNull(this.dataSource);
		log.info("this.dataSource : {}", this.dataSource);
		
		@Cleanup Connection conn =this.dataSource.getConnection();
		log.info("\t + conn: {}", conn);
	}// beforeAll

	
//	@Disabled
	@Tag("Data-Source-Test")
	@Order(0)
	@Test
//	@RepeatedTest(1)
	@DisplayName("contextLoads")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void contextLoads() {
		log.debug("contextLoads() invoked.");
		
	}// contextLoads
	

}// end class
