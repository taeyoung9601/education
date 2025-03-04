package org.zerock.myapp.persistence;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.Person;

@Repository
public interface PersonRepository 
//	extends CrudRepository<T, ID> 			// 여기서, T 타입: 엔티티 클래스의 타입명, ID: 엔티티 클래스의 PK필드
	extends CrudRepository<Person, Long>	// 여기서, T 타입: Person, ID: Long
{
	
	   /**
	    * ==================================
	    * Query Methods : Spring Data Repository 가 인식가능한 일정한 규칙에 따라 만드는 검색 메소드의 총칭
	    * Rule: findBy(prefix) + 엔티티속성(=필드)명 + <연산자>
	    * ==================================
	    * Spring Data Repository Interface 에는, 여러분만의 추상메소드를, Spring Data가 요구하는 대로만
	    * 이름을 잘 지어주시면, 이 메소드까지 자동구현해줍니다.
	    */
	
	public abstract List<Person> findByName(String name);
	
	
	// 이 메소드는 위의 Query Method가 아닙니다. 실제 수행시킬 Query를 어노테이션으로
	// 가지고 있으니, 굳이 Query Method의 이름 짓는 규칙에 제약을 받을 필요가 없겠죠
	
	// 어노테이션에 있는 SQL문장이 DML이라면, 아래와 같이 @Modifying 을 붙여줘야합니다.
	@Modifying	// Indicates a method should be regarded as modifying query
	@Query("UPDATE person SET name=:name WHERE id=:id")
//	public abstract Boolean updateByIdAndNames(Long id,String name);
	public abstract Boolean updateByIdAndNames(@Param("id") Long id2,@Param("name")String name2);
	
	
	
}// end interface
