package org.zerock.myapp.mapper;

import org.apache.ibatis.annotations.Select;


// 혼합방식 구현 = Mapper Interface + Mapper XML
public interface MixedMapper {

	@Select("SELECT current_date AS now FROM dual")
	public abstract String getNow();

	public abstract String getNowWithXML();
	
}
