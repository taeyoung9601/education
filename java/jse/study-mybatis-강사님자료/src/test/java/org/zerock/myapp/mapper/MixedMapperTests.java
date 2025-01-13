package org.zerock.myapp.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.mapper.MixedMapper;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@Log4j2
@NoArgsConstructor
public class MixedMapperTests {
	private SqlSessionFactory factory;
	
	
	@BeforeAll
	void beforeAll() throws IOException {
		log.debug("beforeAll() invoked.");
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory = builder.build(inputStream, "production");
		
		Objects.requireNonNull(factory);
		this.factory = factory;
	}
		
	
	@Disabled
	@Tag("Mixed-Mapper")
	@Order(0)
	@Test
//	@RepeatedTest(n)
	@DisplayName("contextLoads")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void contextLoads() {
		log.debug("contextLoads() invoked.");

	}
	
//	@Disabled
	@Tag("Mixed-Mapper")
	@Order(1)
	@Test
//	@RepeatedTest(n)
	@DisplayName("testGetNow")
	@Timeout(value=3L, unit=TimeUnit.SECONDS)
	void testGetNow() {
		log.debug("testGetNow() invoked.");

		@Cleanup SqlSession sqlSession = this.factory.openSession();
		
		MixedMapper mapper = sqlSession.getMapper(MixedMapper.class);
		
		String now = mapper.getNow();
		log.info("\t+ now: {}", now);
	}
	
//	@Disabled
	@Tag("Mixed-Mapper")
	@Order(2)
	@Test
//	@RepeatedTest(n)
	@DisplayName("testGetNowWithXML")
	@Timeout(value=3L, unit=TimeUnit.SECONDS)
	void testGetNowWithXML() {
		log.debug("testGetNowWithXML() invoked.");

		@Cleanup SqlSession sqlSession = this.factory.openSession();
		
		MixedMapper mapper = sqlSession.getMapper(MixedMapper.class);
		
		// (1) 아직 Mapper XML 파일이 준비가 안되어 있다면, 아래 오류가 발생
		// BindingException: Invalid bound statement (not found): 
		// 	org.zerock.myapp.mapper.MixedMapper.getNowWithXML <-- ***
		//    ------------------------------------  ---------------
		//							namepsace						 .     sqlId
		String now = mapper.getNowWithXML();
		log.info("\t+ now: {}", now);		
	}

}
