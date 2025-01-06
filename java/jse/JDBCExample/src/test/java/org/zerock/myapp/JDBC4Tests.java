package org.zerock.myapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.domain.EmpVO;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@Log4j2
@NoArgsConstructor
public class JDBC4Tests {
	

//	private static final String jdbcUrl = "jdbc:oracle:thin:@seoul?TNS_ADMIN=C:/u01/oracle";  // TNSNAMES
//	private static final String jdbcUrl = "jdbc:oracle:thin:@DESKTOP-14VLTEM:1521/seoul";  // EZCONNECT

	//When using log4jdbc
	private static final String jdbcUrl = "jdbc:log4jdbc:oracle:thin:@seoul?TNS_ADMIN=C:/u01/oracle";  // TNSNAMES
	private static String driverClass = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
	
//	private static final String driverClass = "oracle.jdbc.OracleDriver";
	private static final String user = "scott";
	private static final String pass = "oracle";
	
	private Connection conn;
	
	@BeforeAll
	void beforeAll() throws SQLException {  // Connection 객체 생성 & 필드에 저장
		log.debug("beforeAll() invoked.");
		
		this.conn = DriverManager.getConnection(jdbcUrl,user,pass);
		assert this.conn != null;
		log.info("\t + this.conn:{} ", this.conn);
		
	} // beforeAll
	
	@AfterAll
	void afterAll() {
		log.debug("afterAll() invoked.");
		
		Objects.requireNonNull(this.conn);
		
		try {this.conn.close();}
		catch (SQLException _ignored) {}
		finally {
			log.info("\t+this.conn.closed");
		} //try
		
	}// afterAll
	
//	@Disabled
	@Tag("JDBC-test")
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testSelectAllEmployees")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void testSelectAllEmployees() throws SQLException {
		log.debug("testSelectAllEmployees() invoked.");
		
		@Cleanup
		Statement stmt = conn.createStatement(); 	// Dynamic SQL
		assertNotNull(stmt);
		log.info("\t+ stmt : {}" , stmt);
		
		// 현업에서는, StringBuffer를 쓴다. 하지만 사용법은 아래와 동일
//		StringBuilder sb = new StringBuilder();
		
		String sql = """
				SELECT *
				FROM emp
				""";
		
		
		
		@Cleanup ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			// Caution : column name case-insensitive
			Integer empno = rs.getInt("empno");
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			Integer mgr = rs.getInt("mgr");
			java.util.Date hireDate = rs.getDate("hireDate");
			Double sal = rs.getDouble("sal");
			Double comm = rs.getDouble("comm");
			Integer deptno = rs.getInt("deptno");
			
			EmpVO vo = new EmpVO(empno,ename,job, mgr,hireDate,sal,comm,deptno);
			log.info("\t+ vo :{}" , vo);
			
		}// while
		
	} //testSelectAllEmployees
//	@Disabled
	@Tag("JDBC-test")
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testDriverSpy")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void testLog4jdbc() throws SQLException {
		log.debug("testLog4jdbc() invoked.");
		
		String sql = """
				SELECT * FROM emp WHERE sal > ? ORDER BY sal DESC
				""";
		
		
		
		@Cleanup PreparedStatement pstmt = this.conn.prepareStatement(sql);
		pstmt.setDouble(1, 3000.);
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		List<EmpVO> employees = new ArrayList<>();
		
		while(rs.next()) {
			// Caution : column name case-insensitive
			Integer empno = rs.getInt("empno");
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			Integer mgr = rs.getInt("mgr");
			java.util.Date hireDate = rs.getDate("hireDate");
			Double sal = rs.getDouble("sal");
			Double comm = rs.getDouble("comm");
			Integer deptno = rs.getInt("deptno");
			
			EmpVO vo = new EmpVO(empno,ename,job, mgr,hireDate,sal,comm,deptno);
			employees.add(vo);
			
		} // while
		
		 log.info("=".repeat(50));
	     employees.forEach(log::info);
	
	}// testLog4jdbc
	
//	@Disabled
	@Tag("JDBC-test")
	@Order(3)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testInsertNewEmployee")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void testInsertNewEmployee() throws SQLException {
		log.debug("testInsertNewEmployee() invoked.");
		
		//새로운 사원 추가 입력
		String sql = """
				INSERT INTO emp (EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO)
				VALUES (?,?,?,?,current_date,?,null,?)
				""";
		
		PreparedStatement pstmt = this.conn.prepareStatement(sql);
		pstmt.setInt(1, 8077);		//empno
		pstmt.setString(2, "Tae");		//ename
		pstmt.setString(3, "CLERK");		//job
		pstmt.setInt(4, 7369);		//mgr
		
		//hireDate - 1st. method
//		java.util.Date now = new java.util.Date();
//		pstmt.setDate(5, new java.sql.Date(now.getTime()));		
		
		pstmt.setDouble(5, 7777);	//sal
		
		// 1st. method
//		pstmt.setDouble(7, Types.DOUBLE);	//comm
		
		pstmt.setInt(6, 30); 		//deptno		
		
		//DML 문의 실행결과로, 몇개의 행이 영향을 받았는지
		//(즉, 몇개의 행이 업데이트-변경 되었는지를 반환)
		int affectedLines = pstmt.executeUpdate();	// If DML (CUD),
		assertEquals(1, affectedLines); 			// Auto-Validation
		
		log.info("\t+ affectedLines:{}", affectedLines);  // Eye Checking
		
	} //testSelectAllEmployees

	
	
} //end class
	
	

