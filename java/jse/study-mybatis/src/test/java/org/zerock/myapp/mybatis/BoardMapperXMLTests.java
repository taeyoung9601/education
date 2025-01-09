package org.zerock.myapp.mybatis;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
import org.zerock.myapp.domain.BoardVO;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

// 클래스 단위로 테스트 객체 생성
// 단위 테스트 메소드마다 객체를 생성하는게 아니라,
// 단위 테스트를 1개를 수행시키든, 전체를 수행시키든, 객체 1개를 만든다는 의미
@TestInstance(Lifecycle.PER_CLASS)

// 만일 전체테스트 메소드를 수행시킬 때,
// 각 단위테스트 메소드의 수행순서를  @Order(n) 어노테이션으로 정해놓는다는 의미
@TestMethodOrder(OrderAnnotation.class)


@Log4j2
@NoArgsConstructor
public class BoardMapperXMLTests {
	
   private SqlSessionFactory factory;
   private SqlSession sqlSession;
   
   @BeforeAll
   void beforeAll() throws IOException {
      log.debug("beforeAll() invoked.");
      
      SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
      
      InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
      final String environment = "production";
      
      SqlSessionFactory factory = builder.build(inputStream,environment);
      
      assert factory != null;
      this.factory = factory;
   } //beforeAll
   
   @BeforeEach
   void beforeEach() {
	   log.debug("beforeEach() invoked.");
	   
	   SqlSession sqlSession = this.factory.openSession(); 	//auto commit OFF
	   this.sqlSession = sqlSession;
	   log.info("\t+this.sqlSession : {}", this.sqlSession );
   }
   
   @AfterEach
   void afterEach() {
	   log.debug("afterEach() invoked.");
	   
	   Objects.requireNonNull(this.sqlSession);
	   this.sqlSession.close();
	   
	   log.info("\t+closed.");
   }
   
   
 @Disabled
 @Tag("mapper-xml")
 @Order(0)
 @Test
// @RepeatedTest(1)
 @DisplayName("test")
 @Timeout(value=1L ,unit=TimeUnit.SECONDS)
 void test() {
    log.debug("test() invoked.");
    
 } // test   
   
 
 
//   @Disabled
   @Tag("mapper-xml")
   @Order(1)
   @Test
//   @RepeatedTest(1)
   @DisplayName("testSelectAllBoards")
   @Timeout(value=10L ,unit=TimeUnit.SECONDS)
   void testSelectAllBoards() throws SQLException {
      log.debug("testSelectAllBoards() invoked.");
      
      //Step1. 위임할 SQL 문장이 저장되어 있는 Mapper XML 지정
      final String namespace = "TaeMapper";
      // Step2. 실행을 의뢰할 SQL 문의 ID 지정
      final String sqlId = "selectAllBoards";
      // Step3. 마이바티스가 인식가능한 형태로, Step1/2 의 값을 결합시킴
      //		이 때 형식은 반드시 "<namespace>.<sqlId>" 문자열로 만들어야 함 (***)
      //					 비유 : " 패키지 
      final String mappedStatement = "%s.%s".formatted(namespace,sqlId);
      
      // 그런데, 왜 변수명을 매핑된 (Mapper Statement)이라고 했을까?
      // 어차피 변수명은 개발자 맘대로 지어도 되는데..
      // 이유: 바로 이 문자열이 Mapper XML 파일에 저장된 특정 SQL문장과 매핑되기 때문에
      // 	   앞에 Mapped 가 붙은 것이고, 뒤의 Statement 는 SQL 문장이란 뜻이죠
      // 	   마이바티스도 이렇게 Mapper XML에 저장된 특정 SQL 문장을 Mapped Statement라고
      //	   부릅니다. 또한, 어떤 오류가 발생할 때에도, 이 단어로 대부분 나옵니다.
      log.info("\t + mappedStatement:{}", mappedStatement);
      
      // 매개변수없는 factory.openSession() 이 있고, factory.openSession(boolean autoCommit)이 있다.
      @Cleanup SqlSession sqlSession = this.factory.openSession();
      log.info("\t + isAutoCommit: {} " , sqlSession.getConnection().getAutoCommit());
      
      // 1. 단일행 반환하는 DQL 문장일 때 사용
//      sqlSession.selectOne(mappedStatement);
      
      // 2. 복수행 반환하는 DQL 문장일 때 사용
      //	결과셋이 단 1개의 행도 없더라도, 리스트는 만들어집니다 (***)
//      sqlSession.<BoardVO>selectList(mappedStatement);
      List<BoardVO> boards = sqlSession.selectList(mappedStatement);
      assertNotNull(boards);
      boards.forEach(log::info); 	// 내부 순회하면서 모두 출력
      
      // 3. DML 문은 아래와 같이, 각각 대응되는 메소드가 있고
      //	아래 3개의 메소드의 리턴 타입은 모두 int( 즉, Affected Rows) 입니다.
//      sqlSession.insert(mappedStatement);
//      sqlSession.update(mappedStatement);
//      sqlSession.delete(mappedStatement);
      
//   	TCL 문장을 수행할 수 있도록 메소드가 준비되어있습니다.   
//      sqlSession.commit();
//      sqlSession.rollback();
   } // testSelectAllBoards
   
   
   
   
   
   
   
// @Disabled
 @Tag("mapper-xml")
 @Order(2)
 @Test
// @RepeatedTest(1)
 @DisplayName("findBoardByBno")
 @Timeout(value=10L ,unit=TimeUnit.SECONDS)
 void findBoardByBno() throws SQLException {
    log.debug("findBoardByBno() invoked.");
    
    final String mappedStatement = "%s.%s".formatted("TaeMapper","findBoardByBno");
   
    @Cleanup SqlSession sqlSession = this.factory.openSession();
    BoardVO board = sqlSession.selectOne(mappedStatement,7);
    
    Objects.requireNonNull(board);
    log.info("\t + board: {}", board);
    
    
 } // findBoardByBno
 
 
 
