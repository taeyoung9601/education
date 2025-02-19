package org.zerock.myapp.service;

import java.util.List;
import java.util.Map;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.domain.EmpVO2;
import org.zerock.myapp.exception.InsertServiceException;
import org.zerock.myapp.model.ModelAndView;
import org.zerock.myapp.persistence.EmpDAO;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 역할: (1) 위임받은 요청에 대한 비지니스 로직 수행
//    	(2) 로직 수행결과로, model and view name 을 반환

@NoArgsConstructor
@Slf4j

public class ListService implements CommandService{
	
	@Override
	public Map<String, Object> execute(EmpDTO dto) throws InsertServiceException{
		log.debug("execute({}) invoked.", dto);
		
		try {
			//--1---
			EmpDAO dao = new EmpDAO();
			log.info("\t + dao : {}", dao);
			
			//--2----
			List<EmpVO2> list= dao.findAllEmps(); 	// Model
			list.forEach(vo -> log.info(vo.toString()));
			
			//--3---
			/**
			 * 별도의 
			 */
//			Map<String,Object> modelAndView = new HashMap<>();		//	XX
//			Map<String,Object> modelAndView = new Hashtable<>();	// OK -> Thread- safe
//			
//			modelAndView.put(MODEL_KEY, list);
//			modelAndView.put(VIEW_KEY, "mvc/response");		// WEB-INF/views/mvc/response.jsp
			
			// MVC패턴대로 각각의 타입을 만들어 사용하는 것으로 교체합니다.
			ModelAndView<String, Object> modelAndView = new ModelAndView<String, Object>();
			modelAndView.addAttribute(MODEL_KEY, list);
			modelAndView.addAttribute(VIEW_KEY, "mvc/response");
			
			log.info("\t3. modelAndView:{}", modelAndView);
			
			
			return modelAndView;
		}catch(Exception e) {
			throw new InsertServiceException(e);
		}// try-catch
		
		
	}// execute
	
}// end class
