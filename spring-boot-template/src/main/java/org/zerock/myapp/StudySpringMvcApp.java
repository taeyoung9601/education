package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j

// Do Scan All @WebServlet, @WebFilter, @WebListener, Etc Web Components Started From Root Package.
// Which have the following Execution Class.
@ServletComponentScan	// ** Required.

@SpringBootApplication
public class StudySpringMvcApp extends ServletInitializer{

	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		// 1st. method
//		SpringApplication.run(StudySpringMvcApp.class, args);
		
		// 2nd. method
		SpringApplication app = new SpringApplication(StudySpringMvcApp.class);
		
		// 설정파일에 설정을 우선시한다!
		// 내장형 WAS를 구동시킨다!
//		app.setWebApplicationType(WebApplicationType.SERVLET);
		// 내장형 WAS를 구동시키지 않음 -> 일반 자바 어플리케이션과 동일
//		app.setWebApplicationType(WebApplicationType.NONE);
		
		// --3--
		app.run(args);		// String[] args required
//		app.run();			// if main's String[] args not necessary
		
	}// main

}// end class
