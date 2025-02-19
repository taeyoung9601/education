package org.zerock.myapp.association;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
import org.slf4j.Marker;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.entity.Member;
import org.zerock.myapp.entity.Team;
import org.zerock.myapp.util.PersistenceUnits;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
		// Create EntityManagerFactory with the specified Persistence Unit and Hibernate Properties
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
	@Tag("One-To-Many")
	@Order(0)
	@Test
//	@RepeatedTest(1)
	@DisplayName("contextLoads")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void contextLoad() {
		log.debug("contextLoad() invoked.");
	}
	
	
	
//	@Disabled
	@Tag("One-To-Many")
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. prepareData")
	@Timeout(value = 1L, unit = TimeUnit.SECONDS)
	void prepareData() {
		log.debug("prepareData() invoked.");
		
		// 1. 부모(Team) 엔티티 생성 및 저장
		try {
			this.em.getTransaction().begin();
			IntStream.of(1,2,3).forEach(seq -> {
				Team transientTeam = new Team();
				transientTeam.setName("Name-"+seq);
				
				em.persist(transientTeam);
			});
			this.em.getTransaction().commit();
		}catch(Exception e) {
			this.em.getTransaction().rollback();
			
			throw e;
		}
		
		//2.Managed 상태의 3개 팀 조회
		Team team1 = this.em.<Team>find(Team.class, 1L);
		Team team2 = this.em.<Team>find(Team.class, 2L);
		Team team3 = this.em.<Team>find(Team.class, 3L);
		
		Objects.requireNonNull(team1);
		assertNotNull(team2);
		assert team3 != null;
		
		// 3. 자식(Member) 엔티티 생성 및 저장
		try {
			this.em.getTransaction().begin();
			
			IntStream.rangeClosed(1, 20).forEachOrdered(seq -> {
				Member transientMember = new Member();
				transientMember.setName("Name-"+seq);
				
				// 새로운 팀원마다, 자기의 팀에 배정 시킴
				// Team 의 List<Member> 컬렉션에, 각각의 팀원 저장
				if(seq >= 15) {team3.getMembers().add(transientMember);}
				else if(seq >= 8) {team2.getMembers().add(transientMember);}
				else team1.getMembers().add(transientMember);
				
				this.em.persist(transientMember);
			});	
			this.em.getTransaction().commit();
		}catch (Exception e) {
			this.em.getTransaction().rollback();
			
			throw e;
		}
	} // prepareData
	
	
//	@Disabled
	@Tag("One-To-Many")
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testObjectGraphTraverseFromTeamToMember")
	@Timeout(value = 3L, unit = TimeUnit.SECONDS)
	void testObjectGraphTraverseFromTeamToMember() {
		log.debug("testObjectGraphTraverseFromTeamToMember() invoked.");
		
		// One-To-Many Traverse
		// Do change hbm2ddl.auto mode update
		
		Team team1 = em.<Team>find(Team.class, 1L);
		Team team2 = em.<Team>find(Team.class, 2L);
		Team team3 = em.<Team>find(Team.class, 3L);
		
		Objects.nonNull(team1);
		assert team2 != null;
		assertNotNull(team3);
		
		log.info("=".repeat(30));
		team1.getMembers().forEach(m -> log.info(m.toString()));
		log.info("=".repeat(30));
		team2.getMembers().forEach(m -> log.info(m.toString()));
		log.info("=".repeat(30));
		team3.getMembers().forEach(m -> log.info(m.toString()));
		
	}// testObjectGraphTraverseFromTeamToMember
	
} // end class
