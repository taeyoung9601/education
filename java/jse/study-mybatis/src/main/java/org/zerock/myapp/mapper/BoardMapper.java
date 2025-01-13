package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;

//이 인터페이스가 Mapper XML과 동일한 역할을 수행합니다.
//즉, SQL 문 저장/바인드 변수 선언/ SQL문 호출시 바인딩 값 전달

@Mapper
public interface BoardMapper {
	
	// 특정 게시글 번호로 한개의 게시글을 검색해서
	
	@Select("""
			SELECT *
			FROM TBL_BOARD
			WHERE bno > 0
			""")
	public abstract List<BoardVO> selectAllBoards();
	
	@Select("""
			SELECT *
			FROM tbl_board
			WHERE bno = #{bno}
			""")
	public abstract BoardVO findBoardByBno(Integer bno2);
	
	@Select("""
			SELECT *
			FROM tbl_board
			WHERE title LIKE '%'|| #{title} ||'%'
			""")
	public abstract List<BoardDTO> findBoardsByTitle(String title);
//	public abstract List<BoardDTO> findBoardsByTitle(String title2); //이름이 달라도 바인딩 변수가 하나면 자동 대입
	
	@Select("""
			 SELECT *
			 FROM tbl_board
			 WHERE 
			 bno = #{bno}
             OR title LIKE '%'|| #{title} ||'%'
			""")
//	public abstract List<BoardDTO> findBoardsBnoOrTitle(Integer bno, String title);  : Ok
//	public abstract List<BoardDTO> findBoardsBnoOrTitle(Integer bno2, String title2);
//	이름이 다르면 오류발생
	public abstract List<BoardDTO> findBoardsBnoOrTitle(
			@Param("bno") Integer bno2, 
			@Param("title") String title2
	);  // OK
	
	@Insert("""
			INSERT INTO tbl_board(title,content,writer)
			VALUES(#{title},#{content},#{writer})
			""")
//	public abstract Integer insertBoard(String title,String content, String writer);
	public abstract Integer insertBoard(
			@Param("title") String title2,
			@Param("content") String content2, 
			@Param("writer") String writer2
	);

}
