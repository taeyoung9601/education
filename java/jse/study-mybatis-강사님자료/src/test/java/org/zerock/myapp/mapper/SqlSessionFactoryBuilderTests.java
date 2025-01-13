package org.zerock.myapp.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@Log4j2
@NoArgsConstructor
public class SqlSessionFactoryBuilderTests {		
	private SqlSessionFactoryBuilder builder;
	private InputStream inputStream;					// for mybatis-config.xml
	
	
	@BeforeAll
	void beforeAll() throws SQLException, IOException {
		log.debug("beforeAll() invoked.");
		
		// Step1. Create a SqlSessionFactoryBuilder Object.
		this.builder = new SqlSessionFactoryBuilder();
		if(this.builder != null) {
			log.info("this.builder: {}", this.builder);
		}
		
		// Step2. Build a SqlSessionFactory Object with SqlSessionFactoryBuilder.
		// 마이바티스가 제공하는 Helper Class 인 Resources 의 정적메소드를 이용하여,
		// 마이바티스 설정파일을 읽을 수 있게 해주는 InputStream 객체를 쉽게 만들 수
		// 있습니다.
//		InputStream is = Resources.getResourceAsStream("/path/to/mybatis-config.xml");
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		
		Objects.requireNonNull(is);
		log.info("is: {}", is);
		
		this.inputStream = is;
	}
	
	
	@Disabled
	@Tag("OracleDataSource")
	@Order(0)
	@Test
//	@RepeatedTest(n)
	@DisplayName("contextLoads")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void contextLoads() {
		log.debug("contextLoads() invoked.");

		// Step2. Step1 에서 생성한 설정을 기반으로 CP 생성
		// Step3. Unit Test Method 에서, Step2에서 생성한 CP 사용법 획득
	}
	
	
//	@Disabled
	@Tag("SqlSessionFactoryBuilder")
	@Order(1)
	@Test
//	@RepeatedTest(n)
	@DisplayName("testSqlSessionFactoryBuilderUnderDevelopementEnv")
	@Timeout(value=10L, unit=TimeUnit.SECONDS)
	void testSqlSessionFactoryBuilderUnderDevelopementEnv() {
		log.debug("testSqlSessionFactoryBuilderUnderDevelopementEnv() invoked.");

		// Step1. 필드에 저장한 InputStream과 SqlSessionFactoryBuilder를 통해서,
		//            SqlSessionFactory (공장) 객체 생성
		// (Note) 앱당 공장은 하나면 충분합니다. 왜요? 공장이니까...얼마든지 상품을
		//             생산할 수 있으니까요...
		SqlSessionFactory factory = this.builder.build(inputStream);
		
		// null 검증 방법 3가지: 
		//  (1) assert 언어 키워드 				<--- JAVA 언어가 제공하는 키워드
		//  (2) Objects.requireNonNull()		<--- JAVA 표준라이브러리가 제공
		//  (3) assertNutNull()						<--- JUnit 이 제공
		assertNotNull(factory);
		log.info("1. factory: {}", factory);
		
		IntStream.rangeClosed(1, 100).forEach(seq -> {
			// Step2. SqlSessionFactory (공장)으로부터, SqlSession 객체 얻기
			//            참고: 이 SqlSession 객체는 역시 "자원객체" 입니다.
			@Cleanup SqlSession sqlSession = factory.openSession();
			
			assert sqlSession != null;
			log.info("[%03d]. sqlSession: %s".formatted(seq, sqlSession));
			
			// Step3. SqlSession객체로부터, 내부에 보관하고 있는 JDBC Connection객체 얻기
			// (*주의*) 아래와 같이, SqlSession 객체 안에 보관되어 있는 JDBC Connection을
			// 얻어낼 수는 있지만, 직접 조작해서도 안되고, 아래와 같이 꺼내서도 안됩니다!
			Connection conn = sqlSession.getConnection();
			log.info("\t+ conn: {}", conn);			
		});
	}
	
	
//	@Disabled
	@Tag("SqlSessionFactoryBuilder")
	@Order(2)
	@Test
//	@RepeatedTest(n)
	@DisplayName("testSqlSessionFactoryBuilderUnderProductionEnv")
	@Timeout(value=10L, unit=TimeUnit.SECONDS)
	void testSqlSessionFactoryBuilderUnderProductionEnv() {
		log.debug("testSqlSessionFactoryBuilderUnderProductionEnv() invoked.");
		
		// Case1 - Choose production environment of the mybatis configuration
//		SqlSessionFactory factory = this.builder.build(this.inputStream);
		
		// Case2 - Choose an environment to use in the mybatis configuration with code.
		SqlSessionFactory factory= this.builder.build(this.inputStream, "production");
		
		IntStream.rangeClosed(1, 100).forEach(seq -> {
			@Cleanup SqlSession sqlSession = factory.openSession();
			
			assert sqlSession != null;
			log.info("[%03d]. sqlSession: %s".formatted(seq, sqlSession));
			
			Connection conn = sqlSession.getConnection();
			log.info("\t+ conn: {}", conn);
		}); // forEach
	}

}
