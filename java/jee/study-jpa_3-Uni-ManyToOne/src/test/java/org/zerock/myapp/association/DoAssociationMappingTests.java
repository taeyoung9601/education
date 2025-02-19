package org.zerock.myapp.association;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
		// Create EntityManagerFactory with the specified Persistence Unit and Hibernate Properties
		EntityManagerFactory emf =	Persistence.createEntityManagerFactory(PersistenceUnits.H2, properties);
//		EntityManagerFactory emf =	Persistence.createEntityManagerFactory(PersistenceUnits.ORACLE, properties);
//		EntityManagerFactory emf =	Persistence.createEntityManagerFactory(PersistenceUnits.MYSQL, properties);

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
	@Timeout(value =3L, unit = TimeUnit.SECONDS)
	void prepareData() {
		log.debug("prepareData() invoked.");
		
		try {
			this.em.getTransaction().begin();
			
			//--1---
			LongStream.rangeClosed(1L, 3L).forEachOrdered(seq -> {
				// 주의사항 : 두 엔티티 중에, FK 관계를 고려하여 누가 먼저 영속화(persist)되어야 하는지를 고려해야 합니다.
				Team transientTeam = new Team();
				transientTeam.setName("Team-"+seq);
				
				this.em.persist(transientTeam);
			});
			
			//--2---
			Team team1 = this.em.<Team>find(Team.class, 1L);
			Team team2 = this.em.<Team>find(Team.class, 2L);
			Team team3 = this.em.<Team>find(Team.class, 3L);
			
			assert team1 != null;
			assertNotNull(team2);
			Objects.requireNonNull(team3);
			
			//--3---
			IntStream.rangeClosed(1, 7).forEach(seq -> {
				Member transientMember = new Member();
				transientMember.setName("Name-"+seq);
				
				int teamNo = new Random().nextInt(1,4);
				switch(teamNo) {
				case 1 -> {transientMember.setTeam(team1);}
				case 2 -> {transientMember.setTeam(team2);}
				case 3 -> {transientMember.setTeam(team3);}
				}	// switch expression
				
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
	@DisplayName("2. testFindMember")
	@Timeout(value =3L, unit = TimeUnit.SECONDS)
	void testFindMember() {
		log.debug("testFindMember() invoked.");
		
		int memberNo = new Random().nextInt(1,8);	// Half-open
		Member foundMember = this.em.<Member>find(Member.class, Long.valueOf(memberNo));
		
		assert foundMember != null;
		log.info("\t + foundMember : {} " , foundMember);
		
		//Object Graph Traverse
		log.info("\t + My team : {} ", foundMember.getTeam());
	}
	
	
	
//	@Disabled
	@Tag("DoAssociationMapping")
	@Order(3)
	@Test
//	@RepeatedTest(3)
	@DisplayName("3. testFindMemberWithJPQL")
	@Timeout(value =3L, unit = TimeUnit.SECONDS)
	void testFindMemberWithJPQL() {
		log.debug("testFindMemberWithJPQL() invoked.");
		
		// JPQL를 사용한다는 말은, 엔티티들 간의 연관관계를 무시하고
		// 자식엔티티가 소유한 FK를 이용하여 쿼리를 하겠다!
		
		// 주의사항 : 모든 절의 대상은 엔티티와 엔티티의 필드가 됩니다.
		String jpql = "SELECT m FROM Member m WHERE m.team.id = 3 ORDER BY m.id DESC";
		TypedQuery<Member> typedQuery = this.em.<Member>createQuery(jpql,Member.class);
		log.info("\t + typedQuery: {}", typedQuery);
		
		List<Member> resultList = typedQuery.getResultList();
//		resultList.forEach(log::info);
		resultList.forEach(m -> log.info("\t + m: {}, team: {} ", m , m.getTeam()));
	}
	
	
	
//	@Disabled
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Tag("DoAssociationMapping")
	@Order(4)
	@Test
//	@RepeatedTest(3)
	@DisplayName("4. testFindMemberWithNativeSQL")
	@Timeout(value =3L, unit = TimeUnit.SECONDS)
	void testFindMemberWithNativeSQL() {
		log.debug("testFindMemberWithNativeSQL() invoked.");
		
		//--1---
		// Native Query Creation
		final String nativeSQL = "SELECT * FROM member WHERE my_team = 1";
		Query nativeQuery = this.em.createNativeQuery(nativeSQL, Member.class);
		log.info("\t + query: {} ", nativeQuery);
		
		//--2---
		// Execute Native SQL and Get ResiltSet by List
		List resultList = nativeQuery.getResultList();
		
		resultList.forEach(obj -> {
			if(obj instanceof Member member) {
				log.info("\t + member: {}, team: {}", member, member.getTeam());
			}
		});
		
		
	}
	
}
