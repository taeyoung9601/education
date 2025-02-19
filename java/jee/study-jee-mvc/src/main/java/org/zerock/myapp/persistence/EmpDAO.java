package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.domain.EmpVO2;

import lombok.Cleanup;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

// DAO pattern : Data Access Object (DB같은 데이터 저장소에 대한 접근을 수행하는 객체)
// (1) 이전 계층인 비지니스 계층의 서비스가 요구하는 DB조작 메소드를 구현합니다.
// (2) 기본 생성자를 가져야 한다.
// (3) POJO 기반입니다.
// (4) JPA, MyBatis, JDBC api 등 선택한 라이브러리/프레임워크를 이용해서,
// 	   DB조작 메소드를 구현하면 됩니다.

@Slf4j
//@NoArgsConstructor
@ToString

public class EmpDAO {	// POJO
	
	// 아래의 어노테이션을 이용한, 서블릿 컨테이너가 생성/관리하는 리소스에 대한 주입은
	// 서블릿 컨테이너에 의해 관리/수행되는 서블릿 클래스에서만 가능합니다(****)
	// DAO 클래스는 서블릿 컨테이너에 의해서 관리되는 클래스가 아니기 때문에,
	// 아래와 같이 주입받을 수가 없습니다.
	// 그럼 어떻게 하면, WAS에서 생성된 다양한 종류의 자원객체를 일반 클래스에서도 얻어내
	// 사용할 수가 있을까? -> JNDI service (Java Naming & Directory Service)
	
//	@Resource(name = "dataSourceName", type = DataSource.class)  // XX: 불가능 -> JNDI Lookup 필요
	
	private final String jndiLookupPrefix = "java:comp/env/";
	
//	private final String dataSourceJDNIName = "jdbc/h2/seoul";	// h2
	private final String dataSourceJDNIName = "jdbc/oracle/seoul";	// oracle
//	private final String dataSourceJDNIName = "jdbc/mysql/scott";	// mysql
	
	private DataSource dataSource;
	
	// 영속성 계층의 
	
	public EmpDAO() throws NamingException {
		log.debug("Default Constructor invoked.");
		
		// JNDI Lookup을 통해, 우리가 원하는 이름을 가진 데이터소스 획득
		
		try {
			//--1---
			Context ctx = new InitialContext();	// JNDI tree 의 ROOT에 해당
			log.info("\t1. ctx: {}", ctx);
			
			//--2---
			// JNDI lookup 수행
			// 실제자원이름: java:comp/env/jdbc/mysql/scott
			Object obj = ctx.lookup(jndiLookupPrefix + dataSourceJDNIName);
			Objects.requireNonNull(obj);
			
			if(obj instanceof DataSource dataSource) {
				this.dataSource = dataSource;
				log.info("\t2. this.dataSource:{}", this.dataSource);
		}// if
		}catch(Exception e) {
			// Original 예외의 메시지를 추출해서, JNDI 예외로 던짐
			throw new NamingException(e.getMessage());
		}// try -catch
		
	}// Default Constructor
	
	
	public boolean modify(EmpDTO dto) throws SQLException{
		log.debug("remove({}) invoked.", dto);
		
		//--1---
		@Cleanup Connection conn = this.dataSource.getConnection();
		log.info("\t + conn:{}", conn);
		
		//--2---
		final String sql = "UPDATE emp SET ename=?, job=?,mgr=?,hiredate=?,sal=?,comm=?,deptno=? WHERE empno =?";
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		if(dto.getEname() != null )pstmt.setString(1, dto.getEname());
		else pstmt.setNull(1, Types.VARCHAR);
		
		if(dto.getJob() != null) pstmt.setString(2, dto.getJob());
		else pstmt.setNull(2, Types.VARCHAR);
		
		if(dto.getMgr() != null) pstmt.setInt(3, dto.getMgr());
		else pstmt.setNull(3, Types.INTEGER);
		
		// 1개의 소스 안에, 패키지만 틀린 이름같은 2개의 클래스를 사용해야합니다ㅣ.
//		(0)(dto.getHireDate().getTime());
		
		if(dto.getHireDate() != null) pstmt.setDate(4, new java.sql.Date(dto.getHireDate().getTime()));
		else pstmt.setNull(4, Types.DATE);
		
		if(dto.getSal() != null) pstmt.setDouble(5, dto.getSal());
		else pstmt.setNull(5, Types.DOUBLE);
		
		if(dto.getComm() != null) pstmt.setDouble(6, dto.getComm());
		else pstmt.setNull(6, Types.DOUBLE);
		
		if(dto.getDeptno() != null) pstmt.setInt(7, dto.getDeptno());
		else pstmt.setNull(7, Types.INTEGER);
		
		pstmt.setInt(8, dto.getEmpno());
		
		int affectedRows = pstmt.executeUpdate();	// For DML
		
		return affectedRows ==1;
			
	}// modify
	
	
	
	
	public boolean create(EmpDTO dto) throws SQLException{
		log.debug("remove({}) invoked.", dto);
		
		//--1---
		@Cleanup Connection conn = this.dataSource.getConnection();
		log.info("\t + conn:{}", conn);
		
		//--2---
		final String sql = "INSERT INTO emp VALUES(?,?,?,?,?,?,?,?)";
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, dto.getEmpno());
		
