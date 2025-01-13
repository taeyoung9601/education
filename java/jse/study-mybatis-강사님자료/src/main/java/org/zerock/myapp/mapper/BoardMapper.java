package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;

// 이 인터페이스가 Mapper XML과 동일한 역할을 수행합니다:
// 즉, SQL문 저장/바인드변수선언/SQL문호출시 바인딩값전달

public interface BoardMapper {

	@Select("""
			SELECT /*+ index_desc(tbl_board) */ *
	        FROM tbl_board
	        WHERE bno > 0
			""")
	public abstract List<BoardVO> selectAllBoards();	// 게시글 전체목록조회
	
	@Select("""
			SELECT *
	        FROM tbl_board
	        WHERE bno = #{bno}
			""")
	public abstract BoardVO findBoardByBno(Integer bno2);	// 특정 게시글 하나 조회
	
	
	@Select("""
			SELECT *
	        FROM tbl_board
	        WHERE title LIKE '%' || #{title} || '%'
			""")
	public abstract List<BoardDTO> findBoardsByTitle(String title);
		
	@Select("""
			SELECT *
	        FROM tbl_board
	        WHERE 
            bno = #{bno}
            OR title LIKE '%' || #{title} || '%'
			""")
//	public abstract List<BoardDTO> findBoardsByBnoOrTitle(Integer bno, String title);	// OK
//	public abstract List<BoardDTO> findBoardsByBnoOrTitle(String title, Integer bno);	// OK
	
	//-- BindingException: Parameter 'bno' not found. Available parameters are [bno2, title2, param1, param2]
//	public abstract List<BoardDTO> findBoardsByBnoOrTitle(Integer bno2, String title2);	// XX
//	public abstract List<BoardDTO> findBoardsByBnoOrTitle(Integer param1, String param2);	// XX
	public abstract List<BoardDTO> findBoardsByBnoOrTitle(
			@Param("bno") Integer bno2, 
			@Param("title") String title2
	);	// OK
	
	
	@Insert("""
			INSERT INTO tbl_board (title, content, writer)
			VALUES ( #{title}, #{content}, #{writer} )
			""")
	public abstract Integer insertBoard(
			@Param("title") String title,
			@Param("content") String content, 
			@Param("writer") String writer
	);
	
}
