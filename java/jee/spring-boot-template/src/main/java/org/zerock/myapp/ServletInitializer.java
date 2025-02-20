package org.zerock.myapp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

// The packaging .war file could be run as a standalone app, also web application
// Due to this class.
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
		log.debug("configure({}) invoked.", app);
		return app.sources(StudySpringMvcApp.class);
	} // configure

} // end class
