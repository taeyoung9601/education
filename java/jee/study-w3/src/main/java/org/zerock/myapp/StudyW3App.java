package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@ServletComponentScan
@SpringBootApplication
public class StudyW3App{

	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
//		SpringApplication.run(StudyW3App.class, args);
		
		SpringApplication app = new SpringApplication(StudyW3App.class);
		app.setWebApplicationType(WebApplicationType.SERVLET);
		app.setBannerMode(Mode.CONSOLE);
		
//		app.addListeners(e -> log.info("<<<{}>>>", e.toString())); -> 클래스 이름이 길어 아래 코드로 대체
		app.addListeners(e -> log.info("<<<{}>>>", e.getClass().getSimpleName()));
		
		app.run(args);
		
		
	}// main


}// end class
