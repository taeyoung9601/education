package org.zerock.myapp.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
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

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import oracle.jdbc.pool.OracleDataSource;


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@Log4j2
@NoArgsConstructor
public class OracleDataSourceTests {
	private static final String jdbcUrl = "jdbc:oracle:thin:@seoul?TNS_ADMIN=C:/u01/oracle";
	private static final String user = "scott";
	private static final String pass = "oracle";
	private static final String dataSourceName = "Yoseph-Pool";
	
	private OracleDataSource ods;
	
	
	@BeforeAll
	void beforeAll() throws SQLException {
		log.debug("beforeAll() invoked.");
		
		OracleDataSource ods = new OracleDataSource();
		
		assertNotNull(ods);
		
		// --- 1. JDBC 4대정보 설정 -----------
		ods.setURL(jdbcUrl);
		ods.setUser(user);
		ods.setPassword(pass);

		// --- 2. CP 설정 -----------
		ods.setDataSourceName(dataSourceName);		// Optional, Pool Name
		ods.setDescription("Oracle Data Source with Oracle JDBC Driver");	// Optional, CP Description
		ods.setLoginTimeout(2);							// Optional, seconds, but required.
		ods.setDriverType("thin");						// Optional, (1) Thin (2) OCI
		ods.setDatabaseName("seoul");				// Optional, Database Name to use.
		ods.setExplicitCachingEnabled(true); 	// Optional, default is true. Whether using cache for performace.
		
		this.ods = ods;
		log.info("ods: {}", ods);
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
	@Tag("OracleDataSource")
	@Order(1)
	@Test
//	@RepeatedTest(n)
	@DisplayName("testSingleTurn")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testSingleTurn() throws SQLException {
		log.debug("testSingleTurn() invoked.");

		// ODS 는 일반적인 CP와 다르게, 시스템 자원이 허용하는 한, 
		// 매번 신규로 연결(Connection) 을 생성하기 때문에,
		// 사실상, CP의 장점이 없습니다.
		try (Connection conn = this.ods.getConnection()) {
			log.info("conn: {}", conn);
		}
	}
	
	
//	@Disabled
	@Tag("OracleDataSource")
	@Order(2)
	@Test
//	@RepeatedTest(n)
	@DisplayName("testMultiTurn")
	@Timeout(value=10L, unit=TimeUnit.SECONDS)
	void testMultiTurn() throws SQLException {
		log.debug("testMultiTurn() invoked.");

		// HikariCP 에 비해서, ODS가 얼마나 느린지 콘솔로그로 확인이 가능할 정도입니다.
		IntStream.rangeClosed(1, 100).forEach(seq -> {
			try (Connection conn = this.ods.getConnection()) {
				log.info("[%03d]. conn: %s".formatted(seq, conn));
			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch
		});
	}
	
	
	
	

}
