package org.zerock.myapp.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.domain.EmpVO;
import org.zerock.myapp.exception.DAOException;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)


// 이 어노테이션을 붙여놔야, 테스트 메소드 수행시,
// 스프링부트 앱이 함께 구동되어, 웹3계층의 요소를 테스트할 수 있게 합니다.
@SpringBootTest
public 
class EmpDAOTests {
   @Setter(onMethod_= @Autowired)
   private EmpDAO dao;

   
   @BeforeAll
   void beforeAll() throws SQLException {
      log.debug("beforeAll() invoked.");
      
      assertNotNull(this.dao);
      log.info("\t+ this.dao: {}", this.dao);
   } // beforeAll
   
   @Disabled
   @Tag("EmpDAO-Test")
   @Order(0)
   @Test
//   @RepeatedTest(1)
   @DisplayName("contextLoads")
   @Timeout(value=1L, unit=TimeUnit.SECONDS)
   void contextLoads() {
      log.debug("contextLoads() invoked.");
      
   } // contextLoads
   
//   @Disabled
   @Tag("EmpDAO-Test")
   @Order(1)
   @Test
//   @RepeatedTest(1)
   @DisplayName("1. testSelectAllEmps")
   @Timeout(value=1L, unit=TimeUnit.SECONDS)
   void testSelectAllEmps() throws DAOException {
      log.debug("testSelectAllEmps() invoked.");
      
      @Cleanup("clear") List<EmpVO> list = this.dao.selectAllEmps();
      Objects.requireNonNull(list);
      
      list.forEach(vo -> log.info("EmpVO: {}", vo));
   } // testSelectAllEmps
   
//   @Disabled
   @Tag("EmpDAO-Test")
   @Order(2)
   @Test
//   @RepeatedTest(1)
   @DisplayName("2. testSelectEmp")
   @Timeout(value=1L, unit=TimeUnit.SECONDS)
   void testSelectEmp() throws DAOException {
      log.debug("testSelectEmp() invoked.");
      
      EmpDTO dto = new EmpDTO();
      dto.setEmpno(7521);
      
      EmpVO vo = this.dao.selectEmp(dto);
      
      Objects.requireNonNull(vo);
      log.info("\t+ vo: {}", vo);
   } // testSelectEmp
   
//   @Disabled
   @Tag("EmpDAO-Test")
   @Order(3)
   @Test
//   @RepeatedTest(1)
   @DisplayName("3. testDeleteEmp")
   @Timeout(value=1L, unit=TimeUnit.SECONDS)
   void testDeleteEmp() throws DAOException {
      log.debug("testDeleteEmp() invoked.");
      
      EmpDTO dto = new EmpDTO();
      dto.setEmpno(8001);
      
      boolean isDeleted = this.dao.deleteEmp(dto);
      assertEquals(true, isDeleted);
      log.info("\t+ isDeleted: {}", isDeleted);
   } // testDeleteEmp
   
//   @Disabled
   @Tag("EmpDAO-Test")
   @Order(4)
   @Test
//   @RepeatedTest(1)
   @DisplayName("4. testUpdateEmp")
   @Timeout(value=1L, unit=TimeUnit.SECONDS)
   void testUpdateEmp() throws DAOException {
      log.debug("testUpdateEmp() invoked.");
      
      EmpDTO dto = new EmpDTO();
      dto.setEmpno(8780);
      dto.setEname("PYRAMIDE");
      dto.setDeptno(20);
      dto.setJob("ANALYST");
      dto.setMgr(7902);
      dto.setComm(33.);
      dto.setSal(30000.);
      dto.setHiredate(new Date());
      
      log.info("\t+ dto: {}", dto);
      
      boolean isUpdated = this.dao.updateEmp(dto);
      assertEquals(true, isUpdated);
      log.info("\t+ isUpdated: {}", isUpdated);
   } // testUpdateEmp
   
//   @Disabled
   @Tag("EmpDAO-Test")
   @Order(5)
   @Test
//   @RepeatedTest(1)
   @DisplayName("5. testInsertEmp")
   @Timeout(value=1L, unit=TimeUnit.SECONDS)
   void testInsertEmp() throws DAOException {
      log.debug("testInsertEmp() invoked.");
      
      EmpDTO dto = new EmpDTO();
      dto.setEmpno(8788);
      dto.setEname("TRINITY");
      dto.setDeptno(20);
      dto.setJob("ANALYST");
      dto.setMgr(7902);
      dto.setComm(33.);
      dto.setSal(30000.);
      dto.setHiredate(new Date());
      
      log.info("\t+ dto: {}", dto);
      
      boolean isInserted = this.dao.insertEmp(dto);
      assertEquals(true, isInserted);
      log.info("\t+ isInserted: {}", isInserted);
   } // testInsertEmp
   
   

} // end class
