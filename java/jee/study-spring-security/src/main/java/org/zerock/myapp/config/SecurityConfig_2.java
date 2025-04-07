package org.zerock.myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

//@Configuration
public class SecurityConfig_2 {      
	
	// ====================================
	// STEP2 : Create In-Memory Spring Security Users With `authenticate(auth)` callback.
	// ====================================
	
	@Autowired			// OK : Callback will occur.
	void authenticate(
//		@Autowired 	// XX : Callback will NOT occur.
		AuthenticationManagerBuilder auth) throws Exception{	//@Since v2.7.x - v3.4.x
		
		auth.inMemoryAuthentication()
			.withUser("member")
			
			// 1st method
			.password("{noop}member")
			// 2nd method
//			.password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("member"))
			
			.accountExpired(false)
			.accountLocked(false)
			.disabled(false)
			.credentialsExpired(false)
			
			.roles("MEMBER")
//			.authorities("ROLE_MEMBER")
			
			.and()
			
			//--2-----------------------
			.withUser("manager")
			// 1st method
			.password("{noop}manager")
			// 2nd method
//			.password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("manager"))
			
			.accountExpired(false)
			.accountLocked(false)
			.disabled(false)
			.credentialsExpired(false)
			
//			.roles("MEMBER","MANAGER")
			.authorities("ROLE_MEMBER","ROLE_MANAGER")
			
			.and()
			
			//--3-----------------------
			.withUser("admin")
			// 1st method
//			.password("{noop}admin")
			// 2nd method
			.password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("admin"))
			
			.accountExpired(false)
			.accountLocked(false)
			.disabled(false)
			.credentialsExpired(false)
			
//			.roles("MEMBER","ADMIN")
			.authorities("ROLE_MEMBER","ADMIN");
			
			
	}// authenticate
	
} // end class