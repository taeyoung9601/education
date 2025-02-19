package org.zerock.myapp.controller;

import java.io.IOException;
import java.io.Serial;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.service.DeleteService;
import org.zerock.myapp.service.InsertService;
import org.zerock.myapp.service.ListService;
import org.zerock.myapp.service.SelectService;
import org.zerock.myapp.service.UpdateService;
import org.zerock.myapp.view.ViewResolver;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@WebServlet(name = "FrontControllerServlet", urlPatterns = "*.do")
public class FrontControllerServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.info("service({},{}) invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	
		try {
			//--1-------
			final String requestURI = req.getRequestURI();
			
			// Ex : /a/b/c/insert.do -> from i - to t(index.do)
			final String command = requestURI.substring(requestURI.lastIndexOf('/')+1, requestURI.lastIndexOf('.'));
			log.info("\t1 + command: {}", command);
			
			
			//--2---
			// 어떤 command 요청이 들어왔든, 사원관리서비스에 필요한
			// 모든 요청파라미터들을 획득하는 것입니다.
			req.setCharacterEncoding("utf8");
			
			String empno = req.getParameter("empno");
			String ename = req.getParameter("ename");
			String job = req.getParameter("job");
			String mgr = req.getParameter("mgr");
			String hireDate = req.getParameter("hireDate");
			String sal = req.getParameter("sal");
			String comm = req.getParameter("comm");
			String deptno = req.getParameter("deptno");
			
			//-- 3-----
			// 2에서 수집된 모든 요청파라미터를, 비지니스 로직 처리에 적합한 DTO로
			// 저장해서, 후에 서비스 객체의 공통 메소드(규격) 호출할 때 전달하기 위함.
			// 이러한 요청에 의해서 만들어진 패턴이 DTO(데이터 전달 객체) 패턴입니다.
			// 비지니스 로직을 수행할 책임이 있는, 서비스 객체는 이 DTO와 같은 데이터 변환에
			// 신경쓰지말고, 오직 로직에 집중할 수 있도록 도와주는게 또한 DTO의 역할입니다.
			EmpDTO dto = new EmpDTO();
			
			if(empno != null && !"".equals(empno)) dto.setEmpno(Integer.valueOf(empno));
			
			dto.setEname(ename);
			dto.setJob(job);
			
			if(mgr != null && !"".endsWith(mgr)) dto.setMgr(Integer.valueOf(mgr));
			
			//hireDate: YYYY-MM-DD, ex) 2025-02-13
			
			if(hireDate != null && !"".equals(hireDate)) {
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				dto.setHireDate(formatter.parse(hireDate));	// Convert from String to Date
			}
			
			if(sal != null && !"".equals(sal)) dto.setSal(Double.valueOf(sal));
			if(comm != null && !"".equals(comm)) dto.setComm(Double.valueOf(comm));
			if(deptno != null && !"".equals(deptno)) dto.setDeptno(Integer.valueOf(deptno)); 
			
			log.info("\t2. dto:{}", dto);
			
			//-- 4-----------
			// 수신한 command가 무엇인지 식별하고, (아래 테이블을 이용해서)
			// 식별된 command의 로직을 수행할 수 있는 적합한, 서비스 객체에게
			// DTO를 전달해서, 해당 command에 대한 비지니스 로직을 수행시키도록 "위임"시킨다!
			// Command Pattern의 1단계 - 요청식별과 식별된 요청을 처리할 핸들러 확인 및 위임
			Map<String,Object> modelAndView = 
				switch(command) {			// Using Command Pattern
					case "insert" 	-> new InsertService().execute(dto);		// CREATE
					case "select" 	-> new SelectService().execute(dto);		// READ
					case "update" 	-> new UpdateService().execute(dto);		// UPDATE
					case "delete" 	-> new DeleteService().execute(dto);		// DELETE
					case "list" 	-> new ListService().execute(dto);			// LIST
					default 		-> null;		// Unknown Command
				}; // switch expression
			
			// 이 modelAndView 에는 비지니스 로직 수행의 결과물인 아래의 2가지 정보를
			// 저장하는 객체입니다:
			// (1) 1개 이상의 모델 데이터 -> 때문에 Map이 필요하죠
			//	   각 모델 데이터마다, 이름을 부여해서 관리할 수 있기 때문이죠
			// (2) 위 (1)의 모델을 기반으로 최종 응답화면을 만들어 낼 "뷰이름"(viewName)이
			//	   반환되게 됩니다. 각 서비스마다 수행하는 로직이 틀리기 때문에
			// 	   각 서비스의 모델 데이터를 보여줄 최종 응답화면을 가장 잘 보여줄 수 있는 뷰가
			// 	   각각 다르기 때문에, 뷰 이름을 서비스가 결정해야 합니다.
			// 	   (단, 스프링(부트)에서는 일반 컨트롤러가 뷰이름을 결정하도록 되어 있습니다.
			// 		우리는 일반 컨트롤러를 만들지 않기 때문에, 서비스가 결정할 뿐입니다.)
			// (3) 결국 뷰의 이름이란, 템플릿 엔진으로 JSP를 사용할 때에는, JSP파일의 이름이 되고
				//                              타임리프를 사용한다면, HTML파일의 이름이 됩니다.
				
				
			log.info("\t3. modelAndView: {}", modelAndView);
			
			if(modelAndView == null) {	// if unknown command
				// Bad Request 처리
				res.sendError(400, "알수없는 명령(%s)이 들어왔습니다".formatted(command));  //(상태, 메세지)
				return;	// 그 다음 단계가 수행되지 않고, 여기서 수행 종료시킴
			}
			
		//--5---
		// 이전 단계에서 받은 모델과 뷰 이름으로, 실제 뷰를 호출하고 이 때 모델도 뷰에게 넘겨주어
		// 호출된 뷰가 제대로 모델 데이터를 최종 응답화면에서 보여줄 수 있도록 해줘야 합니다.
		
		// 스프링(부트)에서는, 지정된 이름의 뷰를 실제 호출하는 객체를
		// viewResolver 빈이 담당합니다.
		
		ViewResolver viewResolver = new ViewResolver();
		viewResolver.render(req, res, modelAndView);
			
			
		}catch(Throwable t) {
			throw new ServletException(t);
		}// try-catch
		
	}// service
	

}// end class
