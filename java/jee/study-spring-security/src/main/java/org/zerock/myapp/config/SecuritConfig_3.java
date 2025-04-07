package org.zerock.myapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@Configuration
public class SecuritConfig_3 {
	
	@Autowired private DataSource dataSource;
	
	@PostConstruct
	void postConstruct() {
		log.debug("postConstruct() invoked.");
		log.info("\t + this.dataSource:{}", this.dataSource);
	}// postConstruct
	
	
	// ====================================
	// STEP2 : Create In-Memory Spring Security Users With `authenticate(auth)` callback.
	// ====================================
	// Note: This 'authenticate(auth)' callback cannot be used with 'userDetailsService' 
	
	@Autowired
	void authenticate(AuthenticationManagerBuilder auth) throws Exception{
		log.debug("authenticate({}) invoked.", auth);
		
		// 2nd. method: Create In-memory Spring Security users from database User table;
		
		// 2-1. 인증정보: (1) 계정명(username) (2)암호(password) (3) 활성여부(enabled)
		final String query1 ="SELECT id AS username , concat('{noop}',password) AS password, enabled FROM member WHERE id=?";
		
		// 2-2. 인가정보: (1) 계정ID(id) (2) 권한명(role)
		final String query2 ="SELECT id, role FROM member WHERE id=?";
		
		auth.jdbcAuthentication()
			.dataSource(this.dataSource)
			.usersByUsernameQuery(query1)
			.authoritiesByUsernameQuery(query2);

	} // authenticate
	
	
}// end class
