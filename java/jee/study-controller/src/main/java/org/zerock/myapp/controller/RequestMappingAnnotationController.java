package org.zerock.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.PersonDTO;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@RequestMapping("/request/mapping/annotation/*")

//==========
// 목표: 경로매핑에 사용되는 어노테이션의 나머지 속성의 사용법에 대해서 알자
//==========

@RestController
public class RequestMappingAnnotationController {
	
	// 1. value (or path)속성 - 매핑경로를 지정합니다.
	@GetMapping(path= {"/AAA","/BBB"})
	String aaa() {
		log.debug("aaa() invoked.");  
		return "/AAA OR /BBB";     
	}
	// 2. method - HTTP method 전송방식을 제약하는데 사용합니다.
	
	// 요청시, 입력데이터인 Request Parameter를 핸들러에서 얻는 방법:
	// (1) 핸들러의 매개변수명을, 받고자 하는 매개변수
	// (2) 
	
	@GetMapping("/makePerson")
//	String makePerson(String name,Integer age) {	// ok, (1)방법
	String makePerson(
			@RequestParam("name") String myName,
			@RequestParam("age") Integer myAge) {	// (2) 방법
		log.debug("makePerson({},{}) invoke.", myName, myAge);
		
//		String result = name + age;		//(1)
		String result = myName + myAge;	//(2)
		
		return result;
	}// makePerson
	
	
	// 3. params
	//		이 속성은, 요청을 위임받아서 아래의 핸들러가 요청을 처리하고자
	// 	할 때, **반드시** 입력데이터로 들어와야할 요청파라미터 이름을
	// 	지정할 때 사용합니다.
	// 	  즉, 필수 요청파라미터를 지정함으로써, 반드시 전송되어야할 요청
	// 	  파라미터를 지정하는데 사용됩니다.
	@GetMapping(path = "/makePerson2", params = {"name"})
	String makePerson2(String name,Integer age) {	// ok, (1)방법
		
		log.debug("makePerson({},{}) invoke.", name, age);
		String result = name + age;		//(1)
		
		return result;
	}// makePerson2
	
	// 4. headers
	// 요청 메시지의 헤더영역에, 이 속성에 지정된 이름의 헤더가 반드시
	// 포함되어야 요청을 처리하겠다고 제약하는데 사용됩니다.
	@GetMapping(path = "/headers", headers = {"My-Header"})
	String headers() {
		log.debug("headers() invoked.");
		
		return "headers";
	}// headers
	
	// 5. consumes
	// HTTP request 메시지에 포함된(body) 컨텐츠의 형식을 제약할 때 사용
	// 예: Content-Type 이 application/json 으로 지정하면,
	// JSON 문자열이 포함된 요청만 처리하겠습니다란 뜻이 됩니다.
	@PostMapping(path = "/consumes", consumes = {"application/json"})
	public String postMethodName() {  
		log.debug("postMethodName() invoked.");
		 
		return "consumes";
	}// consumes
	
//==========================
	
	// 요청 파라미터를 DTO(=Spring Command Object)로 받아내는 방법을 배우자!
	@GetMapping(path= "/person", params = {"name"})
	PersonDTO dto(PersonDTO dto) {
		log.debug("dto({}) invoked.", dto);
		
		return dto;
	} // dto
	
}// end class
