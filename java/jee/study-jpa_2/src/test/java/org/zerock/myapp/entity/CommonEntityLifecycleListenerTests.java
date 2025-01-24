package org.zerock.myapp.entity;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class CommonEntityLifecycleListenerTests {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	@BeforeAll
	void beforeAll() {
		log.debug("beforeAll() invoked.");
		
		//------
		this.emf = Persistence.createEntityManagerFactory(PersistenceUnits.H2);
		log.info("\t + this.emf : {}", this.emf);
		
		//------
		this.em = this.emf.createEntityManager();
		log.info("\t + this.em : {}", this.em);
	}
	
	@AfterAll
	void afterAll() {
		log.debug("afterAll() invoked.");
		
		if(this.em != null) this.em.close();
		if(this.emf != null) this.emf.close();
	}
	
	@Disabled
	@Tag("CommonEntityLifecycleListenerTests")
	@Order(0)
	@Test
//	@RepeatedTest(1)
	@DisplayName("contextLoads")
	@Timeout(value=3L, unit=TimeUnit.SECONDS)
	void contextLoads() {
		log.debug("contextLoad() invoked.");
	}
	
	
//	@Disabled
	@Tag("CommonEntityLifecycleListenerTests")
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1.testPersist")
	@Timeout(value=3L, unit=TimeUnit.SECONDS)
	void testPersist() {
		log.debug("testPersist() invoked.");
		
		Member3 transientMember = new Member3();	// TRANSIENT
		transientMember.setName("Tae");
		transientMember.setAge(null);
		transientMember.setWeight(null);
		
		this.em.persist(transientMember); 			// -> MANAGED
		log.info("\t+ managedMember: {} " , transientMember);
		
		try {
			this.em.getTransaction().begin();
			log.info("\t + managedMember: {}", transientMember);
			
			this.em.getTransaction().commit();
		}catch(Exception e){
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	
//	@Disabled
	@Tag("CommonEntityLifecycleListenerTests")
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("2.testCreateEntities")
	@Timeout(value=3L, unit=TimeUnit.SECONDS)
	void testCreateEntities() {
		log.debug("testCreateEntities() invoked.");
		
		this.em.getTransaction().begin();
		
		IntStream.rangeClosed(1, 7).forEach(seq -> {
			Member3 transientMember = new Member3();
			transientMember.setName("Name-"+seq);
			transientMember.setAge(seq);
			transientMember.setWeight(seq * 1.0);
			
			em.persist(transientMember);
		});
		
		this.em.getTransaction().commit();
	}
	
//	@Disabled
	@Tag("CommonEntityLifecycleListenerTests")
	@Order(3)
	@Test
//	@RepeatedTest(1)
	@DisplayName("3.testSelectEntities")
	@Timeout(value=3L, unit=TimeUnit.SECONDS)
	void testSelectEntities() {
		log.debug("testSelectEntities() invoked.");
	
		LongStream.of(1,3,7).forEach(seq -> {
			Member3 foundMember = em.find(Member3.class, seq);
			log.info("\t+ foundMember: {}", foundMember);
		});
		 
	}
	
}// end class
