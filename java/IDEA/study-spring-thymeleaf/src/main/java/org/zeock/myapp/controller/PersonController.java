package org.zeock.myapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zeock.myapp.domain.PersonDto;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@RequestMapping("/person")
@Controller
public class PersonController {

	
	@GetMapping(path ="/me", params = {"name", "age"})
	void me(String name, Integer age, Model model) {
		log.debug("me({},{},{}) invoked.", name,age,model);
		
		// Put each request parameter into the 'Request' scope as a shared attribute.
		model.addAttribute("_NAME_", name);
		model.addAttribute("_AGE_", age);

	}// me
	
	@GetMapping(path ="/me2", params = {"name", "age"})
	void me2(PersonDto dto, Model model) {
		log.debug("me2({},{}) invoked.", dto ,model);
		
//		dto.setName("Tae");
//		dto.setAge(27);
		// Put each request parameter into the 'Request' scope as a shared attribute.
//		model.addAttribute("dto", dto);
	}// me
	
	@GetMapping(path ="/me3", params = {"name", "age"})
	void me3(PersonDto dto, Model model) {
		log.debug("me3({},{}) invoked.", dto,model);
		
		Map<String, Object> map = new HashMap<>();
		map.put("name", dto.getName());
		map.put("age", dto.getAge());
		
		// Put each request parameter into the 'Request' scope as a shared attribute.
		model.addAttribute("_MODEL_",map);

	}// me
	
}// end class
