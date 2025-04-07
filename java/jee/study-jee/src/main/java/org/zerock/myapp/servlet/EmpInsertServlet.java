package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.zerock.myapp.domain.EmpDTO;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@WebServlet(name = "EmpInsertServlet", urlPatterns = "/EmpInsert")
public class EmpInsertServlet extends HttpServlet {				// MVC 패턴의 Controller 역할
	@Serial private static final long serialVersionUID = 1L;

	@Resource(type =DataSource.class, name = "jdbc/mysql/scott")
	private DataSource dataSource;
	
	// throw 절 가지면안돼, 정적이면 안돼
	@PostConstruct
	void postConstruct()  {
		log.debug("postConstruct() invoked.");
		
		//--1---
		log.info("\t1. this.dataSource1: {}", this.dataSource);

		
		//--2---
		try {
		@Cleanup Connection conn1 = this.dataSource.getConnection();
		log.info("\t + conn1: {} " , conn1);
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
		
		try {
			// 요청처리
			//--1
			req.setCharacterEncoding("utf8");
			final String empno = req.getParameter("empno");
			final String ename = req.getParameter("ename");
			final String sal = req.getParameter("sal");
			final String deptno = req.getParameter("deptno");
			
			//--2
			// DTO Pattern : Data Transfer Object
			// 웹 3계층의 Presentation Layer에서 전송된 모든 전송파라미터들의 데이터를
			// Mutable(수정가능한) 객체로 만드는 디자인 패턴으로, 웹 3계층의 앞-> 뒤로
			// 요청의 Input Data를 전달하는 용도의 클래스를 만드는 디자인 패턴입니다.
			// lombok은 이를 위해, @Data 란 어노테이션 하나로 클래스를 DTO로 만듧니다.
			// (1) 모든 필드는 private 으로 선언
			// (2) 각 필드에 대해서, Getter/Setter 모두 선언
			// (3) 중복판정 2단계 알고리즘에 대응
			// (4) (optional) Serializable 해야 합니다.
			// (5) 결국, "자바빈즈(Javabean)"규약대로 선언합니다.
			
			EmpDTO dto = new EmpDTO();
			dto.setEmpno(Integer.valueOf(empno));
			dto.setEname(ename);
			dto.setSal(Double.valueOf(sal));
			dto.setDeptno(Integer.valueOf(deptno));
			
			log.info("\t + dtp: {} ", dto);
			
			//--3------
			
			@Cleanup Connection conn = this.dataSource.getConnection(); 	// MySQL
			final String sql = "INSERT INTO emp(empno, ename, sal, deptno) VALUES(?,?,?,?)";
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,dto.getEmpno());
			pstmt.setString(2,dto.getEname());
			pstmt.setDouble(3,dto.getSal());
			pstmt.setInt(4,dto.getDeptno());
			
			int affectedRows = pstmt.executeUpdate();				// MVC 패턴의 model
			log.info("\t + affectedRows : {}", affectedRows);

			// 2. 응답 처리---
			
			res.setCharacterEncoding("UTF-8");
			
			res.setContentType("text/html; charset=UTF-8");
			
			@Cleanup PrintWriter out = res.getWriter();
			
			if(affectedRows == 1) out.println("<h3>성공</h3>");
			else out.println("<h3>실패</h3>");
			
			out.flush();
			
		}catch(Throwable t) {
			throw new IOException(t);
		}// try
		
	}// service
	

}// end class
