package org.zerock.myapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Arrays;

@Slf4j

@SpringBootApplication
public class StudyServiceApp extends ServletInitializer implements ApplicationListener<ApplicationEvent> {

	public static void main(String[] args) {
		SpringApplication.run(StudyServiceApp.class, args);
		log.debug("main({}) invoked.", Arrays.toString(args));
	}// main

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		log.debug("<<<{}>>>",event.getClass().getSimpleName());
	}
}// end class
