package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

// Do Scan All @WebServlet, @WebFilter, @WebListener, Etc Web Components 
// Started From (Root) Package Which have the following Execution Class.
@ServletComponentScan	// **Required.

@SpringBootApplication
public class StudySpringMvcApp extends ServletInitializer {

	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		// -- 1st. method --------------
//		SpringApplication.run(StudySpringMvcApp.class, args);

		// -- 2nd. method --------------
		SpringApplication app = new SpringApplication(StudySpringMvcApp.class);
		
		// The configuration is prior to the following code.
//		app.setWebApplicationType(WebApplicationType.SERVLET);
//		app.setWebApplicationType(WebApplicationType.NONE);
		
		// -- 3 -------------------
		// Start Spring Boot Application
		app.run(args);			// String[] args required.
//		app.run();					// If main's String[] args not necessary		
	} // main

} // end class


