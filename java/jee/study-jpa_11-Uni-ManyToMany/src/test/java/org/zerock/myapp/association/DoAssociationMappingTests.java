package org.zerock.myapp.association;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

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
import org.zerock.myapp.entity.Product1;
import org.zerock.myapp.entity.Shopper1;
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
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
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
//		EntityManagerFactory emf =	Persistence.createEntityManagerFactory(PersistenceUnits.H2, properties);
		EntityManagerFactory emf =	Persistence.createEntityManagerFactory(PersistenceUnits.ORACLE, properties);
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
	@Tag("Many-To-Many-Uni")
	@Order(0)
	@Test
//	@RepeatedTest(1)
	@DisplayName("contextLoads")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void contextLoad() {
		log.debug("contextLoad() invoked.");
	}
	
	
	
//	@Disabled
	@Tag("Many-To-Many-Uni")
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("prepareData")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void prepareData() {
		log.debug("prepareData() invoked.");
		
		//--1---
		
		this.em.getTransaction().begin();
		
		IntStream.rangeClosed(1, 7).forEachOrdered(seq ->{
			Product1 transientProduct = new Product1();
			transientProduct.setName("NAME"+seq);
			
			this.em.persist(transientProduct);
		});
		
		this.em.getTransaction().commit();
		
		//--2---
		
		Product1 product1 = this.em.<Product1>find(Product1.class, 1L);
		Product1 product2 = this.em.<Product1>find(Product1.class, 2L);
		Product1 product3 = this.em.<Product1>find(Product1.class, 3L);
		Product1 product4 = this.em.<Product1>find(Product1.class, 4L);
		Product1 product5 = this.em.<Product1>find(Product1.class, 5L);
		Product1 product6 = this.em.<Product1>find(Product1.class, 6L);
		Product1 product7 = this.em.<Product1>find(Product1.class, 7L);
		
		Objects.requireNonNull(product1);
		Objects.requireNonNull(product2);
		Objects.requireNonNull(product3);
		Objects.requireNonNull(product4);
		Objects.requireNonNull(product5);
		Objects.requireNonNull(product6);
		Objects.requireNonNull(product7);

		//--3---
		this.em.getTransaction().begin();
		
		IntStream.of(1,2,3).forEachOrdered(seq ->{
			Shopper1 transientShopper = new Shopper1();
			transientShopper.setName("Name"+seq);
			
			switch(seq) {
				case 1 -> {
					transientShopper.addOrder(product7);
					transientShopper.addOrder(product3);
					transientShopper.addOrder(product1);
				}
				case 2 -> {
					transientShopper.addOrder(product2);
					transientShopper.addOrder(product6);
				}
				case 3 -> {
					transientShopper.addOrder(product4);
					transientShopper.addOrder(product5);
				}
			
			}
			
			this.em.persist(transientShopper);
		});
		
		this.em.getTransaction().commit();
	}

	
//	@Disabled
	@Tag("Many-To-Many-Uni")
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("2. testObjectGraphTraverseFromShopperToProduct")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void testObjectGraphTraverseFromShopperToProduct() {
		log.debug("testObjectGraphTraverseFromShopperToProduct() invoked.");
		
		 Long shopperId = new Random().nextLong(1L, 4L); // Half-open
	      
		 Shopper1 shopper = this.em.<Shopper1>find(Shopper1.class,shopperId);
		 Objects.requireNonNull(shopper);
      
		 shopper.getProducts().forEach(p -> log.info(p.toString()));	

		
	}
	
	
} // end class
