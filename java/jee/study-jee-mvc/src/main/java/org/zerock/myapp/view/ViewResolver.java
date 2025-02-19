package org.zerock.myapp.view;

import java.io.IOException;
import java.util.Map;

import org.zerock.myapp.service.CommandService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 모델과 뷰 이름을 받아서, 실제 지정된 이름의 뷰를 호출할 책임과 기능을 수행할
// 객체입니다.

@Slf4j
@NoArgsConstructor

public class ViewResolver {
	// 아래에 선언된 2개의 상수로, 전달받은 뷰이름과 합쳐서, 실제 호출할
	// 최종적인 뷰의 경로를 완성하는데에 사용됩니다.
	// 각각의 템플릿 엔진마다, 아래와 같은 접두사와 접미사를 설정으로 관리하도록
	// 되어 있습니다.
	private final String viewPrefix = "/WEB-INF/views/";	// View 파일의 접두사 관리
	private final String viewSuffix = ".jsp";				// View 파일의 확장자를 접미사로 관리
	
	
	public void render(HttpServletRequest req, HttpServletResponse res, Map<String,Object>modelAndView) throws IOException {
		log.debug("render({},{},{}) invoked.", 
				req.getClass().getSimpleName(), res.getClass().getSimpleName(), modelAndView);
		
		try {
			//--1----
			final Object model = modelAndView.get(CommandService.MODEL_KEY);
			log.info("\t + model: {}", model);
			
			// 위에서 얻어낸 모델 객체를 호출할 뷰에 전달하려면, 공유 데이터 영역 중에
			// 현재의 요청에 대한 응답이 나가기 전까지만 유효한, Request Scope에 넣어서
			// 호출할 뷰에 전달합니다.
			req.setAttribute(CommandService.MODEL_KEY, model);
			
			//--2---
			// 전달받은 modelAndView 에서 호출할 뷰 이름을 얻어냅니다.
			final String viewName = (String)modelAndView.get(CommandService.VIEW_KEY);
			log.info("\t+ viewName:{}", viewName);
			
			//--3---
			// 이전 단계에서 얻어낸 뷰 이름과 이 viewResolver의 설정인, 접두사/접미사를 합쳐서
			// 실제 호출할 뷰 파일의 완전한 경로를 만들어냅니다. 왜? 호출할려고..
			// 호출하려면 호출할 파일의 경로를 알아야 하기 때문에...
			//						/WEB-INF/views/  뷰이름  + .jsp     => /WEB-INF/views/뷰이름.jsp
			final String viewPath = viewPrefix + viewName +viewSuffix;
			log.info("\t + viewPath:{}", viewPath);
			
			//--4---
			// Request Forwarding 을 이용해서, 이전 단계에서 결정된 뷰의 최종경로를 target으로
			// 포워딩(요청 떠 넘기기, 계주경기 선수처럼, 바통터치) 수행
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
			dispatcher.forward(req, res);
			
		}catch(Exception e) {
			throw new IOException(e);
		}
		
	}// render
	
	
}// end class
