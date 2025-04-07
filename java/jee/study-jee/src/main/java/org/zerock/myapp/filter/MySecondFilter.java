package org.zerock.myapp.filter;

import java.io.IOException;
import java.io.Serial;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// public 접근제한자를 붙힌, 기본 생성자를 생성하라!
@NoArgsConstructor(access = AccessLevel.PUBLIC)

//@WebFilter(urlPatterns = {"/xxx"}, filterName = "MySecondFilter")					
public class MySecondFilter extends HttpFilter implements Filter {
       
	@Serial private static final long serialVersionUID = 1L;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		log.debug("init({}) invoked.", config);
	}
	
	
	@Override
	public void destroy() {
		log.debug("destroy invoked.");
		
	}// destroy

	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		log.debug("doFilter({},{},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName(), chain);
		
		// (1)***Pre-processing: 전처리 코드는 여기에 넣으세요
		log.info("-".repeat(80));
		log.info("Here is the area for PRE-processing...");
		log.info("-".repeat(80));
		
		// pass the request along the filter chain
		chain.doFilter(req, res);
		
		// (2)***Post-processing: 후처리 코드는 여기에 넣으세요
		log.info("-".repeat(80));
		log.info("Here is the area for POST-processing...");
		log.info("-".repeat(80));
	}


}
