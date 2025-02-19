package org.zerock.myapp.association;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
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
import org.zerock.myapp.entity.Locker3;
import org.zerock.myapp.entity.Student3;
import org.zerock.myapp.util.PersistenceUnits;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Log4j2
@Slf4j
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

public class DoAssociationMappingTests {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	@BeforeAll
	void beforeAll() {
		log.debug("beforeAll() invoked.");

		// ------------------
		Map<String, Object> properties = new HashMap<>();
		
		// 1. Supported values include 'create', 'create-drop', 'create-only', 'drop', 'update', 'none' and 'validate'.
		properties.put("hibernate.hbm2ddl.auto", "update");

		// 2. Set Hibernate Dialect
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
//		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

		// 3. Set Hibernate Naming Strategies (Converting Camel-Case To Snake-Case)
		properties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");

		// 4. Set Other Hibernate Properties
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.use_sql_comments", false);
		properties.put("hibernate.id.new_generator_mappings", true);

		// ------------------
//		 Create EntityManagerFactory with the specified Persistence Unit and Hibernate Properties
		EntityManagerFactory emf =	Persistence.createEntityManagerFactory(PersistenceUnits.H2, properties);
//		EntityManagerFactory emf =	Persistence.createEntityManagerFactory(PersistenceUnits.ORACLE, properties);
//		EntityManagerFactory emf =	Persistence.createEntityManagerFactory(PersistenceUnits.MYSQL, properties);

		// ------------------
		assert emf != null;
		
		this.emf = emf;
		log.info("\t + this.emf : {} ", this.emf);
		
		this.em = this.emf.createEntityManager();
		log.info("\t + this.em : {} ", this.em );
	}
	
	
	@AfterAll
	void afterAll() {
		log.debug("afterAll() invoked.");
		
		if(this.emf != null) this.emf.close();
		log.info("\t + this.emf closed.");
	}
	
	
	@BeforeEach
	void beforeEach() {
		log.debug("beforeEach() invoked.");
		
		this.em = this.emf.createEntityManager();
		log.info("\t + this.em : {} ", this.em);
	}
	
	@AfterEach
	void afterEach() {
		log.debug("afterEach() invoked.");
		
		if(this.em != null) this.em.close();
		log.info("this.em closed.");
	}
	
	@Disabled
	@Tag("One-To-One-Bi-Right")
	@Order(0)
	@Test
//	@RepeatedTest(1)
	@DisplayName("contextLoads")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void contextLoad() {
		log.debug("contextLoad() invoked.");
	}
	
	
//	@Disabled
	@Tag("One-To-One-Bi-Right")
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. prepareData")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void prepareData() {
		log.debug("prepareData() invoked.");
		
		try {
			this.em.getTransaction().begin();
			
			IntStream.of(1,2,3).forEachOrdered(seq -> {
				Student3 transientStudent = new Student3();
				transientStudent.setName("NAME-"+seq);
				
				this.em.persist(transientStudent);
			});
			
			this.em.getTransaction().commit();
		}catch(Exception e) {
			this.em.getTransaction().rollback();
			throw e;
		}
		
		//--2----
		Student3 student1 = this.em.<Student3>find(Student3.class, 1L);
		Student3 student2 = this.em.<Student3>find(Student3.class, 2L);
		Student3 student3 = this.em.<Student3>find(Student3.class, 3L);
		
		Objects.nonNull(student1);
		Objects.nonNull(student2);
		Objects.nonNull(student3);
		
		//--3---
		this.em.getTransaction().begin();
		
		IntStream.of(1,2,3).forEachOrdered(seq -> {
			Locker3 transientLocker = new Locker3();
			transientLocker.setName("Name-"+seq);
			
			// Set FK < --** : 이 때 편의 메소드에 의해서, Main(Locker)의 사용자(Student)도 함께 설정됨
			if(seq == 1) transientLocker.setStudent(student1);		
			if(seq == 2) transientLocker.setStudent(student2);
			if(seq == 3) transientLocker.setStudent(student3);
			
			this.em.persist(transientLocker);
		});
		
		this.em.getTransaction().commit();
		
	}
	
	
	
//	@Disabled
	@Tag("One-To-One-Bi-Right")
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("2. testObjectGraphTraverseFromLockerToStudent")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void testObjectGraphTraverseFromLockerToStudent() {
		log.debug("testObjectGraphTraverseFromLockerToStudent() invoked.");
		
		LongStream.of(1L,2L,3L).forEach(seq -> {
			Locker3 foundLocker = this.em.<Locker3>find(Locker3.class, seq);
			
			Objects.nonNull(foundLocker);
			log.info("\t + foundLocker : {}", foundLocker);
		});
		
		
	}
	

	
//	@Disabled
	@Tag("One-To-One-Bi-Right")
	@Order(3)
	@Test
//	@RepeatedTest(1)
	@DisplayName("3. testObjectGraphTraverseFromStudentToLocker")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void testObjectGraphTraverseFromStudentToLocker() {
		log.debug("testObjectGraphTraverseFromStudentToLocker() invoked.");
		
		LongStream.of(1L,2L,3L).forEach(seq -> {
			Student3 foundStudent = this.em.<Student3>find(Student3.class, seq);
			
			Objects.nonNull(foundStudent);
			log.info("\t + foundStudent : {}", foundStudent);
		});
		
		
	}
	
} // end class
