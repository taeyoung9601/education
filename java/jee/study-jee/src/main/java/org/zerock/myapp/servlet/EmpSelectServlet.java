package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import org.zerock.myapp.domain.EmpVO;

import com.google.gson.Gson;

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

@WebServlet(name = "EmpSelectServlet", urlPatterns = "/EmpSelect")
public class EmpSelectServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	@Resource(type =DataSource.class, name = "jdbc/h2/seoul")
	private DataSource dataSource1;
	
	@Resource(name = "jdbc/oracle/seoul")
	private DataSource dataSource2;
	
	@Resource(type =DataSource.class, name = "jdbc/mysql/scott")
	private DataSource dataSource3;
	
	// throw 절 가지면안돼, 정적이면 안돼
	@PostConstruct
	void postConstruct()  {
		log.debug("postConstruct() invoked.");
		
		//--1---
		log.info("\t1. this.dataSource1: {}", this.dataSource1);
		log.info("\t1. this.dataSource2: {}", this.dataSource2);
		log.info("\t1. this.dataSource3: {}", this.dataSource3);
		
		//--2---
		try {
		@Cleanup Connection conn1 = this.dataSource1.getConnection();
		log.info("\t + conn1: {} " , conn1);
		
		@Cleanup Connection conn2 = this.dataSource2.getConnection();
		log.info("\t + conn2: {} " , conn2);
		
		@Cleanup Connection conn3 = this.dataSource3.getConnection();
		log.info("\t + conn3: {} " , conn3);
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
		
		try {
			@Cleanup Connection conn = this.dataSource3.getConnection(); 	// MySQL
			@Cleanup Statement stmt = conn.createStatement();
			@Cleanup ResultSet rs = stmt.executeQuery("SELECT * FROM emp ORDER BY empno DESC");
			
			// VO패턴: 특정 테이블의 한개의 행 데이터를 읽기 전용으로 저장하는 객체를 만드는 패턴으로,
			//        웹 3계층에서 가장 마지막 계층인, "영속성 계층"에 속한 패턴으로, 패턴 규칙에 맞게 만들어야합니다.
			//        이를 위해서
			// 		  (1) lombok은 이 패턴을 자동으로 지켜주는 @Value 어노테이션을 사용해서 간단하게 만들 수 있습니다.
			//     	  (2) 자바 레코드 (Record) 클래스로 선언
			
//			List<EmpVO> list = new Arraylist<>();	// Thread-unsafe
			List<EmpVO> list = new Vector<>();		// Thread-safe
			
			while(rs.next()) {
				Integer empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				Integer mgr = rs.getInt("mgr");
				Date hireDate = rs.getDate("hireDate");
				Double sal = rs.getDouble("sal");
				Double comm = rs.getDouble("comm");
				Integer deptno = rs.getInt("deptno");
				
				EmpVO vo =new EmpVO(empno,ename,job,mgr,hireDate,sal,comm,deptno);
				list.add(vo);
			}// while
			
			list.forEach(vo -> log.info(vo.toString()));
//			list.forEach(log::info);
			
			//-- 2. 응답처리
			// 		UI를 생성하는 계층이, 웹 3계층의 "Presentation Layer"입니다.
			// 		이 응답처리 부분 이후에는, JSP Template Engine 으로 역할이 넘어갑니다.
			res.setCharacterEncoding("UTF-8");
			
			// 응답 문서로 기존의 동적 HTML를 생성하지 않고,
			// React 같은 Front 화면에는, JSON or XML 문설를 전달하는게 일반적이기 때문에
			// 이번에는 JSON으로 변환하여 전달합니다.(즉, List Collection -> JSON으로 변환)
			res.setContentType("application/json; charset=UTF-8");
			
			@Cleanup PrintWriter out = res.getWriter();
			
			Gson gson = new Gson();
			final String json = gson.toJson(list);
			
			out.print(json);
			
			out.flush();
			
		}catch(Throwable t) {
			throw new IOException(t);
		}// try
		
	}// service
	

}// end class
