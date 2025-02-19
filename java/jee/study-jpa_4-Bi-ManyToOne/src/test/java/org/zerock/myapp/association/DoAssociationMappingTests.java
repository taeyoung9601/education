package org.zerock.myapp.association;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.zerock.myapp.entity.Member;
import org.zerock.myapp.entity.Team;
import org.zerock.myapp.util.PersistenceUnits;

import lombok.extern.log4j.Log4j2;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)


@Log4j2
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
		properties.put("hibernate.show_sql", false);
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
	
	
//	@Disabled
	@Tag("DoAssociationMapping")
	@Order(0)
	@Test
//	@RepeatedTest(3)
	@DisplayName("contextLoads")
	@Timeout(value =3L, unit = TimeUnit.SECONDS)
	void contextLoads() {
		log.debug("contextLoads() invoked.");
	}
	
//	@Disabled
	@Tag("DoAssociationMapping")
	@Order(1)
	@Test
//	@RepeatedTest(3)
	@DisplayName("1. prepareData")
	@Timeout(value =3L, unit = TimeUnit.MINUTES,threadMode = ThreadMode.INFERRED)
	void prepareData() {
		log.debug("prepareData() invoked.");
		
		try {
			this.em.getTransaction().begin();
			
			//--1---
			// 3개의 팀 생성 및 저장
			IntStream.of(1,2,3).forEachOrdered(seq -> {
				Team transientTeam = new Team();
				transientTeam.setName("Name-"+seq);
				
				this.em.persist(transientTeam);
			});
			
			//--2--
			// 회원에 설정할 팀들을 조회하여 획득
		
			Team team1 = this.em.<Team>find(Team.class,1L);
			Team team2 = this.em.<Team>find(Team.class,2L);
			Team team3 = this.em.<Team>find(Team.class,3L);
			
			Objects.requireNonNull(team1);
			Objects.requireNonNull(team2);
			Objects.requireNonNull(team3);
			
			//--3---
			// 9명의 멤버를 새로/생성 및 저장하되, 1-6까지는 Team1에 소속
			// 나머지 7-9 까지는 Team3에 소속시킵니다.
			
			IntStream.rangeClosed(1, 6).forEachOrdered(seq -> {
				Member transientMember = new Member();
				transientMember.setName("Name-"+seq);
				
				if(seq != 6) transientMember.setMyTeam(team1);	// Team 1에 소속시킴
				
				this.em.persist(transientMember);
			});
			
			IntStream.of(7,8,9).forEachOrdered(seq -> {
				Member transientMember = new Member();
				transientMember.setName("Name-"+seq);
				
				transientMember.setMyTeam(team3);	// Team 3에 소속시킴
				
				this.em.persist(transientMember);
			});
			
			this.em.getTransaction().commit();
		} catch(Exception e) {
			this.em.getTransaction().rollback();
			
			throw e;
		}
	}
	
//	@Disabled
	@Tag("DoAssociationMapping")
	@Order(2)
	@Test
//	@RepeatedTest(3)
	@DisplayName("2. testObjectGraphTraverseFromMemberToTeam")
	@Timeout(value =3L, unit = TimeUnit.SECONDS)
	void testObjectGraphTraverseFromMemberToTeam() {
		log.debug("testObjectGraphTraverseFromMemberToTeam() invoked.");
		
		LongStream.rangeClosed(1, 9).forEachOrdered(seq ->{
			Member foundMember = this.em.<Member>find(Member.class,Long.valueOf(seq));
			
			Objects.requireNonNull(foundMember);
			log.info("\t + foundMember:{} ", foundMember);
		});
		
	}
	
	
//	@Disabled
	@Tag("DoAssociationMapping")
	@Order(3)
	@Test
//	@RepeatedTest(3)
	@DisplayName("3. testObjectGraphTraverseFromTeamToMember")
	@Timeout(value =3L, unit = TimeUnit.SECONDS)
	void testObjectGraphTraverseFromTeamToMember() {
		log.debug("testObjectGraphTraverseFromTeamToMember() invoked.");
		
		Team team2 = this.em.<Team>find(Team.class,2L);
				
		LongStream.of(1L,2L,3L).forEachOrdered(seq ->{
			Team foundTeam = this.em.<Team>find(Team.class,seq);
			
			Objects.requireNonNull(foundTeam);
			log.info("\t + foundTeam:{} ", foundTeam);
			
			Objects.requireNonNull(foundTeam.getMembers());
			foundTeam.getMembers().forEach( m -> log.info(m.toString()));
//			foundTeam.getMembers().forEach(log::info);
			
			//--2---
			if(seq == 1L)foundTeam.getMembers().get(0).setMyTeam(team2);
			
		});
		
	}
	
}
