package org.zerock.myapp.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@SpringBootTest
public class EmpMapperTests {
	@Autowired private EmpMapper mapper;
	
	@BeforeAll
	void beforeAll() {
		log.debug("beforeAll() invoked.");
		
		Objects.requireNonNull(this.mapper);
		log.info("\t + this.mapper:{}", this.mapper);
	}// beforeAll
	
	
	@Disabled
	@Tag("MyBatis-Mapper-Interface")
	@Order(0)
	@Test
//	@Repeatable(1)
	@DisplayName("contextLoads")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
	void contextLoads() {
		log.debug("contextLoads() invoked.");
	}
	
//	@Disabled
	@Tag("MyBatis-Mapper-Interface")
	@Order(1)
	@Test
//	@Repeatable(1)
	@DisplayName("testSelectAllEmps")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
	void testSelectAllEmps() throws DAOException {
		log.debug("testSelectAllEmps() invoked.");
		
		List<EmpVO> list = this.mapper.selectAllEmps();
		
		assertNotNull(list);
		list.forEach(vo -> log.info(vo.toString()));
	}// testSelectAllEmps
	
//	@Disabled
	@Tag("MyBatis-Mapper-Interface")
	@Order(2)
	@Test
//	@Repeatable(1)
	@DisplayName("testSelectEmp")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
	void testSelectEmp() throws DAOException {
		log.debug("testSelectEmp() invoked.");
		
		EmpDTO dto = new EmpDTO();
		dto.setEmpno(8230);
		dto.setEname("YOUNG");
		log.info("\t + dto:{}", dto);
		
		EmpVO vo = this.mapper.selectEmp(dto);
		
		Objects.requireNonNull(vo);
		log.info("\t + vo :{}", vo);
	}// testSelectEmp
	
//	@Disabled
	@Tag("MyBatis-Mapper-Interface")
	@Order(3)
	@Test
//	@Repeatable(1)
	@DisplayName("testDeleteEmp")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
	void testDeleteEmp() throws DAOException {
		log.debug("testDeleteEmp() invoked.");
		
		EmpDTO dto = new EmpDTO();
		dto.setEmpno(2302);
		Boolean isDeleted = this.mapper.deleteEmp(dto);
		log.info("\t + isDeleted : {}", isDeleted);
		
		assertEquals(true, isDeleted);

		
	}// testDeleteEmp
	
//	@Disabled
	@Tag("MyBatis-Mapper-Interface")
	@Order(4)
	@Test
//	@Repeatable(1)
	@DisplayName("testInsertEmp")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
	void testInsertEmp() throws DAOException {
		log.debug("testInsertEmp() invoked.");
		
		EmpDTO dto = new EmpDTO();
		dto.setEmpno(7789);
		dto.setEname("Tae");
		dto.setJob("CLERK");
		dto.setHiredate(new Date());
		dto.setSal(30000.);
		dto.setDeptno(30);
		
		Boolean isInserted = this.mapper.insertEmp(dto);
		log.info("\t + isInserted : {}", isInserted);
		
		assertEquals(true, isInserted);

		
	}// testInsertEmp
	
//	@Disabled
	@Tag("MyBatis-Mapper-Interface")
	@Order(5)
	@Test
//	@Repeatable(1)
	@DisplayName("testUpdateEmp")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
	void testUpdateEmp() throws DAOException {
		log.debug("testUpdateEmp() invoked.");
		
		EmpDTO dto = new EmpDTO();
		dto.setEmpno(9000);
		dto.setEname("Tae");
		dto.setJob("tt");
		dto.setHiredate(new Date());
		dto.setSal(30000.);
		dto.setDeptno(30);
		
		Boolean isUpdated = this.mapper.updateEmp(dto);
		log.info("\t + isInserted : {}", isUpdated);
		
		assertEquals(true, isUpdated);

		
	}// testInsertEmp
}// end class
