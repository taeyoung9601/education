package org.zerock.myapp.persistence;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.domain.Person;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

// 명시적으로 스프링부트의 핵심구성요소인 "분산트랜잭션관리자" (Transaction Manager)
// 
//
@Transactional

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@SpringBootTest
public class PersonRepositoryTests {
	@Autowired private PersonRepository repo;		// SimpleJdbcRepository
	
	@BeforeAll
	void beforeAll() {
		log.debug("beforeAll() invoked.");
		
		log.info("\t + this.repo:{}", this.repo);
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
	@Tag("Spring-Data-JDBC")
	@Order(1)
	@Test
//	@Repeatable(1)
	@DisplayName("1. testFindAll")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
	void testFindAll() {
		log.debug("testFindAll() invoked.");
		
		long total = this.repo.count();
		log.info("\t + total:{}", total);
		
		Iterable<Person> iter = this.repo.findAll();		
		for(Person person : iter) {
			log.info(person.toString());
		}// enhanced for

		
	}//testFindAll
	
	
//	@Disabled
	@Tag("MyBatis-Mapper-Interface")
	@Order(2)
	@Test
//	@Repeatable(1)
	@DisplayName("2. testCreate")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
	void testCreate() {
		log.debug("testCreate() invoked.");
		
		Person person = new Person();		// New Entity
//		person.setId(null);					// Identity Column(Oracle), Auto Increment(MySQL,H2)
		person.setName("Pyramide");		
		person.setAge(23);
		
		log.info("\t + Before:{}", person);
		
		person = this.repo.save(person);				// Repository(저장소)에 저장 -> 매핑될 테이블에 저장시킨다!
	
		log.info("\t + After : {}", person);
		
	}//testCreate
	
	
	
//	@Disabled
	@Tag("Spring-Data-JDBC")
	@Order(3)
	@Test
//	@Repeatable(1)
	@DisplayName("3. testRead")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
	void testRead() {
		log.debug("testRead() invoked.");
		
		final Long id = 101L;
		Optional<Person> optional = this.repo.findById(id);
		if (!optional.isEmpty()) {
			Person foundPerson = optional.get();
			log.info("\t + foundPerson:{}", foundPerson);
		}// if
		
		optional.ifPresent(p -> log.info("\t + foundPerson:{}", p));
	}//testRead
	
	
//	@Disabled
	@Tag("Spring-Data-JDBC")
	@Order(4)
	@Test
//	@Repeatable(1)
	@DisplayName("4. testUpdate")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
	void testUpdate() {
		log.debug("testUpdate() invoked.");
		
		final Long id = 101L;
		Optional<Person> optional = this.repo.findById(id);
		optional.ifPresent(foundPerson->{
			log.info("Before:{}", foundPerson);
			
			foundPerson.setName("Tae");
			foundPerson.setAge(22);
			
			// 변경된 엔티티를 다시 저장소에 저장
			Person modifiedPerson = this.repo.save(foundPerson);
			
			log.info("After:{}", modifiedPerson);
		});// ifPresent
		
	}//testUpdate


//	@Disabled
	@Tag("Spring-Data-JDBC")
	@Order(5)
	@Test
//	@Repeatable(1)
	@DisplayName("5. testDelete")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
	void testDelete() {
		log.debug("testDelete() invoked.");
		
		//--1-----
		//final Long id = 201L;
		//this.repo.deleteById(id);
		
		//--2-----
		final Long id = 157L;
		Optional<Person> optional = this.repo.findById(id);
		
		optional.ifPresent(foundPerson ->{
			this.repo.delete(foundPerson);
		});
		
	}//testDelete
	
	
	
//	@Disabled
	@Tag("Spring-Data-JDBC")
	@Order(6)
	@Test
//	@Repeatable(1)
	@DisplayName("6. testFindByName")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
	void testFindByName() {
		log.debug("testFindByName() invoked.");

		final String searchName= "NAME_1";
		List<Person> list = this.repo.findByName(searchName);
		
		Objects.requireNonNull(list);
		list.forEach(fp -> log.info(fp.toString()));
	
		
	}//testFindByName
	
	
//	@Disabled
	@Tag("Spring-Data-JDBC")
	@Order(7)
	@Test
//	@Repeatable(1)
	@DisplayName("7. testUpdateNameById")
	@Timeout(value = 1L * 3 , unit = TimeUnit.SECONDS)
//	@Commit
	@Rollback(false)	// == @Commit
//	@Rollback(true)		// == 롤백수행
	void testUpdateNameById() {
		log.debug("testUpdateNameById() invoked.");

		Boolean isUpdated = this.repo.updateByIdAndNames(102L, "Kim");
		log.info("\t + isDeleted:{}", isUpdated);
	
		
	}//testUpdateNameById
	
}// end class
