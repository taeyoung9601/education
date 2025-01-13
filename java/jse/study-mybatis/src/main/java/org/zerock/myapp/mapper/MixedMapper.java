package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;


// 혼합 방식 구현 = Mapper Interface + Mapper XML
public interface MixedMapper {
	
	
	@Select("SELECT current_date AS now FROM dual")
	public abstract String getNow();	//게시글 전체목록 조회
	
	public abstract String getNowWithXML();

}
