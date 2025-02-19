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
import org.zerock.myapp.entity.Orders;
import org.zerock.myapp.entity.Product3;
import org.zerock.myapp.entity.Shopper3;
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
	@Tag("Many-To-Many-Bi-with-CrossEntity")
	@Order(0)
	@Test
//	@RepeatedTest(1)
	@DisplayName("contextLoads")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void contextLoad() {
		log.debug("contextLoad() invoked.");
	}
	
//	@Disabled
	@Tag("Many-To-Many-Bi-with-CrossEntity")
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. prepareData")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void prepareData() {
		log.debug("prepareData() invoked.");
		
		//--1---
		// 1:N 관계 (여기서, N은 교차 테이블)에서는, 1을 먼저 생성/저장
		this.em.getTransaction().begin();
		
		IntStream.of(1,2,3).forEach(seq -> {
			Shopper3 transientShopper = new Shopper3();
			transientShopper.setName("Name-"+seq);
			
			// 컬렉션으로 관리되는, 주문내역은 교차테이블(주문내역)에 저장되기때문에, 그 때 가서 생성합니다.
			
			this.em.persist(transientShopper);
		});
		
		this.em.getTransaction().commit();
		//--2---
		// 1:N 관계 (여기서, N은 교차 테이블)에서는, 1을 먼저 생성/저장
		this.em.getTransaction().begin();
		
		IntStream.rangeClosed(1, 7).forEach(seq -> {
			Product3 transientProduct = new Product3();
			transientProduct.setName("Product-"+seq);
			
			this.em.persist(transientProduct);
		});
		
		this.em.getTransaction().commit();
		//--3---
		// 1,2단계에서 고객과 상품이 등록되었으니, 이를 기반으로 "주문하다"를 수행할 수 있겠죠?
		this.em.getTransaction().begin();
		
		IntStream.rangeClosed(1, 21).forEach(seq->{
			Orders transientOrders = new Orders();
			
			// 무작위로 선정된 고객(Shopper3)을 주문 고객으로 설정하고
			// 무작위로 선정된 상품(Product3)을 주문 상품으로 설정합시다!
			var shopperId = new Random().nextLong(1,4);
			var productId = new Random().nextLong(1,8);
			
			// 중요: 다른 엔티티를 현재 엔티티에서 사용해야 한다면,
			// 	   JPA에서는 반드시 Managed 상태의 엔티티를 사용해야 합니다.
			//     때문에, em.find로 조회해서 사용해야합니다.
			
			Shopper3 foundShopper = this.em.find(Shopper3.class, shopperId);
			Product3 foundProduct = this.em.find(Product3.class, productId);
			
			log.info("\t + foundShopper : {} " , foundShopper);
			log.info("\t + foundProduct : {} " , foundProduct);
			
			transientOrders.setShopperFK(foundShopper);		// 가장 중요 : FK 엔티티 설정
			transientOrders.setProductFK(foundProduct);		// 가장 중요 : FK 엔티티 설정
			transientOrders.setOrderAmount(seq);
			transientOrders.setOrderPrice(1000 * seq);
			
			this.em.persist(transientOrders);
		});
		
		
		this.em.getTransaction().commit();
		
		
	}
	
	
//	@Disabled
	@Tag("Many-To-Many-Bi-with-CrossEntity")
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("2. testObjectGraphTraverseFromShopperToOrders")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void testObjectGraphTraverseFromShopperToOrders() {
		log.debug("testObjectGraphTraverseFromShopperToOrders() invoked.");
		
		Long shopperId = new Random().nextLong(1L,4L);
		Shopper3 shopper = this.em.<Shopper3>find(Shopper3.class, shopperId);
		
		Objects.requireNonNull(shopper);
		
		log.info("shopper : {}", shopper);	
		shopper.getMyOrders().forEach(o -> log.info("orders : {} " , o));
	}

	
//	@Disabled
	@Tag("Many-To-Many-Bi-with-CrossEntity")
	@Order(3)
	@Test
//	@RepeatedTest(1)
	@DisplayName("3. testObjectGraphTraverseFromProductToOrders")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void testObjectGraphTraverseFromProductToOrders() {
		log.debug("testObjectGraphTraverseFromProductToOrders() invoked.");
		
		Long productId = new Random().nextLong(1L,8L);
		Product3 product = this.em.<Product3>find(Product3.class, productId);
		
		Objects.requireNonNull(product);
		
		log.info("shopper : {}", product);	
		product.getMyOrders().forEach(o -> log.info("orders : {} " , o));
	}
	
	
//	@Disabled
	@Tag("Many-To-Many-Bi-with-CrossEntity")
	@Order(4)
	@Test
//	@RepeatedTest(1)
	@DisplayName("4. testObjectGraphTraverseOrders")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void testObjectGraphTraverseOrders() {
		log.debug("testObjectGraphTraverseOrders() invoked.");
		
		Long orderId = new Random().nextLong(1L,22L);
		Orders foundorder = this.em.<Orders>find(Orders.class, orderId);
		
		Objects.requireNonNull(foundorder);
		
		log.info("foundorder : {}", foundorder);

	}
	
//	@Disabled
	@Tag("Many-To-Many-Bi-with-CrossEntity")
	@Order(5)
	@Test
//	@RepeatedTest(1)
	@DisplayName("5. testObjectGraphTraverseAllOrders")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void testObjectGraphTraverseAllOrders() {
		log.debug("testObjectGraphTraverseAllOrders() invoked.");
		
		LongStream.rangeClosed(1L, 21L).forEach(seq ->{
			Orders foundOrder = this.em.find(Orders.class, seq);
			Objects.requireNonNull(foundOrder);
			
			log.info("\t+ foundOrder : {}" , foundOrder);
			
		});

	}
	
} // end class
