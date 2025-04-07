package org.zerock.myapp.servlet;import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
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
          
@WebServlet(name = "SetAttributeToAppScopeServlet", urlPatterns = "/SetAttributeToAppScope")
public class SetAttributeToAppScopeServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	// 
	private String param1;
	private String param2;
	private String param3;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.debug("init({}) invoked.", config.getClass().getSimpleName());
		super.init(config);
		
		//--1---
		// web.xml 파일에 등록된 3개의 Context Parameter의 값을 획득
		// 이 핵심객체인 ServletContext 객체는, 곧 우리가 만들고 있는
		// ROOT 웹 어플리케이션에 대한 모든 정보를 접근할 수 있는 객체입니다.
		// 그래서, Context란 용어 = 웹 어플리케이션 입니다.
		ServletContext ctx = config.getServletContext();
		
		final String param1 = ctx.getInitParameter("PARAM1");
		final String param2 = ctx.getInitParameter("PARAM2");
		final String param3 = ctx.getInitParameter("PARAM3");
		
		//다른 메소드에서도 컨텍스트 파라미터를 사용할 수 있도록
		// 필드에 저장합니다
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
		
		log.info("\t1 + param1: {}", this.param1);
		log.info("\t1 + param2: {}", this.param2);
		log.info("\t1 + param3: {}", this.param3);
		
		//--2----
		// 각각의 웹 어플리케이션마다, "공유데이터영역(Shared Data Area)" 이 만들어지는데
		// (런타임 중에), 이 영역이 아래와 같이, 3개 영역이 있습니다:
		// (1) 어플리케이션 스코프 (Application Scope, 줄여서 App Scope이라고 부릅니다)
		// (2) 세션 스코프(Session Scope)
		// (3) 요청 스코프(Request Scope)
		// (4) 페이지 스코프(Page Scope) : Servlet Container 에는 업속, JSP Container에서 만들어집니다.
		//                             즉, 우리가 JSP를 사용하기 전까지는 없는 공유 영역입니다.
		
		// 위의 (1) app scope에 3개의 Context Parameter 를 공유 데이터로 저장합니다.
		// 공유할 데이터에 부여한 이름을, "공유속성(Shared Attribute)" 라고 합니다.
		// 그래서, 아래의 3개 메소드에 모두 "Attribut"(공유 속성)란 단어가 들어 있습니다.
//		ctx.setAttribute(name,value);
//		ctx.getAttribute(name);
//		ctx.removeAttribute(name);
		
		// App Scope에, setAttribute(key,value) 메소드로 공유데이터 저장
		ctx.setAttribute("PARAM1",param1);
		ctx.setAttribute("PARAM2",param2);
		ctx.setAttribute("PARAM3",param3);
		
		
		
		
	}// init
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{})", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			//---
			// 요청처리
			// 2st. method: With inherited method
			ServletContext ctx = this.getServletContext();
			
			ctx.setAttribute("_PARAM1_", this.param1);
			ctx.setAttribute("_PARAM2_", this.param2);
			ctx.setAttribute("_PARAM3_", this.param3);
			
			log.info("\t2 + _PARAM1_: {}", this.param1);
			log.info("\t2 + _PARAM2_: {}", this.param2);
			log.info("\t2 + _PARAM3_: {}", this.param3);
			
			//---
			// 응답처리
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			@Cleanup PrintWriter out = res.getWriter();
			
			out.println("<h3>app scope에 데이터 저장</h3>");
			
			out.flush();
			
		}catch(Throwable t) {
			throw new IOException(t);
		}
		
	}// service

}	// end class
