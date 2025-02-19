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
import org.zerock.myapp.entity.Locker2;
import org.zerock.myapp.entity.Student2;
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
//		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

		// 3. Set Hibernate Naming Strategies (Converting Camel-Case To Snake-Case)
		properties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");

		// 4. Set Other Hibernate Properties
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.use_sql_comments", false);
		properties.put("hibernate.id.new_generator_mappings", true);

		// ------------------
//		 Create EntityManagerFactory with the specified Persistence Unit and Hibernate Properties
//		EntityManagerFactory emf =	Persistence.createEntityManagerFactory(PersistenceUnits.H2, properties);
//		EntityManagerFactory emf =	Persistence.createEntityManagerFactory(PersistenceUnits.ORACLE, properties);
		EntityManagerFactory emf =	Persistence.createEntityManagerFactory(PersistenceUnits.MYSQL, properties);

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
	@Tag("One-To-One-Bi")
	@Order(0)
	@Test
//	@RepeatedTest(1)
	@DisplayName("contextLoads")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void contextLoad() {
		log.debug("contextLoad() invoked.");
	}
	
	
//	@Disabled
	@Tag("One-To-One-Bi")
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
				Locker2 transientLocker = new Locker2();
				transientLocker.setName("NAME-"+seq);
				
				this.em.persist(transientLocker);
			});
			
			this.em.getTransaction().commit();
		}catch(Exception e) {
			this.em.getTransaction().rollback();
			throw e;
		}
		
		//--2----
		Locker2 locker1 = this.em.<Locker2>find(Locker2.class, 1L);
		Locker2 locker2 = this.em.<Locker2>find(Locker2.class, 2L);
		Locker2 locker3 = this.em.<Locker2>find(Locker2.class, 3L);
		
		Objects.nonNull(locker1);
		Objects.nonNull(locker2);
		Objects.nonNull(locker3);
		
		//--3---
		this.em.getTransaction().begin();
		
		IntStream.rangeClosed(1, 7).forEachOrdered(seq -> {
			Student2 transientStudent = new Student2();
			transientStudent.setName("Name-"+seq);
			
			switch(seq) {
				case 1 -> transientStudent.setLocker(locker1);
				case 2 -> transientStudent.setLocker(locker2);
				case 3 -> transientStudent.setLocker(locker3);
			}
			
			this.em.persist(transientStudent);
		});
		
		this.em.getTransaction().commit();
		
	}
	
	
	
//	@Disabled
	@Tag("One-To-One-Bi")
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("2. testObjectGraphTraverseFromStudentToLocker")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void testObjectGraphTraverseFromLockerToStudent() {
		log.debug("testObjectGraphTraverseFromStudentToLocker() invoked.");
		
		LongStream.of(1L,2L,3L).forEach(seq -> {
			Locker2 foundLocker = this.em.<Locker2>find(Locker2.class, seq);
			
			Objects.nonNull(foundLocker);
			log.info("\t + student : {}", foundLocker);
		});
		
		
	}
	

} // end class
