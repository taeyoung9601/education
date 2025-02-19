package org.zerock.myapp.runner;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

//*** All runner class must implement ApplicationRunner or CommandLineRunner interfaces.
@Component
public class CustomCLIRunner implements CommandLineRunner{
	// Runner: Only once executed object which have a named, 'run' method.
	
	@Override
	public void run(String... args) throws Exception {
		
		log.debug("run({}) invoked.", Arrays.toString(args));
		
	}// run

	
		
}// end class
