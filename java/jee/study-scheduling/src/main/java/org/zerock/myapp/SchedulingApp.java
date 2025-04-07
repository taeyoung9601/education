package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@EnableScheduling

@ServletComponentScan

//@SpringBootConfiguration +  @EnableAutoConfiguration + @ComponentScan
@SpringBootApplication
public class SchedulingApp {

	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		SpringApplication app = new SpringApplication(SchedulingApp.class);
		app.addListeners(e-> log.info("=>{}", e.getClass().getSimpleName()));
		app.run(args);
	}// main

}// end class
