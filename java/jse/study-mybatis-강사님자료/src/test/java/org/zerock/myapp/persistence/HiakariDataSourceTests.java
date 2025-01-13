package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

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

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@Log4j2
@NoArgsConstructor
public class HiakariDataSourceTests {
	private HikariConfig config;
	
	
	@BeforeAll
	void beforeAll() {
		log.debug("beforeAll() invoked.");
		
		// HikariCP의 설정객체 생성과 다양한 설정
		// Step1. HikariCP의 설정 생성
		HikariConfig config = new HikariConfig();
		
		// -- 1. JDBC 4대 정보 설정 ------------
//		config.setJdbcUrl("jdbc:oracle:thin:@호스트명:포트번호/DB명");	// EZCONNECT
//		config.setJdbcUrl("jdbc:oracle:thin:@TNS별칭?TNS_ADMIN=/path/to/tnsnames.ora");	// TNSNAMES
		
		config.setJdbcUrl("jdbc:oracle:thin:@DESKTOP-RU3KK4F:1521/seoul");		// EZCONNECT
//		config.setJdbcUrl("jdbc:oracle:thin:@SEOUL?TNS_ADMIN=C:/u01/oracle");	// TNSNAMES
		
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setUsername("scott");
		config.setPassword("oracle");

		// -- 2. CP자체에 대한 설정 ------------
		config.setAutoCommit(true);							// 자동커밋설정
		config.setPoolName("Yoseph-Pool");			// CP의 이름
		config.setMaximumPoolSize(10);					// CP의 최대크기
		config.setMinimumIdle(3);								 // CP의 최소크기
		config.setConnectionTimeout(1000L * 1);		// 1초안에 연결생성이 되어야 함
		config.setIdleTimeout(1000L * 1800);			// 30분동안 사용이 안되면 연결파괴
		config.setMaxLifetime(1000L * 1800);			// 한 커넥션의 생명유지시간(30분)
		config.setConnectionTestQuery("SELECT 1 FROM dual");	// 커넥션의 유효성 검증 쿼리
		config.setConnectionInitSql("SELECT 1 FROM dual");	// 커넥션 생성 직후, 최초수행쿼리
				
		this.config = config;
		log.info("\t+ this.config: {}", this.config);
	}
	
	
	@Disabled
	@Tag("HikariDataSource")
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
	@Tag("HikariDataSource")
	@Order(1)
	@Test
//	@RepeatedTest(n)
	@DisplayName("testSingleTurn")
	@Timeout(value=2L, unit=TimeUnit.SECONDS)
	void testSingleTurn() throws SQLException {
		log.debug("testSingleTurn() invoked.");

		// Step2. Step1 에서 생성한 설정을 기반으로 CP 생성
		HikariDataSource hds = new HikariDataSource(this.config);
		assert hds != null;
		log.info("+ hds: {}", hds);
		
		// Step3. Unit Test Method 에서, Step2에서 생성한 CP 사용법 획득 (***)
		// (1) CP 에서 하나의 Connection 대여 요청: hds.getConnection();
		// (2) CP 에서 대여받은 Connection의 반납 : conn.close();
		try (hds; Connection conn = hds.getConnection();) {
			log.info("+ conn: {}, type: {}", conn, conn.getClass().getName());
		} // try-with-resources
	}
	
	
//	@Disabled
	@Tag("HikariDataSource")
	@Order(2)
	@Test
//	@RepeatedTest(n)
	@DisplayName("testMultiTurn")
	@Timeout(value=10L, unit=TimeUnit.SECONDS)
	void testMultiTurn() {
		log.debug("testMultiTurn() invoked.");

		// Step2. Step1 에서 생성한 설정을 기반으로 CP 생성		
		@Cleanup HikariDataSource hds = new HikariDataSource(this.config);
		
		Objects.requireNonNull(hds);
		log.info("hds: {}", hds);
				
		// Step3. Multi-Turn 수행
		IntStream.rangeClosed(1, 1000).forEach(seq -> {
			try (Connection conn = hds.getConnection();) {
				log.info("[%03d]. conn: %s".formatted(seq, conn));
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch
		});
	}

}
