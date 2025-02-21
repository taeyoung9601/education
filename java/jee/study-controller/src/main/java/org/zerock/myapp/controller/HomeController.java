package org.zerock.myapp.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j

@RestController
public class HomeController {

	@RequestMapping("/")
	String home(Locale locale) {				// Handler(Method)
		log.debug("home({}) invoked.", locale);
		
		DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.LONG,0,locale);
		String formattedDate = formatter.format(new Date());
		
		return formattedDate;
	}
	
}// end class
