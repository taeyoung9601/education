package org.zerock.myapp.config;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

//@Configuration
public class SecurityConfig_1 {      // POJO   => Called, "Java Confugration Class for Spring(Boot)"
   

	// ====================================
	// STEP1 : Create In-Memory Spring Security Users With `UserDetailsService` Bean.
	// ====================================


	// 가. 이 어노테이션은 "자바설정클래스" 내에서만 사용가능
	// 나. 목적은, 이 어노테이션이 붙은 메소드는, Spring Boot App 구동시, 자동으로 callback되고
	//       이때, 이 메소드가 반환한 자바객체를 Spring Context에 빈으로 등록시키라!!! 
	//  다. 아래 메소드의 이름이, 곧 반환환 자바객체를 빈으로 등록할 때, 빈의 "이름"이 됩니다.
	@Bean
	String myName() {   // @Since v2.7.x and v3.4.x
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> myName() invoked.");
  
		return "Yoseph";
	} // myName
   
	@Bean(name = "OurNames")
	Set<String> myNames() {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> myNames() invoked.");
  
		return Set.of("NAME_1", "NAME_2", "NAME_3");
	}

	// 스프링시큐리티 의존성이 추가되면, 기본으로 생성되는 단일한 계정인 'user'
	// 말고, 우리만의 인증가능한 계정을 만들어 보자!!!
	// (단, DB의 회원테이블에 계정을 만드는게 아니라, 메모리에 만드는 계정입니다.)
   
   
	/** 
	(1) UserDetailService(i):
		가. 스프링 보안에서 사용가능한 사용자를 생성하는 서비스(비지니스 계층)에 대한 규격을 정의한 인터페이스(FunctionalInterface)
		나. 이 인터페이스 타입의 서비스 빈을 스프링 보안은 내부적으로 자동주입(DI)받아서
		다. 사용자 객체를 얻어내는데 사용하여, 보안(=접근통제: 인증/인가)에 사용합니다.
	
	(2) UserDetails (i) : 
		가. 스프링 보안에서 사용가능한 사용자에 대한 규격을 정의하고 있는 인터페이스.
		나. 스프링 보안에서 사용가능한 사용자 객체는 반드시 이 인터페이스 대로 생성되어야 합니다.
	
	(3) User (C) :
		가. 위 UserDetails 인터페이스 규격대로 생성된 사용자 객체 생성 클래스.
		나. 이 객체를 생성하기 수월하도록 아래 코드와 같이, 메소드 체이닝이 가능한 메소드들을 제공합니다.
	 */
   
	@Bean
	UserDetailsService userDetailsService() {				// 스프링 보안에 사용 가능한 계정들을 생성
		log.debug("userDetailsService() invoked.");
  
		// -- 1---------------
		UserDetails member = // In-memory Account
				User.withUsername("member")         // Account ID
				// 여기서, "{noop}"이란 문자열은, 암호화를 적용하지 말라!는 의미
				// (1) Set a password with ${noop} or {bcypt} id
				//            		.password("{noop}member")      // Account Password

				.password("member")		//	(2) {bcypt}encodedPassword
				.passwordEncoder( pass -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(pass))

				.accountExpired(false)               // Account isExpired ?
				.accountLocked(false)               // Account isLocked ?
				.disabled(false)                        // Account isDisabled ?
				.credentialsExpired(false)            // Account Credential isExpired ?                  
				// 계정에 권한할당 방식은 2가지 : ROLE, AUTHORITY => 하지만 같은거임
				// 지정방식만 틀릴 뿐, 할당된 권한은 동일한 겁니다. (***)
				// 의미는 같지만, "권한명(ROLE_권한명)"을 주실때,
				//    (1) ROLE : "ROLE_" 란 접두사를 제거하고 지정.            (***)
				//    (2) AUTHORITY : "ROLE_"란 접두사를 완전하게 지정.
				.roles("MEMBER")                  // Account Roles (== Authorities)
				//        			.authorities("ROLE_MEMBER")      // Account Authorities (== Roles)
				.build();                                 // Create a New Account.
  
		UserDetails manager = // In-memory Account
				User.withUsername("manager")         // Account ID
				// 여기서, "{noop}"이란 문자열은, 암호화를 적용하지 말라!는 의미
				// (1) Set a password with ${noop} or {bcypt} id
				.password("{noop}manager")      // Account Password

				.password("manager")		//	(2) {bcypt}encodedPassword
				.passwordEncoder( pass -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(pass))

				.accountExpired(false)               // Account isExpired ?
				.accountLocked(false)               // Account isLocked ?
				.disabled(false)                        // Account isDisabled ?
				.credentialsExpired(false)            // Account Credential isExpired ?                  
				// 	계정에 권한할당 방식은 2가지 : ROLE, AUTHORITY => 하지만 같은거임
				// 지정방식만 틀릴 뿐, 할당된 권한은 동일한 겁니다. (***)
				// 의미는 같지만, "권한명(ROLE_권한명)"을 주실때,
				//    (1) ROLE : "ROLE_" 란 접두사를 제거하고 지정.            (***)
				//    (2) AUTHORITY : "ROLE_"란 접두사를 완전하게 지정.
				//                    .roles("MEMBER")                  // Account Roles (== Authorities)
				.authorities("ROLE_MANAGER")      // Account Authorities (== Roles)
                .build();   
  
		UserDetails admin = // In-memory Account
				User.withUsername("admin")         // Account ID
				// 여기서, "{noop}"이란 문자열은, 암호화를 적용하지 말라!는 의미
				// (1) Set a password with ${noop} or {bcypt} id
				//              		.password("{noop}admin")      // (1) Account Password {noop}password

				.password("admin") 			//	(2) {bcypt}encodedPassword
				.passwordEncoder( pass -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(pass))
				
				.accountExpired(false)               // Account isExpired ?
				.accountLocked(false)               // Account isLocked ?
				.disabled(false)                        // Account isDisabled ?
				.credentialsExpired(false)            // Account Credential isExpired ?                  
				// 계정에 권한할당 방식은 2가지 : ROLE, AUTHORITY => 하지만 같은거임
				// 지정방식만 틀릴 뿐, 할당된 권한은 동일한 겁니다. (***)
				// 의미는 같지만, "권한명(ROLE_권한명)"을 주실때,
				//    (1) ROLE : "ROLE_" 란 접두사를 제거하고 지정.            (***)
				//    (2) AUTHORITY : "ROLE_"란 접두사를 완전하게 지정.
				//          			.authorities("ROLE_MEMBER")      // Account Authorities (== Roles)
				.roles("MEMBER","ADMIN")		// Account Roles (== Authorities)
                .build();     

		// -- 1---------------
		log.info("1. member:{}", member);
		log.info("2. manager:{}", manager);
		log.info("3. admin:{}", admin);
		
		// -- 5-----
		return new InMemoryUserDetailsManager(member, manager, admin);
		
	}// userDetailsService

   
} // end class