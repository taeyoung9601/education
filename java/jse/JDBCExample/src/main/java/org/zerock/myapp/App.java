package org.zerock.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Hello world!
 *
 */
@Log4j2
@NoArgsConstructor

public class App {
	private static final String user = "HR";
	private static final String pw = "oracle";
	//DB vendor 마다, 자기의 문서에 아래의 JDBC URL을 어떻게 지정해야 하는지가
	//나옵니다. 이유는, 이것까지 모든 것을 표준화시키지는 않았기 때문에,
	//DB 벤더마다 약간씩 차이가 납니다. 그럼 당연히 오라클은 문서를 보고
	//어떻게 JDBC Url 을 지정하는지 찾아봐야죠..그런데, 찾아봤다고 치고...
	// 아래와 같이 지정하시면 됩니다.
	//private static final String jdbcUrl = "jdbc:<DB벤더명>: ----"; 여기까지만 표준화되어 있고, 나머지 벤더마다
//	private static final String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/seoul"; //EZConnect 방식
//	private static final String jdbcUrl = "jdbc:oracle:thin:@seoul"; // TNSNAME 방식 - tnsnames.ora 파일의 위치를 알수 없다.
	private static final String jdbcUrl = "jdbc:oracle:thin:@seoul? TNS_ADMIN=C:/u01/oracle/";
	
//	private static final String driver = "oracle.jdbc.driver.OracleDriver"; // old
	private static final String driver = "oracle.jdbc.OracleDriver"; // new
	
    public static void main( String[] args ) throws SQLException {
    	log.debug("main({}) invoked.",Arrays.toString(args));
    	
    	//Step1. 위에 JDBC 4대 변수 정의
    	
    	//Step2. JDBC Driver Class Loading with java.lang.Class.forName(driver)
//    	Class.forName(driver);  // SKipped - No required
    	
    	//Step3.
    	@Cleanup
    	Connection conn = DriverManager.getConnection(jdbcUrl, user, pw);
    	log.info("\t1.conn:{}",conn);
    	
    	//Step4.SQL 문장 객체를 획득 from Step2 Connection 객체의 메소드로..
    	Statement stmt = conn.createStatement();
    	log.info("\t2.stmt:{}",stmt);
    	
    	//Step5. SQL 문장을 수행시키고, 결과 셋을 수신
    	final String sql = "SELECT * FROM employees";  //DB로 던질 sql 문장
    	
    	@Cleanup
    	ResultSet rs = stmt.executeQuery(sql);  // Result 객체: 이것도 자원 객체
    	log.info("\t3.rs:{}",rs);
    	
    	//Step6. step5 의 ResultSet 객체와 while 문을 이용하여, 각 행의 데이터 출력
    	while(rs.next()) { // ResultSet.net 메소드 : 이 메소드가 호출될 때마다, 결과셋의 다름 레코드로 이동
    		//자동으로 다음 행으로 이동되었고, 이 행의 각 컬럼 값 추출
    		//각 컬럼별로 컬럼 값을 얻어내기 위해서는, 테이블 스키마에 있는 컬럼 명으로 얻어냅니다.
    		//때문에, 테이블 스키마(구조)를 알아야겠죠!?
    		
    		String employeeId = rs.getString("EMPLOYEE_ID");
    		String lastName = rs.getString("last_name");
    		String email = rs.getString("EMAIL");
    		String hire_date = rs.getString("hire_date");
    		
    		log.info("{},{},{},{}",employeeId,lastName,email,hire_date);
    	}

    	
    	
    }
}