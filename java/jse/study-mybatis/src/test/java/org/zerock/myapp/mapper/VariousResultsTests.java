package org.zerock.myapp.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
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
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.CustomBoard;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

// 마이바티슥ㅏ 지원하는 다양한 결과셋을 얻는 방법 테스트
public class VariousResultsTests {
	
	private SqlSessionFactory factory;
	
	@BeforeAll
	void beforeAll() throws IOException {
		log.debug("beforeAll() invoked.");
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = 
//		builder.build(inputStream);	//Using Mybatis config's default environement
		builder.build(inputStream, "production");	//Using Mybatis config's default environment
		
		assertNotNull(factory);
		this.factory = factory;
	}
	
	@Disabled
	@Tag("Various-Results-Sets") 	// console.group("GROUPNAME")
	@Order(0)
	@Test
//	@RepeatedTest(1)
	@DisplayName("Default Test Unit")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	
	void contextLoads() {
		log.debug("contextLoads() invoked.");
	}
	
	
//	@Disabled
	@Tag("Various-Results-Sets") 	// console.group("GROUPNAME")
	@Order(1)
//	@Test
	@RepeatedTest(3)
	@DisplayName("testSelectAllBoardsAsMap")
	@Timeout(value = 1L * 5, unit = TimeUnit.SECONDS)
	
	void testSelectAllBoardsAsMap() {
		log.debug("testSelectAllBoardsAsMap() invoked.");
		
		//Get a response as a Map collection
//		String mappedStatement = "%s.%s".formatted("namespace", "sqlId");
		String mappedStatement = "%s.%s".formatted("org.zerock.myapp.mapper.TaeMapper", "selectAllBoards");
		
		//DQL : Without a Transaction, DML : Under a Transaction with autocommit ON/OFF
		SqlSession sqlSession = this.factory.openSession();			//autocommit OFF
//		SqlSession sqlSession = this.factory.openSession(false); 	//autocommit OFF
		
		//Get a response as a Map collection, but Not a List collection
//		List<BoardDTO> list =sqlSession.<BoardDTO>selectList(mappedStatement); 	//default
		
		// K : PK column type, V : resultType property in a Mapper XML.
		String pkColumn = "bno";	//In the BoardDTO
		Map<Integer,BoardDTO> map = 
			// The 2nd parameter is a name of the specified value type.
			sqlSession.<Integer,BoardDTO>selectMap(mappedStatement, pkColumn); 	// AS a Map collection
		
		Objects.requireNonNull(map);
		log.info("=".repeat(50));
		
		// The traverse of a Map collection with forEach method & Lambda expression
		map.forEach((key,value)->log.info("key : {} , value : {}", key, value));
		
		/**
//		for(Map.Entry entry : map) { 	// XX : Due that the Map is Not iterable.
		@Cleanup("clear")
		Set<Entry<Integer, BoardDTO>> set = map.entrySet();						//Step1.
		for(Entry<Integer,BoardDTO> entry : set) {								//Step2.
			log.info("ket:{}, value: {}", entry.getKey(), entry.getValue());	//Step3.
		} // enhanced for
		*/	
	}
	
	
//	@Disabled
	@Tag("Various-Results-Sets") 	// console.group("GROUPNAME")
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testFindBoardByBno")
	@Timeout(value = 1L * 3, unit = TimeUnit.SECONDS)
	
	void testFindBoardByBno() {
		log.debug("testFindBoardByBno() invoked.");
		
		final String mappedStatement = "%s.%s".formatted("org.zerock.myapp.mapper.TaeMapper", "findBoardByBno");
		
		@Cleanup SqlSession sqlSession = this.factory.openSession();
		Map<Integer,BoardDTO> map = sqlSession.<Integer,BoardDTO>selectMap(mappedStatement,3,"bno");
		
		Objects.requireNonNull(map);
		log.info("=".repeat(50));
		map.forEach((key,value)->log.info("key : {} , value : {}", key, value));
		
		
	}
	
	
	
//	@Disabled
	@Tag("Various-Results-Sets") 	// console.group("GROUPNAME")
	@Order(3)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testFindBoardsByTitle")
	@Timeout(value = 1L * 2, unit = TimeUnit.SECONDS)
	
