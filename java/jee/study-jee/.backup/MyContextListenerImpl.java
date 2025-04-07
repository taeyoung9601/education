package org.zerock.myapp.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@WebListener
public class MyContextListenerImpl implements ServletContextListener {

	@Override
    public void contextInitialized(ServletContextEvent event)  { 
        log.debug("contextInitialized({}) invoked.", event);
    } // contextInitialized

	
	@Override
    public void contextDestroyed(ServletContextEvent event)  { 
		log.debug("contextDestroyed({}) invoked.", event);
    } // contextDestroyed
	
}	// end class
