package org.zerock.myapp.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.mapper.BoardMapper;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

public class BoardMapperTests {
	
	private SqlSessionFactory factory;
    private SqlSession sqlSession;
	   
	@BeforeAll
	void beforeAll() throws IOException {
		log.debug("beforeAll() invoked.");
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory = builder.build(inputStream, "production");
		
		Objects.requireNonNull(factory);
		this.factory = factory;
		
		
	}
	

	
//	@Disabled
	@Tag("Mapper-Interface")
	@Order(1)
	@Test
//	@RepeatedTest(n)
	@DisplayName("testSelectAllBoards")
	@Timeout(value = 5L, unit=TimeUnit.SECONDS)
	void testSelectAllBoards() {
		log.debug("testSelectAllBoards() invoked.");
		
		@Cleanup SqlSession sqlSession = this.factory.openSession();
		
		
//		Step1. Get a Mapper Proxy for the specified Mapper Interface.
//			   The Mapper Proxy is created with Java Dynamic Proxy API
		BoardMapper mapper = sqlSession.<BoardMapper>getMapper(BoardMapper.class); // Polymorphism-1
		
		assertNotNull(mapper);
		log.info("\t + mapper : {}, type : {}", mapper, mapper.getClass().getName());
		
//		Step2.
		List<BoardVO> list = mapper.selectAllBoards();
		
		assert list != null;
	}
	
	

//	@Disabled
	@Tag("Mapper-Interface")
	@Order(2)
	@Test
//	@RepeatedTest(n)
	@DisplayName("testFindBoardByBno")
	@Timeout(value = 5L, unit=TimeUnit.SECONDS)
	void testFindBoardByBno() {
		log.debug("testFindBoardByBno() invoked.");
		
		SqlSession sqlSession = this.factory.openSession(false);	// autocommit OFF
		
		try(sqlSession;){
			
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			
			Objects.requireNonNull(mapper);
			log.info("\t + mapper : {}, type : {}", mapper, mapper,getClass().getName());
			
			BoardVO vo = mapper.findBoardByBno(7);
			
			if(vo != null) {
				log.info("\t + vo : {}", vo);
			}
			
			
		}// try- with-resources
		
	}
	
//	@Disabled
	@Tag("Mapper-Interface")
	@Order(3)
	@Test
//	@RepeatedTest(n)
	@DisplayName("testFindBoardsByTitle")
	@Timeout(value = 5L, unit=TimeUnit.SECONDS)
	void testFindBoardsByTitle() {
		log.debug("testFindBoardsByTitle() invoked.");
		
		@Cleanup SqlSession sqlSession = this.factory.openSession(); // autocommit OFF
		
		BoardMapper mapper = sqlSession.<BoardMapper>getMapper(BoardMapper.class);
//		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);   // <BoardMapper> 생략가능
		
		Objects.requireNonNull(mapper);
		log.info("\t + mapper: {} , type : {} ", mapper, mapper.getClass().getName());
		
		//최종 결과셋에서, 우리가 지정한 요소 타입으로 객체의 필드에 값을 넣어줄 떄에,
		// 마이바티스는 기본으로 각 필드의 Setter 메소드로 필드에 값을 넣게 되어 있습니다.
		// 단 리턴타입이 읽기전용타입 ( 즉, VO패턴객체 또는 자바고급의 Record Class인 경우
		List<BoardDTO> list = mapper.findBoardsByTitle(null);
		
		assertNotNull(list);
		
		log.info("=".repeat(30));
		list.forEach(log::info);
	}
	
//	@Disabled
	@Tag("Mapper-Interface")
	@Order(4)
	@Test
//	@RepeatedTest(n)
	@DisplayName("testfindBoardsBnoOrTitle")
	@Timeout(value = 5L, unit=TimeUnit.SECONDS)
	void testfindBoardsBnoOrTitle() {
		log.debug("testfindBoardsBnoOrTitle() invoked.");
		
		try(SqlSession sqlSession = this.factory.openSession(true);){  	//autocommit ON
			BoardMapper mapper = sqlSession.<BoardMapper>getMapper(BoardMapper.class);
			assert mapper != null;
			log.info("\t + mapper : {} , type : {}", mapper, mapper.getClass().getName());
			
			List<BoardDTO> list = mapper.findBoardsBnoOrTitle(7, "LE_2");
			assertNotNull(list);
			log.info("=".repeat(50));
			list.forEach(log::info);
			
		}// try-with-resources
	}
	
	
//	@Disabled
	@Tag("Mapper-Interface")
	@Order(5)
	@Test
//	@RepeatedTest(n)
	@DisplayName("testinsertBoard")
	@Timeout(value = 5L, unit=TimeUnit.SECONDS)
	void testinsertBoard() {
		log.debug("testinsertBoard() invoked.");
		
		@Cleanup SqlSession sqlSession = this.factory.openSession(false); 	//autocommit OFF
		
		BoardMapper mapper = sqlSession.<BoardMapper>getMapper(BoardMapper.class);
		assert mapper != null;
		log.info("\t + mapper: {}, type: {}", mapper, mapper.getClass().getName());		// With Java Dynamic Proxy api.
		
		int affectedRows = mapper.insertBoard("---New title---", "---New content---", "---pyramid---");
		
		assertEquals(1, affectedRows);
		log.info("\t + affectedRows : {} ",affectedRows);
		
		sqlSession.commit(); // End this TX by commit.
	
	}
	
}
