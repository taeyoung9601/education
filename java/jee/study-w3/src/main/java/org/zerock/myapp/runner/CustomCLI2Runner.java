package org.zerock.myapp.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.zerock.myapp.persistence.PersonRepository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@Component
public class CustomCLI2Runner implements CommandLineRunner {
	@Autowired private PersonRepository repository;
//	@Autowired private PersonSeeder seeder;
	
	
	@Override
	public void run(String... args) throws Exception {
		log.debug("run({}) invoked.", Arrays.toString(args));
		
		log.info("\t1. this.repository:{}", this.repository);
		log.info("\t2. type of this.repository:{}", this.repository.getClass().getName());
		
		// -- Create data into PERSON table With PersonSeeder Component
		//	this.seeder.insertData();
		
	}// run

}// end class
