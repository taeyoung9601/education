package org.zerock.myapp.listener;

import java.util.EnumSet;
import java.util.Objects;

import org.zerock.myapp.filter.MyFirstFilter;
import org.zerock.myapp.filter.MySecondFilter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@WebListener
public class FilterConfigListener implements ServletContextListener {

	
	@Override
    public void contextInitialized(ServletContextEvent event)  { 
         log.debug("contextInitialized({}) invoked.", event);
         
         // 필터체인에 포함된 필터들의 실행순서 결정
         ServletContext ctx = event.getServletContext();
         Objects.requireNonNull(ctx);
         
         FilterRegistration.Dynamic filter1 = ctx.addFilter("MySecondFilter", MySecondFilter.class);
         FilterRegistration.Dynamic filter2 = ctx.addFilter("MyFirstFilter", MyFirstFilter.class);
         
         filter1.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/xxx");
         filter2.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/xxx");
         
    }// contextInitialized

}// end class
