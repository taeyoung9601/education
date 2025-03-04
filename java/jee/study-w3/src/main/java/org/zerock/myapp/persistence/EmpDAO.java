package org.zerock.myapp.persistence;

import java.util.List;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.domain.EmpVO;
import org.zerock.myapp.exception.DAOException;

public interface EmpDAO {
	// 사원테이블에 대한 CRUD + LIST 5개의 기능을 수행하는 추상메소드 선언
	public abstract List<EmpVO> selectAllEmps() throws DAOException;		// List
	
	public abstract Boolean insertEmp(EmpDTO dto) throws DAOException;	// CREATE
	public abstract EmpVO selectEmp(EmpDTO dto) throws DAOException;	// READ
	public abstract Boolean updateEmp(EmpDTO dto) throws DAOException;	// UPDATE
	public abstract Boolean deleteEmp(EmpDTO dto) throws DAOException; 	// DELETE
	
}// end interface