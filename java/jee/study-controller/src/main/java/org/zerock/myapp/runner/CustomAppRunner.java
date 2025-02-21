package org.zerock.myapp.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
// This stereotype annotations register the class having this as a bean in the Spring Context
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j

// *** All runner class must implement ApplicationRunner or CommandLineRunner interfaces.
@Component
public class CustomAppRunner implements ApplicationRunner{
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.debug("run({}) invoked.", args);
	}// run

}// end class
