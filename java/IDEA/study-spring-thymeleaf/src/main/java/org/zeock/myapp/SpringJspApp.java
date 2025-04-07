package org.zeock.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@ServletComponentScan
@SpringBootApplication
public class SpringJspApp {

	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(SpringJspApp.class);
		app.addListeners(e -> log.info("=>{}", e.getClass().getSimpleName()));
		
		app.run(args);
	}

}
