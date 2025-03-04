package org.zerock.myapp.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.domain.EmpVO;
import org.zerock.myapp.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

// 두 개의 기능을 수행하는 어노테이션
// (1) 이 클래스가 DAO 역할을 한다.
// (2) Spring Context에 빈으로 등록
// BeanNameAware 을 implements하면 빈에 등록되기전에 수행하는 setBeanName 메소드
@Repository("empDAO")
public class EmpDAOImpl implements EmpDAO, BeanNameAware{
	// 첫번째 방법 : jdbc api
// 	@Inject		// 의존성 라이브러리 추가
//	@Resource(name="주입받을 빈의 이름", type = 주입받을 빈의 타입 정보)
//  @Autowired
	@Setter(onMethod_=@Autowired)	// 기본적으로 이걸 써주는게 좋다.
	private javax.sql.DataSource dataSource;
	
	@Autowired private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<EmpVO> selectAllEmps() throws DAOException {
		log.debug("selectAllEmps() invoked.");
		
	
		try {
			/*
			// 1st. method: Using JDBC API.
			final String sql = "SELECT * FROM emp ORDER BY empno DESC";
			
			Connection conn = this.dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			try (conn;pstmt; rs;){
				
				if(rs.getFetchSize()>0) {
					List<EmpVO> list = new Vector<>();
					
					while(rs.next()) {
						final Integer empno = rs.getInt("empno");
						final String ename = rs.getString("ename");
						final String job = rs.getString("job");
						final Integer mgr = rs.getInt("mgr");
						final Date hireDate = rs.getDate("hireDate");
						final Double sal = rs.getDouble("sal");
						final Double comm = rs.getDouble("comm");
						final Integer deptno = rs.getInt("deptno");
						
						EmpVO vo = new EmpVO(empno, ename, job, mgr, hireDate, sal, comm, deptno);
						
						list.add(vo);
					}// while
					
					return list;
				}// if
				
				return null;
			} // try-with-resources
			*/
			
			// 2st method : Using Spring JDBC, jdbcTemplate
			final String sql = "SELECT * FROM emp ORDER BY empno DESC";
			List<EmpVO> list = this.jdbcTemplate.<EmpVO>query(sql,(rs,i) -> {
				log.debug("mapRow({},{}) invoked.", rs, i);
				
				final Integer empno = rs.getInt("empno");
				final String ename = rs.getString("ename");
				final String job = rs.getString("job");
				final Integer mgr = rs.getInt("mgr");
				final Date hireDate = rs.getDate("hireDate");
				final Double sal = rs.getDouble("sal");
				final Double comm = rs.getDouble("comm");
				final Integer deptno = rs.getInt("deptno");
				
				EmpVO vo = new EmpVO(empno, ename, job, mgr, hireDate, sal, comm, deptno);
				
				return vo;
			});// query
			
			return list;
			
		}catch(Exception _original) {
			throw new DAOException(_original);
		}
	} // selectAllEmps

	
	@Override
	public Boolean insertEmp(EmpDTO dto) throws DAOException {
		log.debug("insertEmp({}) invoked.", dto);
		
		try {
			/*
			final String sql = "INSERT INTO emp () VALUES (?,?,?,?,?,?,?,?)";
						
			Connection conn = this.dataSource.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpno());
			pstmt.setString(2, dto.getEname());
			pstmt.setString(3, dto.getJob());
			pstmt.setInt(4, dto.getMgr());
			
			// java.util.Date -> java.sql.Date 로 형변환 필요
			Date hireDate = Objects.requireNonNullElse(dto.getHireDate(),null);
			if(hireDate != null) pstmt.setDate(5, new java.sql.Date(dto.getHireDate().getTime()));
			else pstmt.setNull(5, Types.DATE);	// Set NULL data as each type.
			
			pstmt.setDouble(6, dto.getSal());
			pstmt.setDouble(7, dto.getComm());
			pstmt.setInt(8, dto.getDeptno());
			
			int affectedRows = pstmt.executeUpdate();
			
			return (affectedRows == 1);
			*/
			
			// 2nd method : Using Sping JDBC's JdbcTemplate Helper.
			final String sql = "INSERT INTO emp VALUES (?,?,?,?,?,?,?,?)";
			int affectedRows = this.jdbcTemplate.update(sql,
					dto.getEmpno(),dto.getEname(),dto.getJob(),dto.getMgr(),
					new java.sql.Date(dto.getHiredate().getTime()),
					dto.getSal(), dto.getComm(), dto.getDeptno()
					);
			
			return (affectedRows == 1);
			
		}catch(Exception _original) {
			throw new DAOException(_original);
		}// try-catch

	} // insertEmp

	
	@Override
	public EmpVO selectEmp(EmpDTO dto) throws DAOException {
		log.debug("selectEmp({}) invoked.", dto);
		
		try {
			/*
			final String sql =  "SELECT * FROM emp WHERE empno = ? ORDER BY empno DESC";
			
			Connection conn = this.dataSource.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpno());
			
			ResultSet rs = pstmt.executeQuery();
            try (conn;pstmt; rs;) {
		
				if(rs.next()) {
					final Integer empno = rs.getInt("empno");
					final String ename = rs.getString("ename");
					final String job = rs.getString("job");
					final Integer mgr = rs.getInt("mgr");
					final Date hireDate = rs.getDate("hireDate");
					final Double sal = rs.getDouble("sal");
					final Double comm = rs.getDouble("comm");
					final Integer deptno = rs.getInt("deptno");		
	
					EmpVO vo = new EmpVO(empno, ename, job, mgr, hireDate, sal, comm, deptno);
			        
					return vo;
				} else {
				return null;
				} // if-else
            }// try-resource
            */
			final String sql =  "SELECT * FROM emp WHERE empno = %d".formatted(dto.getEmpno());
					
			List<EmpVO> list = this.jdbcTemplate.<EmpVO>query(sql,(rs,i) ->{
				
				final Integer empno = rs.getInt("empno");
				final String ename = rs.getString("ename");
				final String job = rs.getString("job");
				final Integer mgr = rs.getInt("mgr");
				final Date hireDate = rs.getDate("hireDate");
				final Double sal = rs.getDouble("sal");
				final Double comm = rs.getDouble("comm");
				final Integer deptno = rs.getInt("deptno");		

				EmpVO vo = new EmpVO(empno, ename, job, mgr, hireDate, sal, comm, deptno);
		        
				return vo;
			});
			
			return list.get(0);
		}catch(Exception _original) {
			throw new DAOException(_original);
		}// try-catch
	} // selectEmp

