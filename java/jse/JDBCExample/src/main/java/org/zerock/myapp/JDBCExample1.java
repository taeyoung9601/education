package org.zerock.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JDBCExample1 {
	//JDBC 접속을 위한 4대 정보 선언
	private static final String user = "HR";
	private static final String pw = "oracle";
	private static final String jdbcUrl = "jdbc:oracle:thin:@seoul? TNS_ADMIN=C:/u01/oracle/";
	private static final String driver = "oracle.jdbc.OracleDriver"; // 더 이상 구동기 클래스의 로딩은 JDBC 표준 4부터 필요 없음
	
	
	public static void main(String[] args)  {
		log.debug("main({}) invoked.", Arrays.toString(args));
	
		//JDBC 3대 인터페이스 참조변수 선언
//		Connection conn = null;
//		PreparedStatement pstmt = null;   //자원객체
//		ResultSet rs = null;				/// 자원객체
		
		//step1. Get connection with DriverManager.getConnection() method
		try{
			//effectively final, so final keyword not necessary
			Connection conn = DriverManager.getConnection(jdbcUrl,user,pw); 
			
			//STEP 2.
//			Statement stmt = null; 			//Dynamic SQL 방식의 SQL 문장
			
			//Prepared SQL 방식의 SQL 문장
			String sql = "SELECT * FROM employees WHERE salary >= ?"; // ? : Bind Variable
			
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			//STEP 3.binding value to the specific bind variable
			pstmt.setDouble(1, 10000.); // binding value to the specific bind variable
			
			//STEP 4.Excute the DQL with binding & Receive a Result Set
			ResultSet rs = pstmt.executeQuery();   // Throw the Prepared SQL to the Oracle to be executed
			//Local variable 'conn' defined in an enclosing scope must be final or effectively final
			try(conn; pstmt; rs;){
				
				@Cleanup("clear")
				List<String> list = new ArrayList<>();
				
				
				//STEP 5. Extract all column value by each row.
				while(rs.next()) {
				String employeeId  = rs.getString("EMPLOYEE_ID");
				String lastName  = rs.getString("LAST_NAME");
				String salary  = rs.getString("SALARY");
				
				String row = "employeeId(%s), lastName(%s), salary(%s)".formatted(employeeId,lastName,salary);
				
				list.add(row); // Add a new row into the list.
				
				}//while
				
				list.forEach(log::info);  // Print all items of a list to console.
			}//try-with-resources
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
	} // main

}// end class