		if(dto.getEname() != null )pstmt.setString(2, dto.getEname());
		else pstmt.setNull(2, Types.VARCHAR);
		
		if(dto.getJob() != null) pstmt.setString(3, dto.getJob());
		else pstmt.setNull(3, Types.VARCHAR);
		
		if(dto.getMgr() != null) pstmt.setInt(4, dto.getMgr());
		else pstmt.setNull(4, Types.INTEGER);
		
		// 1개의 소스 안에, 패키지만 틀린 이름같은 2개의 클래스를 사용해야합니다ㅣ.
//		(0)(dto.getHireDate().getTime());
		
		if(dto.getHireDate() != null) pstmt.setDate(5, new java.sql.Date(dto.getHireDate().getTime()));
		else pstmt.setNull(5, Types.DATE);
		
		if(dto.getSal() != null) pstmt.setDouble(6, dto.getSal());
		else pstmt.setNull(6, Types.DOUBLE);
		
		if(dto.getComm() != null) pstmt.setDouble(7, dto.getComm());
		else pstmt.setNull(7, Types.DOUBLE);
		
		if(dto.getDeptno() != null) pstmt.setInt(8, dto.getDeptno());
		else pstmt.setNull(8, Types.INTEGER);
		
		int affectedRows = pstmt.executeUpdate();	// For DML
		
		return affectedRows ==1;
		
	}// create
	
	
	
	public boolean remove(Integer empno) throws SQLException {
		log.debug("remove({}) invoked.", empno);
		
		//--1---
		@Cleanup Connection conn = this.dataSource.getConnection();
		log.info("\t + conn:{}", conn);
		
		//--2---
		final String sql = "DELETE FROM emp WHERE empno =?";
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, empno);
		
		int affectedRows = pstmt.executeUpdate();	// For DML
		
		return affectedRows ==1;
		
	}// remove
	
	
	//매개변수로 받은 사원번호로 사원 상세조회
	public EmpVO2 findByEmpno(Integer empno) throws SQLException {
		log.debug("findByEmpno({}) invoked.", empno);
		
		//--1---
		@Cleanup Connection conn = this.dataSource.getConnection();
		log.info("\t + conn:{}", conn);
		
		//--2---
		final String sql = "SELECT * FROM emp WHERE empno = ?";
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, empno);
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		//--3----
		
		if(rs.next()) {
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			Integer mgr = rs.getInt("mgr");
			
			Date hiredate = rs.getDate("hiredate");
			
			Double sal = rs.getDouble("sal");
			Double comm = rs.getDouble("comm");
			Integer deptno = rs.getInt("deptno");
			
			EmpVO2 vo = new EmpVO2(empno,ename,job,mgr,hiredate,sal,comm,deptno);
			
			return vo;
		}else {

		return null;
		}
		
	}// findByEmpno
	
	public List<EmpVO2> findAllEmps() throws SQLException{	// CRUD + LIST -> List =>전체사원목록조회 
		log.debug("findAllEmps() invoked.");
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		log.info("\t + conn:{}", conn);
		
		// Prepared SQL : DB Optimizr(Engine)가 단 1번만 파싱하고, 이 정보를 재사용
		// 				  이게 JDBC api에서는, PreparedStatement 객체로 표현
		// Dynamic SQL: 아무리 SQL문이 같아도, 매번 파싱하고, 이정보를 1회성 사용
		// 				  이게 JDBC api에서는, Statement 객체로 표현
		
		final String sql = "SELECT * FROM emp ORDER BY empno DESC";
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
//		@Cleanup Statement stmt = conn.createStatement();
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		//--3----
		
		// 이 컬렉션이 현재 요청에 대한 비지니스 데이터인 "Model"이 되는 것입니다(***)
//		List<EmpVO> list = new ArrayList<>();	// xx: 실무에서 안 사용
		List<EmpVO2> list = new Vector<>();		// Ok: Thread-Safe
		
		while(rs.next()) {
			Integer empno = rs.getInt("empno");			// Auto-boxing
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			Integer mgr = rs.getInt("mgr");
			
			Date hiredate = rs.getDate("hiredate");
			
			Double sal = rs.getDouble("sal");
			Double comm = rs.getDouble("comm");
			Integer deptno = rs.getInt("deptno");
			
			EmpVO2 vo = new EmpVO2(empno,ename,job,mgr,hiredate,sal,comm,deptno);
			list.add(vo);
		}// while
		
		//--4---
		return list;
		
	}//findAllEmps
	
}// end class