	void testFindBoardsByTitle() {
		log.debug("testFindBoardsByTitle() invoked.");
		
		final String mappedStatement = "%s.%s".formatted("org.zerock.myapp.mapper.TaeMapper","findBoardsByTitle");
		
		@Cleanup SqlSession sqlSession = this.factory.openSession();
		Map<Integer,BoardDTO> map = sqlSession.<Integer,BoardDTO>selectMap(mappedStatement,"TLE_3","bno");
		
		map.forEach((k,v) -> log.info("key: {}, value:{}", k,v)) ;
	}
	
	
	
//	@Disabled
	@Tag("Various-Results-Sets") 	// console.group("GROUPNAME")
	@Order(4)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testFindBoardsBnoOrTitle")
	@Timeout(value = 1L * 3, unit = TimeUnit.SECONDS)
	
	void testFindBoardsBnoOrTitle() {
		log.debug("testFindBoardsBnoOrTitle() invoked.");
		
		final String mappedStatement = "%s.%s".formatted("org.zerock.myapp.mapper.TaeMapper","findBoardsBnoOrTitle");
		
		@Cleanup SqlSession sqlSession = this.factory.openSession();
		
		// (1)Map or (2)Java Bean
//		Map<String,Object> params = new HashMap<>();
//		params.put("bno", 3);
//		params.put("title", "TITLE_4");
// ----------------------------------------
		BoardDTO params = new BoardDTO(); 	//DTO is already Java Bean.
		params.setBno(3);
		params.setTitle("TITLE_4");
		
		
		Map<Integer,BoardDTO> map = sqlSession.<Integer,BoardDTO>selectMap(mappedStatement,params, "bno");
		
		map.forEach((k,v) -> log.info("key : {} , value: {}", k,v));
	}
	
	
//	@Disabled
	@Tag("Various-Results-Sets") 	// console.group("GROUPNAME")
	@Order(5)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testFindBoardByBnoAsUserResultMap")
	@Timeout(value = 1L * 3, unit = TimeUnit.SECONDS)
	
	void testFindBoardByBnoAsUserResultMap() {
		log.debug("testFindBoardByBnoAsUserResultMap() invoked.");
		
		//DQL의 조회결과(=ResultSet)를 개발자가 원하는 형태로 받는 방법을 알자!
		//(중요사항) 소위 DTO/VO 는 정확히 특정 1개 테이블에 대한 값 객체이기때문에,
		//			 2개 이상의 테이블을 조인하거나 , 고급 쿼리인 상관/비상관 부속질의로
		// 			 생성된 결과 셋은 DTO/VO로는 처음부터 받을 수가 없다.
		
		final String mappedStatement = "%s.%s".formatted("org.zerock.myapp.mapper.BoardMapper3","findBoardByBnoAsUserResultMap");
		
		@Cleanup SqlSession sqlSession = this.factory.openSession();
		BoardDTO dto = sqlSession.<BoardDTO>selectOne(mappedStatement,7);
		
		assert dto != null;
		log.info("=".repeat(50));
		log.info("\t + dto :{}", dto);
	}
	
	
//	@Disabled
	@Tag("Various-Results-Sets") 	// console.group("GROUPNAME")
	@Order(6)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testfindAllBoardsAsUserResultMap")
	@Timeout(value = 1L * 3, unit = TimeUnit.SECONDS)
	
	void testfindAllBoardsAsUserResultMap() {
		log.debug("testfindAllBoardsAsUserResultMap() invoked.");

		final String mappedStatement = "%s.%s".formatted("org.zerock.myapp.mapper.BoardMapper3","findAllBoardsAsUserResultMap");
		
		@Cleanup SqlSession sqlSession = this.factory.openSession();
		List<BoardDTO> list = sqlSession.selectList(mappedStatement);
		
		log.info("=".repeat(50));
		list.forEach(log::info);

	}
	
	
	
//	@Disabled
	@Tag("Various-Results-Sets") 	// console.group("GROUPNAME")
	@Order(7)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testfindBoardsByTitleAsUserResultMap")
	@Timeout(value = 1L * 3, unit = TimeUnit.SECONDS)
	
	void testfindBoardsByTitleAsUserResultMap() {
		log.debug("testfindBoardsByTitleAsUserResultMap() invoked.");

		final String mappedStatement = "%s.%s".formatted("org.zerock.myapp.mapper.BoardMapper3","findBoardsByTitleAsUserResultMap");
		
		@Cleanup SqlSession sqlSession = this.factory.openSession();
		List<CustomBoard> list = sqlSession.selectList(mappedStatement,"TLE_7");
		
		log.info("=".repeat(50));
		list.forEach(log::info);

	}
	

	
}
