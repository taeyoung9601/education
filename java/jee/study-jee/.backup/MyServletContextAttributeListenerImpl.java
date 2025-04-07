package org.zerock.myapp.listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

//@WebListener
public class MyServletContextAttributeListenerImpl implements ServletContextAttributeListener {

	// App scope 공유 영역에 새로운 공유 속성이 리바인딩 될 때,
	// 자동 콜백되는 메소드
	@Override
    public void attributeReplaced(ServletContextAttributeEvent event)  { 
         log.debug("attributeReplaced({}) invoked.", event);
         log.info("\t1. e.getName:{}", event.getName());
         log.info("\t2. e.getSource:{}", event.getSource());
         log.info("\t3. e.getValue:{}", event.getValue());
    }// attributeReplaced

	
	// App scope 공유 영역에 새로운 공유 속성이 언바인딩 될 때,
	// 자동 콜백되는 메소드
	@Override
    public void attributeRemoved(ServletContextAttributeEvent event)  { 
    	log.debug("attributeRemoved({}) invoked.", event);
    	log.info("\t1. e.getName:{}", event.getName());
        log.info("\t2. e.getSource:{}", event.getSource());
        log.info("\t3. e.getValue:{}", event.getValue());
    }// attributeRemoved

	
	// App scope 공유 영역에 새로운 공유 속성이 바인딩 될 때,
	// 자동 콜백되는 메소드
	@Override
    public void attributeAdded(ServletContextAttributeEvent event)  { 
    	log.debug("attributeAdded({}) invoked.", event);
    	log.info("\t1. e.getName:{}", event.getName());
        log.info("\t2. e.getSource:{}", event.getSource());
        log.info("\t3. e.getValue:{}", event.getValue());
    }// attributeAdded
	
}// end class
