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

//@WebFilter(urlPatterns = {"/xxx","/yyy"}, filterName = "MyFirstFilter")					
public class MyFirstFilter extends HttpFilter implements Filter {
       
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
		
		/*
		// (1)***Pre-processing: 전처리 코드는 여기에 넣으세요
		log.info("-".repeat(80));
		log.info("Here is the area for PRE-processing...");
		log.info("-".repeat(80));
		
		// 모든 요청 처리시, 다국어 문자가 포함된 요청 파라미터를 제대로 얻으려면 req.setCharacterEncoding(charset)
		// 메소드를 무조건 호출해야죠.
		// 더불어서, 응답 처리시에도, 동일한 이유로, res.setCharacterEncoding(charset) 해야죠
		req.setCharacterEncoding("utf8");
		res.setCharacterEncoding("utf8");
		
		res.setContentType("text/html; charset=utf8");
		
		@Cleanup PrintWriter out =res.getWriter();
		
		out.println("<h3>이 응답은 필터에서 보냄</h3>");
		out.flush();
		
		return;
		*/
		// pass the request along the filter chain
		chain.doFilter(req, res);
		// (2)***Post-processing: 후처리 코드는 여기에 넣으세요
//		log.info("-".repeat(80));
//		log.info("Here is the area for POST-processing...");
//		log.info("-".repeat(80));
//		
//		// HTTP response 의 헤더영역에 지정된 헤더가 포함되어 있는지 확인
//		String contentType = res.getContentType();		// Content-Type: text/html; charset=utf8
//		if(contentType == null || "".equals(contentType) || contentType != null) {
//			res.setContentType("application/json; charset=utf8");		// 누락된 필수 헤더 추가
//			log.info("\t+ content-type 헤더값을 JSON으로 변경하였습니다.");
//		}// if
		
	}


}
