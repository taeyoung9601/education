package org.zerock.myapp.suite;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@Log4j2
@NoArgsConstructor

public class Case3Tests {

//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("CASE3-TEST")
	@Timeout(1L)
	void contextLoads() {
		log.debug("contexLoads() invoked.");
	}
}