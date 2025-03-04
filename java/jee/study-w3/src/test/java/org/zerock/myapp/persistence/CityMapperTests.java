package org.zerock.myapp.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.zerock.myapp.domain.CityDTO;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@SpringBootTest
public class CityMapperTests {
	@Autowired private cityMapper mapper;
	
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
	}//contextLoads
	
//	@Disabled
	@Tag("City-Mapper-Test")
	@Order(1)
	@Test
//	@Repeatable(1)
	@DisplayName("1.textInsertCity")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
	void textInsertCity() {
		log.debug("textInsertCity() invoked.");
		
		CityDTO dto = new CityDTO();
		dto.setName("Tae");
		dto.setState("SU");
		dto.setCountry("KR");
		
		Boolean isInserted = this.mapper.insertCity(dto);
		log.info("\t + isInserted : {}", isInserted);
		
		assertEquals(true, isInserted);
		
		
	}// textInsertCity
	
}// end class
