package org.zerock.myapp.runner;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

//*Important: All runner class must *definitely implement 
//`ApplicationRunner` or `CommandLineRunner` interfaces.

@Component
public class CustomCLIRunner implements CommandLineRunner {
	
	@Override
	public void run(String... args) throws Exception {
		log.debug("run({}) invoked.", Arrays.toString(args));
		
	} // run

} // end class
