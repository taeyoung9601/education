package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@ServletComponentScan

@SpringBootApplication
public class StudySpringSecurityApp {

	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
//		SpringApplication.run(StudySpringSecurityApp.class, args);
//		---
		SpringApplication app = new SpringApplication(StudySpringSecurityApp.class);
		app.addListeners(e -> log.info("=>{}", e.getClass().getSimpleName()));
		
		app.run(args);
		
	}// main

}// end class
