package org.zerock.myapp.service;

import java.util.Map;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.exception.InsertServiceException;
import org.zerock.myapp.model.ModelAndView;
import org.zerock.myapp.persistence.EmpDAO;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j

public class InsertService implements CommandService{
	
	@Override
	public Map<String, Object> execute(EmpDTO dto) throws InsertServiceException{
		log.debug("execute({}) invoked.", dto);
		
		try {
			//--1---
			EmpDAO dao = new EmpDAO();
			log.info("\t + dao : {}", dao);
			
			//--2----
			boolean isSucceded = dao.create(dto); 	// Model
			log.info("\t+ isCreated:{}" ,isSucceded);
			
			//--3---		
			ModelAndView<String, Object> modelAndView = new ModelAndView<>();
			modelAndView.addAttribute(MODEL_KEY, isSucceded);
			modelAndView.addAttribute(VIEW_KEY, "mvc/response");
			
			log.info("\t3. modelAndView:{}", modelAndView);

			return modelAndView;
		}catch(Exception e) {
			throw new InsertServiceException(e);
		}// try-catch
		
		
	}// execute
	
}// end class
