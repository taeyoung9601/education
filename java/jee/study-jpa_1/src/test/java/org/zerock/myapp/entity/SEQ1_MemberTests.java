package org.zerock.myapp.entity;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
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
import org.zerock.myapp.util.PersistenceUnits;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2

@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class SEQ1_MemberTests {

	private EntityManagerFactory emf;
	
	
	// JPA api 사용단계:
	// Step1. EntityManagerFactory 객체 생성
	//		  App 당 하나만 생성하면 됩니다.
	// Step2. EntityManager 객체 생성 
    //		  => (**중요**) 이 객체를 Persistence Context 객체라고 부릅니다.
	// 		  필요할 때마다, 여러번 EntityManagerFactory에서 만들어 사용합니다.
	// Step3. Step2의 EntityManager로부터, EntityTransaction 획득
	// Step4. Step3의 트랜잭션 바운더리 안에서, 
	//		  엔티티에 대한 CRUD 작업 수행 및 각종 테스트 수행
	
	
	@BeforeAll
	void beforeAll() {
		log.debug("beforeAll() invoked.");
		
		EntityManagerFactory emf = 
				// 각 영속성 유닛별로 테스트
				Persistence.createEntityManagerFactory(PersistenceUnits.H2);
//				Persistence.createEntityManagerFactory(PersistenceUnits.H2);
//				Persistence.createEntityManagerFactory(PersistenceUnits.H2);
		
		assert emf != null;
		log.info("\t + emf: {} ", emf);
		
		this.emf = emf;
		
	}
	
	
	@AfterAll
	void AfterAll() {
		log.debug("afterAll invoked.");
		
		//@BeforeAll 에서 생성한 emf 를 여기서 close 해줘야합니다.
		// 물론, JUnit 테스트 클래스 일때
		if(this.emf != null) {
			this.emf.close();
			log.info("\t + Closed.");
		}
	}
	
	
	@Disabled
	@Tag("EntityMapping")
	@Order(0)
	@Test
//	@RepeatedTest(1))
	@DisplayName("contextLoads")
	@Timeout(value = 1L, unit=TimeUnit.SECONDS)
	void contextLoads() {
		log.debug("contextLoads() invoked");
	}
	
//	@BeforeEach
//	void beforeEach() {
//		log.debug("beforeEach() invoked.");
//		
//		// 단위 테스트 수행 직전에, emf 로부터 영속성 컨텍스트(즉, EntityManager)를 생성합니다.
//		EntityManager em = this.emf.createEntityManager();
//		assertNotNull(em);
//		log.info("\t + em: {} ", em);
//		
//	}
	
	
//	@Disabled
	@Tag("EntityMapping")
	@Order(1)
	@Test
//	@RepeatedTest(1))
	@DisplayName("testCreateEntities")
	@Timeout(value = 1L * 3, unit=TimeUnit.SECONDS)
	void testCreateEntities() {
		log.debug("testCreateEntities() invoked");
		
		@Cleanup EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			// 개발자가 TCL 문으로 직접 관리하는 트랜잭션, "Local Transaction"이라 한다.
			// Local Transaction 의 반대가, Global Transaction 또는 Distributed Transaction
			// (글로벌 또는 분산 트랜잭션) 이라고 한다.
			tx.begin();
			
			// 명심 : JPA api를 이용해서, 엔티티를 CRUD 할 수 있는 데이터베이스는
			// 		 실제 데이터베이스가 아니라, 위에서 얻은 EntityManager(영속성 컨텍스트라고 불리는)
			//		 가 모든 엔티티를 메모리에서 관리하고, 또한 물리적인 DB와의 동기화를 수행하기때문에
			// 		 이 EntityManager(em)이 JPA api 의 Target Database 가 됩니다.
			
			IntStream.rangeClosed(1, 7).forEach(seq -> {
				//새로운 엔티티 생성 및 em에 저장
				// em에 엔티티를 저장할 때에는, em이 제공하는 em.persist 메소드를 사용합니다.
				Member newMember = new Member();
				newMember.setId("member-%d".formatted(seq));
				newMember.setName("name-%d".formatted(seq));
				newMember.setAge(seq); 	//Auto-boxing
				
				em.persist(newMember);
				
				log.info("-------%d--------".formatted(seq));
			});
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
	}
	
	

//	@Disabled
	@Tag("EntityMapping")
	@Order(2)
	@Test
//	@RepeatedTest(1))
	@DisplayName("testFindEntities")
	@Timeout(value = 1L * 3, unit=TimeUnit.SECONDS)
	void testFindEntities() {
		log.debug("testFindEntities() invoked");
		
		@Cleanup EntityManager em = emf.createEntityManager();
		
		IntStream.of(1,3,5,78).forEachOrdered(seq -> {
			
			// EM에 저장된 키 값이 1,3,5,7 인 엔티티 검색 with em.find 메소드
			String pk = "member-%d".formatted(seq);
			Member foundMember = em.<Member>find(Member.class, pk);

			log.info("foundMember: {} ", foundMember);
			
		});
		
		
	}
	
	
	
	
//	@Disabled
	@Tag("EntityMapping")
	@Order(3)
	@Test
//	@RepeatedTest(1))
	@DisplayName("testUpdateEntities")
	@Timeout(value = 1L * 3, unit=TimeUnit.SECONDS)
	void testUpdateEntities() {
		log.debug("testUpdateEntities() invoked");
		
		@Cleanup EntityManager em = this.emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			// 엔티티의 수정과 관련 em 의 메소드는 없습니다.
			IntStream.of(1,3,5).forEach(seq -> {
				final String pk = "member-%d".formatted(seq);
				Member foundMember = em.<Member>find(Member.class, pk);
				Objects.requireNonNull(foundMember);
				
				// 엔티티의 키 속성 값은 가능하면 변경되면 안되는게 키의 성질이다.
//				foundMember.setId();
				
				// 1st
//				foundMember.setName("Modified-" + seq);
//				foundMember.setAge(23 - seq);
				
				// 2nd. : 동일한 값으로 수정했을 때 update sql이 발생하는가?
				//		  실제로 엔티티의 속성 값에 변화가 없으면 (수정되더라도)
				//		  update sql 은 발생하지 않습니다.	  
//				foundMember.setName(foundMember.getName());
//				foundMember.setAge(foundMember.getAge());
				
				// 3rd Test : 키 속성 값에 대하여..
				//  (1) 키 값의 변경은 가능한가?
				//  (2) 변경이 가능하다면, 이미 존재하는 다른 키로 변경할 때는?
				
//				foundMember.setId("member-33"); // xx : 허용안됨
				
				// JPA에서, 엔티티를 수정하는 명시적인 메소드는 없습니다.
				
			});
			
			
			em.getTransaction().commit();
		} catch(Exception e) {
			em.getTransaction().rollback();
			
			throw e;
		}

	}
	
	