	//@Disabled
	@Tag("mapper-xml")
	@Order(3)
	@Test
	//@RepeatedTest(1)
	@DisplayName("findBoardsByTitle")
	@Timeout(value=10L ,unit=TimeUnit.SECONDS)
	void findBoardsByTitle() throws SQLException {
	  log.debug("findBoardsByTitle() invoked.");
	  
	  final String mappedStatement = "%s.%s".formatted("TaeMapper","findBoardsByTitle");
	  
	  // 실행시킬 SQL 문의 바인드 변수가 1개인 경우에는, 바인드 변수의 이름으로 전달하지않고
	  // 값 1개만 던지면, 마이바티스는 무조건 1개인 바인드 변수에 값을 전달합니다.(***)
	  List<BoardVO> list = sqlSession.selectList(mappedStatement,"LE_1");
	  
	  Objects.requireNonNull(list);
	  list.forEach(log::info);
	  
	} // findBoardsByTitle


	//@Disabled
	@Tag("mapper-xml")
	@Order(4)
	@Test
	//@RepeatedTest(1)
	@DisplayName("findBoardsBnoOrTitle")
	@Timeout(value=10L ,unit=TimeUnit.SECONDS)
	void findBoardsBnoOrTitle() throws SQLException {
	log.debug("findBoardsBnoOrTitle() invoked.");
	
	final String mappedStatement = "%s.%s".formatted("TaeMapper","findBoardsBnoOrTitle");
	
	Map<String,String> params = new HashMap<>();
	params.put("bno","3");				// bno 이름의 바인드 변수에 전달할 값
	params.put("title","LE_1" );		// bno 이름의 바인드 변수에 전달할 값
	
	List<BoardVO> list = sqlSession.selectList(mappedStatement, params);
	
	Objects.requireNonNull(list);
	list.forEach(log::info);
	
	} // findBoardsByTitle
   

} // end class
