package org.zerock.myapp.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.jupiter.api.BeforeAll;
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
import org.zerock.myapp.domain.BoardDTO;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UsingExernalDataSourceTests {
	// (1) With pure JDBC driver
	private static final String jdbcUrl = "jdbc:oracle:thin:@seoul?TNS_ADMIN=C:/u01/oracle";
	private static final String driver = "oracle.jdbc.OracleDriver";
	
	// (2) With log4jdbc (Driver Spy)
//	private static final String jdbcUrl = "jdbc:log4jdbc:oracle:thin:@seoul?TNS_ADMIN=C:/u01/oracle";
//	private static final String driver = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
	
	private static final String user = "scott";
	private static final String pass = "oracle";
	
	private SqlSessionFactory factory;
	
	
	@BeforeAll
	void beforeAll() throws IOException {
		log.debug("beforeAll() invoked.");
		
		// Step1. HikariDataSource's configuration
		HikariConfig config = new HikariConfig();
		
		config.setJdbcUrl("jdbc:oracle:thin:@SEOUL?TNS_ADMIN=C:/u01/oracle");	// TNSNAMES
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setUsername("scott");
		config.setPassword("oracle");

		config.setAutoCommit(true);	
		config.setPoolName("Yoseph-Pool");
		config.setMaximumPoolSize(10);
		config.setMinimumIdle(3);
		config.setConnectionTimeout(1000L * 1);
		config.setIdleTimeout(1000L * 1800);
		config.setMaxLifetime(1000L * 1800);
		config.setConnectionTestQuery("SELECT 1 FROM dual");
		config.setConnectionInitSql("SELECT 1 FROM dual");
		
		// Step2. Create a HikariDataSource with above configuration.
		HikariDataSource hds = new HikariDataSource(config);
		
		// Step3. Create a SqlSessionFactory with external Hikari Data Source.
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory = builder.build(inputStream);
		
		// ---------------------------------------
		// 프로그램 코드로, 동적으로 마이바티스의 새로운 실행환경 생성
		// ---------------------------------------
		// SqlSessionFactory로부터, 
		// 
		// Step1. 설정객체(Configuration)를 얻고
		// Step2. Step1에서 얻은 설정객체의 .setEnvironment(Environment) 메소드로
		//            동적으로 마이바티스의 실행환경을 생성합니다.
		factory
			.getConfiguration()
			.setEnvironment(
				// SqlSessionFactory 가 사용할 새로운 실행환경 생성
				new Environment(
						"EnviromentWithHikariDataSource",		// 새로운 실행환경의 이름 설정
						new JdbcTransactionFactory(), 				// 새로운 실행환경의 TX관리자 생성
						hds)														// 새로운 실행환경의 데이터소스 설정
			);	// .setEnvironment
		
		this.factory = factory;
	}
	
	@Disabled
	@Tag("UsingExternalHikariDataSource")
	@Order(0)
	@Test
//	@RepeatedTest(1)
	@DisplayName("contextLoads")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void contextLoads() {
		log.debug("contextLoads() invoked.");
		
	}
	
//	@Disabled
	@Tag("UsingExternalHikariDataSource")
	@Order(1)
//	@Test
	@RepeatedTest(1000)
	@DisplayName("testExternalHikariDataSource")
	@Timeout(value=3L, unit=TimeUnit.SECONDS)
	void testExternalHikariDataSource() {
		log.debug("testExternalHikariDataSource() invoked.");
		
		final String mappedStatement = 
			"%s.%s".formatted("org.zerock.myapp.mapper.BoardMapper3", "findBoardsByBnoOrTitle");
		
		SqlSession sqlSession = this.factory.openSession();	// autocommit OFF
		
		// Map or Java Bean => BoardDTO is already a Java Bean.
		BoardDTO params = new BoardDTO();		// Thus, called Data Transfer Object (DTO)
		params.setBno(7);
		params.setTitle("TLE_1");
		
		// DTO 는 웹3계층(Presentation, Business, Persistence Layers)에서 전방위적으로
		// 사용되는 목적의 수정가능한(with Setter) 데이터 전달 객체이고,
		// VO 는 웹3계층에서 아래 순서로 데이터를 전달하는 용도로 만든 읽기전용의
		// 데이터 전달객체를 의미합니다:
		//   (1) 영속성계층 -> (2) 비지니스 계층 -> (2) 프리젠테이션 계층
		List<BoardDTO> list =	 //  Thus, called Data Transfer Object (DTO)
			sqlSession.<BoardDTO>selectList(mappedStatement, params);
		
		log.info("=".repeat(50));
		list.forEach(log::info);
	}

}


