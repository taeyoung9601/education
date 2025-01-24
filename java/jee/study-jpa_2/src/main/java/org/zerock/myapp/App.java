package org.zerock.myapp;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.zerock.myapp.entity.Member2;
import org.zerock.myapp.util.PersistenceUnits;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

//@Log4j2
@Slf4j
public class App {

   public static void main(String[] args) {
      log.debug("main({}) invoked.",Arrays.toString(args));
      
      @Cleanup EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistenceUnits.H2);
      @Cleanup EntityManager em = emf.createEntityManager();
      
      EntityTransaction tx = em.getTransaction();
      
      try {
         tx.begin();
         // em.persist, em.find, em.remove, em.detach, em.merge, ...
         // Entity State Transition: 
         // (1) New(Transient) -> em.persist -> (2) Managed -> em.find -> 
         // (2) Managed -> em.remove -> (3) Removed -> em.persist -> (2) Managed -> em.detach ->
         // (4) Detached -> em.merge -> (2) Managed
         
         // Test1 for @Pre
//         Member2 transientMember = new Member2();
//         transientMember.setName("Yoseph");
//         transientMember.setAge(23);
//         transientMember.setWeight(58.9);
//         
//         log.info("Before: {}", transientMember);
         
//         em.persist(transientMember);
//         log.info("After: {}", transientMember);
         
         // Test2 for @PostLoad
//         Member2 managedMember = em.find(Member2.class, 3L);
//         log.info("isContains? : {}", em.contains(managedMember) );
         
         
         // Test3 for @PreUpdate, @PostUpdate
//         Member2 foundMember = em.find(Member2.class, 3L);
//         foundMember.setName("YOUNG");
         
         
         // Test4 for @PreRemove, @PostRemove
//         Member2 foundMember = em.find(Member2.class, 3L);
//         em.remove(foundMember);
         
         
         // Test5 for State Callback Parameters.
         em.<Member2>find(Member2.class, 3L);
         
  

         
         
         tx.commit();
      }catch(Exception e) {
         tx.rollback();
         
         throw e;
      } // try-catch
      

   } // main

} // end class
