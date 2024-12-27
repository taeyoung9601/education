package org.zerock.myapp.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

// (주의) : JUnit 4 Test Framework 는 각각의 단위 테스트 메소드를 수행시킬 때마다,
//			새롭게 객체를 생성해서 테스트를 수행합니다. 즉 , PER_METHOD 방식

public class CalculatorTests  {		//POJO
	
	private Calculator calc; 			//Autocloseable Resource Object

	/*
	 * public CalculatorTests() { 
	 * log.debug("Default constructor({}) invoked.",
	 * this); }
	 */
	
	@Before
	public void setup() {
		
		log.debug("setup() invoked.");
		
		//위 테스트 클래스의 주의사항대로,
		// 매번 테스트 메소드가 수행될 때마다, 새로운 테스트 객체를 생성해서
		// 단위 테스트 메소드를 수행하기 때문에, 사실상 항상 필드값은 null이 됩니다.
		// 때문에, 매번 new 하게 되어 있으니, if문은 전혀 필요가 없습니다.
		
//		if(this.calc == null) {  				//Not necessary
	         this.calc = new Calculator();      // 한번만 객체를 생성하도록 만듬
//	      }
		
     log.info("\t+this.calc:{}",this.calc);
	}
	
	
	@Test (timeout = 0L)
	public void testAdd() {
		log.debug("testAdd() invoked.");
		
		int actual = this.calc.add(100,200);
		
	}  //Test-1
	
	
	@Test (timeout = 0L)
	public void testSub() {
		log.debug("testSub() invoked.");
		
		int actual = this.calc.sub(100,200);
		
		assertEquals(-100, actual);
	}  //Test-2
	
	
	@Test (timeout = 0L)
	public void testMul() {
		log.debug("testMul() invoked.");
		
		Integer actual = this.calc.mul(100,200);  //Auto-boxing
		
		// check whether the variable is null or not 
		assertNotNull(actual);           // 1st
		Objects.requireNonNull(actual);  // 2nd
		assert actual != null;			// 3rd
		
		
//		assertEquals(20000,actual.intValue());			// actual을 기본 타입으로
		assertEquals(Integer.valueOf(20000),actual); 
		//  20000을 wrapper타입으로 객체끼리 비교가되는데 각각 생성되는 주소가 다르니 같다고 나오면같은 값이다.
		
		log.info("\t+actual:{}",actual);
//		assert
	}  //Test-3
	
	
	@Test (timeout = 0L)
	public void testDiv() {
		log.debug("testDiv() invoked.");
		
		double actual = this.calc.div(100,200);
		assertEquals(.5,actual, 0.0);
		log.info("\t+actual:{}", actual);
	} //Test-4
	
	
	@After
	public void tearDown() throws Exception {
		log.debug("tearDown() invoked.");
		
		// surely close of used resource object with close method
		if (this.calc != null){
			this.calc.close();
		}
	}
	
}
