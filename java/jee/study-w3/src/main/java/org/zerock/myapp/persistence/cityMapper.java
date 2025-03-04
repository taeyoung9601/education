package org.zerock.myapp.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.zerock.myapp.domain.CityDTO;

@Mapper
public interface cityMapper {
	
	// INSERT 가 성공적으로 수행된 이후에,
	// PK값이 자동생성되는 (useGeneratedKeys= true) 테이블의 PK 컬럼명 != VO/DTO의 키 속성명이면,
	// 테이블에 새로 생성된 행의 keyColumn 이름의 컬럼값을 -> keyProperty 로 지정된 이름의 VO/DTO 자바빈
	// 의 속성값으로 집어넣어라! => set + ketProperty => set + id => setId 메소드
	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("INSERT INTO city (name,state,country) VALUES (#{name},#{state},#{country})")
	public abstract Boolean insertCity(CityDTO dto);
	
	
}// end interface