//	@Disabled
	@Tag("EntityMapping")
	@Order(4)
	@Test
//	@RepeatedTest(1))
	@DisplayName("testDeleteEntities")
	@Timeout(value = 1L * 3, unit=TimeUnit.SECONDS)
	void testDeleteEntities() {
		log.debug("testDeleteEntities() invoked");
		
		@Cleanup EntityManager em = this.emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			// Step1. 삭제할 엔티티 조회해서 획득
			Member foundMember = em.<Member>find(Member.class,"member-1");
			log.info("foundMember : {}", foundMember);
			
			// Step2. 획득한 엔티티 삭제.
			assert foundMember != null;
			em.remove(foundMember);
			
			// Step3. 삭제되었는지 확인
			// 즉 . em.contains(entity) 로 해당 엔티티가 현재 em 안에 존재하는지 확인
			boolean isContains = em.contains(foundMember);
			log.info("\t + isContains: {} ", isContains);
			
			tx.commit();
		} catch(Exception e){
			tx.rollback();
		}
		
	}
	
	
	
//	@Disabled
	@Tag("EntityMapping")
	@Order(5)
	@Test
//	@RepeatedTest(1))
	@DisplayName("testDetachEntities")
	@Timeout(value = 1L * 3, unit=TimeUnit.SECONDS)
	void testDetachEntities() {
		log.debug("testDetachEntities() invoked");
		
		@Cleanup EntityManager em = this.emf.createEntityManager();
		Member foundMember = em.<Member>find(Member.class, "member-3");
		log.info("\t1. foundMember : {} " , foundMember);
		
		// Step1. MANAGED -> DETACHED 변경 with em.detach(entity)
		em.detach(foundMember); 	// Detached (준영속)
		log.info("\t2. foundMember : {}", foundMember);
		

		try {
			em.getTransaction().begin();
			
			// Step2. MODIFY
//			foundMember.setName("Tae");
			// Step3. REMOVE
//			em.remove(foundMember);
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			throw e;
		}finally {
			log.info("\t3. foundMember : {} ", foundMember);
		}
		
	}
	
//	@Disabled
	@Tag("EntityMapping")
	@Order(6)
	@Test
//	@RepeatedTest(1))
	@DisplayName("testMergeEntities")
	@Timeout(value = 1L * 3, unit=TimeUnit.SECONDS)
	void testMergeEntities() {
		log.debug("testMergeEntities() invoked");
		
		@Cleanup EntityManager em = this.emf.createEntityManager();
		Member foundMember = em.<Member>find(Member.class, "member-3");
		log.info("\t1. foundMember : {} " , foundMember);
		
		// Step1. DETACHED -> MANAGED 변경 with em.merge(entity)
		em.detach(foundMember);	// DETACHED (준영속)
		Member detachedMember = foundMember;
		
		// em.merge(detachedEntity) 가 반환해주는 entity가
		// 다시 managed 상태의 엔티티가 되므로, 이후 작업은
		// 이 managed 상태의 엔티티로 작업하셔야합니다.
		Member managedMember = em.merge(detachedMember); 	// MANAGED (준영속)
		log.info("\t2. foundMember : {}", detachedMember);
		

		try {
			em.getTransaction().begin();
			
			// Step2. MODIFY
			managedMember.setName("Tae");
			// Step3. REMOVE
//			em.remove(foundMember);		//?
			
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
			throw e;
		}finally {
			log.info("\t3. managedMember : {} ", managedMember);
		}
	
	}
	
	
//	@Disabled
	@Tag("EntityMapping")
	@Order(7)
	@Test
//	@RepeatedTest(1))
	@DisplayName("testClearEntityManager")
	@Timeout(value = 1L * 3, unit=TimeUnit.SECONDS)
	void testClearEntityManager() {
		log.debug("testClearEntityManager() invoked");
		
		@Cleanup EntityManager em = emf.createEntityManager();
		Member foundMember = em.<Member>find(Member.class, "member-4");
		log.info("\t1. foundMember : {} " , foundMember);
		
		em.clear();
		
		boolean isContains = em.contains(foundMember);
		log.info("\t2 + .isContains: {}" , isContains);
		
	}
	
}// end class
