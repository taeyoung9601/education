package org.zerock.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.zerock.myapp.util.Utils;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JDBCExample3 {
	
	private static final String user = "HR";
	private static final String pw = "oracle";
	private static final String jdbcUrl = "jdbc:oracle:thin:@seoul? TNS_ADMIN=C:/u01/oracle/";
//	private static final String driver = "oracle.jdbc.OracleDriver";
	public static void main(String[] args) throws SQLException {
		@Cleanup
		Connection conn = DriverManager.getConnection(jdbcUrl,user,pw);
		
//		final String sql = "SELECT * FROM employees WHERE salary >=? AND job_id = ?";
		final String sql = 
				new StringBuffer()
					.append("SELECT *")
//					.append("\t")
					.append(" FROM ")
					.append("employees")
					.append(" WHERE")
					.append("\t")
					.append("salary >= ? AND job_id= ?")
					.toString();
		
		log.info(sql);
		@Cleanup
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setDouble(1, 5000.0);
		pstmt.setString(2, "IT_PROG");
		
		@Cleanup
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			final int employeeId = rs.getInt("employee_id");
			final String lastName = rs.getString("LAST_NAME");
			final double salary = rs.getDouble("salary");
			final String job_id = rs.getString("job_id");
			
			Utils.formatToConsole("{0},{1},{2},{3}", employeeId, lastName, salary, job_id);
			
		}
		
		

	}

}