	@Override
	public Boolean updateEmp(EmpDTO dto) throws DAOException {
		log.debug("updateEmp({}) invoked.", dto);
		
		try {
			/*
			final String sql = """
					UPDATE emp
					SET
						ename =?,
						job = ?,
						mgr =?,
						hireDate =?,
						sal = ?,
						comm = ?,
						deptno = ?
					WHERE empno =?
					""";
			
			Connection conn = this.dataSource.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try(conn; pstmt;){
			pstmt.setString(1, dto.getEname());
			pstmt.setString(2, dto.getJob());
			pstmt.setInt(3, dto.getMgr());
			
			// java.util.Date -> java.sql.Date 로 형변환 필요
			Date hiredate = Objects.requireNonNullElse(dto.getHireDate(),null);
			if(hiredate != null) pstmt.setDate(4, new java.sql.Date(dto.getHireDate().getTime()));
			else pstmt.setNull(4, Types.DATE);	// Set NULL data as each type.
			
			pstmt.setDouble(5, dto.getSal());
			pstmt.setDouble(6, dto.getComm());
			pstmt.setInt(7, dto.getDeptno());
			pstmt.setInt(8, dto.getEmpno());
			
			int affectedRows = pstmt.executeUpdate();
			
			return (affectedRows == 1);
			}
			*/
			final String sql = """
					UPDATE emp
					SET
						ename =?,
						job = ?,
						mgr =?,
						hireDate =?,
						sal = ?,
						comm = ?,
						deptno = ?
					WHERE empno =?
					""";
			   int affectedRows = this.jdbcTemplate.update(sql,
		               dto.getEname(),
		               dto.getJob(),
		               dto.getMgr(),
		               
		               new java.sql.Date(dto.getHiredate().getTime()),
		               //new java.sql.Date((Date) null),
		               //dto.getHiredate().setNull(5, Types.DATE),
		               //null,
		               
		               dto.getSal(),
		               dto.getComm(),
		               dto.getDeptno(),
		               dto.getEmpno()
		               );
		               
		               return affectedRows == 1;

		}catch(Exception _original) {
			throw new DAOException(_original);
		}// try-catch
		
	} // updateEmp

	
	@Override
	public Boolean deleteEmp(EmpDTO dto) throws DAOException {
		log.debug("deleteEmp({}) invoked.", dto);
		
		try {
			/*
			final String sql =  "DELETE FROM emp WHERE empno = ? ";
			
			Connection conn = this.dataSource.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpno());
			
            try (conn;pstmt; ) {

				int affectedRows = pstmt.executeUpdate();	
				return (affectedRows == 1);
            }// try-resource
            */
			final String sql =  "DELETE FROM emp WHERE empno = ? ";
			   int affectedRows = this.jdbcTemplate.update(sql,
	               dto.getEmpno()
	               );
		               
	               return affectedRows == 1;
		}catch(Exception _original) {
			throw new DAOException(_original);
		}// try-catch
		
		
	}// deleteEmp

	@Override
	public void setBeanName(String name) {
		log.debug("setBeanName() invoked.");
		
	} // setBeanName

}// end class
