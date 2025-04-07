package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@WebServlet(name= "PostPreServlet", urlPatterns = "/PostPre")
public class PostPreServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

   /**
     * This annotation is used on a method that needs to be executed 
     * after dependency injection is done to perform any initialization.
     * 
     *       (1) The method on which this annotation is applied may be public, protected, package private or private.
     *       (2) The method must not be static except for the application client.
     *       (3) The method should not be final.
     * 
     */
    @PostConstruct
    void postConstruct() {   // Like init callback
       log.debug("postConstruct() invoked.");
      
    }

    /**
     * This annotation is used on a method as a callback notification to signal 
     * that the instance is in the process of being removed by the container.
     */
    @PreDestroy
    void preDestroy() {      // Like destroy callback
       log.debug("preDestroy() invoked.");
      
    }
	
	@Override
	public void init(ServletConfig config) throws ServletException {	// INIT
		log.info("init({}) invoked.", config);
		super.init(config);			// 주의 : 이 코드는 삭제하지말라!
		
		// Todo
		try {			// READY 상태에서 필요한 자원들을 획득
			
		}catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	
	
	@Override
	public void destroy() {						// DESTROY
		log.info("destroy() invoked.");
//		super.destroy();	// 이 코드는 생략 가능!
		
		try {			// INIT 상태에서 획득한 자원객체들을 반납하는 작업들 수행
			
		}catch(Exception _ignored){}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			
			log.info("\t + PostPreServlet");
			log.info("\t + Request URI:{}", req.getRequestURI());
			
			//---
			// 응답처리
			//---
			
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8"); 	// if HTML
//			res.setContentType("application/json; charset=UTF-8");		// if JSON
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<h3>PostPreServlet</h3><hr>");
			
			out.flush();
		}catch(Throwable t) {
			throw new IOException(t);
		}
	}


}
