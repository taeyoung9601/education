package org.zerock.myapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2 // or @Slf4j
@NoArgsConstructor
public class JUnit4Template {

	//전처리 메소드 - Pre-processing Method for Test Method
	@Before
	public
	void setup() {
		log.debug("setup() invoked.");
	}
	
	@Test(timeout = 1000L * 1)
	public void testUnit2() {     // All unit test method signature: public void testXXX(){}
		log.debug("testUnit2() invoked.");
	}
	// 
	@Test // is a Test Method for a feature.
	public
	void testUnit1() {        //Default test mmethod'name. : 'contextLoads'
//		log.debug("contextLoads() invoked.");
		log.debug("testUnit1() invoked.");
	}
	
//	@Test
//	public void testUnit2() {     // All unit test method signature: public void testXXX(){}
//		log.debug("testUnit2() invoked.");
//	}
//	
	@Test
	public void testaUnit3() {
		log.debug("atestUnit3() invoked.");
	}
	
	// (사)후처리 메소드 - Post-processing Method after testing.
	@After
	public
	void tearDown() {
		log.debug("tearDown() invoked.");
	}
	
}// end class
