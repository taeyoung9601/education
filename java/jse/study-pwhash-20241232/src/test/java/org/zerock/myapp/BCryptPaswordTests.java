package org.zerock.myapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@Log4j2
@NoArgsConstructor
public class BCryptPaswordTests {
	private PasswordEncoder encoder;
	
	
	@BeforeAll
	void beforeAll() {		// Once, Pre-processing
		log.debug("beforeAll() invoked.");
		
		// The core class in the Spring Security is named, "BCryptPasswordEncoder" class.
		BCryptPasswordEncoder encoder1 = new BCryptPasswordEncoder();		// 1st. method: *Not Recommended
		PasswordEncoder encoder2 = 
				PasswordEncoderFactories.createDelegatingPasswordEncoder();	// 2nd. method: *Recommended
		
		log.info("\t1. encoder1: {}", encoder1);
		log.info("\t2. encoder2: {}", encoder2);
		
//		this.encoder = encoder1;								// 1st. case
		this.encoder = encoder2;								// 2nd. case
		
		log.info("\t3. this.encoder: {}", this.encoder);
	}
	
	
//	@Disabled
	@Order(1)
	@Tag("BCrypt")
//	@Test
	@RepeatedTest(3)
	@DisplayName("1. testPasswordEncodingWithBCrypt")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testPasswordEncodingWithBCrypt() {
		log.debug("testPasswordEncodingWithBCrypt() invoked.");
		
		final String password = "Yoseph1234^^&";		// Plain Text
		
		final String cipherText = this.encoder.encode(password);	// Plain Text -> Cipher Text with SHA-256 Hash
		
		Objects.requireNonNull(cipherText);
		log.info("password: {}, cipher: {}, length: {}", password, cipherText, cipherText.length());
	}
	
	// Hash with Salt
//	@Disabled
	@Order(2)
	@Tag("BCrypt")
//	@Test
	@RepeatedTest(3)
	@DisplayName("2. testPasswordEncodingUsingBCryptWithSalt")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	void testPasswordEncodingUsingBCryptWithSalt() {
		log.debug("testPasswordEncodingUsingBCryptWithSalt() invoked.");
		
		final String password = "Pyramide123***7&&";
		final String salt = "__SALT__";		// The salt is an additional token with a password.
		
		final String cipherText = this.encoder.encode(password + salt);
		
		assertNotNull(cipherText);
		log.info("\t+ password: {}, cipher: {}, length: {}", password, cipherText, cipherText.length());
	}
	
	// Validate a password.
//	@Disabled
	@Order(3)
	@Tag("BCrypt")
	@Test
//	@RepeatedTest(3)
	@DisplayName("3. testPasswordMatches")
	@Timeout(value=1L * 2, unit=TimeUnit.SECONDS)
	void testPasswordMatches() {
		log.debug("testPasswordMatches() invoked.");
		
		final String password = "Ab$%s##&io112";
		
		// Case1
//		for(int counter = 0;  counter < 10; counter++) {
//			String cipherText = this.encoder.encode(password);
//			
//			boolean isMatched = this.encoder.matches(password, cipherText);
//			
//			assertEquals(true, isMatched);
//			log.info("[{}].isMatched: {}", counter, isMatched);
//		}
		
		// Case2
		String hash1 = this.encoder.encode(password);
		String hash2 = this.encoder.encode(password);
		
		assert hash1 != hash2;
		
		log.info("\t1. hash1: {}", hash1);
		log.info("\t2. hash2: {}", hash2);
		
		// -------------------
		boolean isMatchedWithHash1 = this.encoder.matches(password, hash1);
		boolean isMatchedWithHash2 = this.encoder.matches(password, hash2);
		
		assertEquals(true, isMatchedWithHash1);
		assertEquals(true, isMatchedWithHash2);
		
		log.info("\t3. isMatchedWithHash1: {}, isMatchedWithHash2: {}",
				isMatchedWithHash1, isMatchedWithHash2);
	}

} // end class

