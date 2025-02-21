package org.zerock.myapp.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;




@Slf4j
@NoArgsConstructor

// Spring MVC pattern 대로, View 를 통한 SSR(Server-Side Rendering)로
// 응답화면을 동적으로 만들어내는 경우에는, 아래와 같이 @Controller
// 어노테이션을 붙이셔야합니다!

@RequestMapping("/handlerReturnTypes/*")

@Controller
public class ReturnTypesController {
	
	// 1. 리턴타입이 void인 경우
	//	  결론 : 뷰의 이름은, Base URI + Detail URI => "매핑 경로"가 그대로 뷰의 이름이 됩니다!!
	@GetMapping("/void")
	void returnVoid() {
		log.debug("returnVoid() invoked.");
		
		// 결론 : 뷰의 이름은 매핑경로가 됩니다! : handlerReturnTypes/void	
	}// returnVoid
	
	// 2. 리턴타입이 String 인 경우 => 결론: 반환한 문자열이 그대로 "뷰의 이름"이 됩니다.
	@GetMapping("/string")
	String returnString() {
		log.debug("returnString() invoked.");
		
		return "Tae";		// 이 반환 문자열이 곧 "뷰의 이름"이 됩니다.
	}// returnString
	
	// 3. 리턴타입이 String 인 경우 + 스프링이 제공하는 특수 문자열로 "redirect."를 붙였을 때
	//    결론: 이때는 뷰의 이름이 아니라, 리다이렉션을 수행하라! 로 변경됨
	@GetMapping("/redirect")
	String redirect() {
		log.debug("redirect() invoked.");
		
		// 리다이렉션 수행
//		return "redirect:https://naver.com";		// OK, 1. 외부 URL 저장
		
		// Redirection 수행시, 다른 컨트롤러의 특정 핸들러로 요청을 보내야 하는 경우에는
		// 아래와 같이 다른 컨트롤러의 Base URI까지 완전하게 지정하시면 됩니다.
//		return "redirect:/handlerReturnTypes/void";	// OK, 2. 내부로 이동
		return "redirect:/transfer/checkIdentity";	// OK, 계좌이체를 담당하는 컨트롤러의 본인확인 핸들러로 이동
		
		// 결론: 상세 URI 이름만 지정해도, 자동으로 이 컨트롤러에 지정한
		//   	BASE URI가 붙게 됩니다. => 같은 컨트롤러의 다른 핸들러로 요청을 보낼 때 필요
		// 	    같은 컨트롤러 내부의 다른 핸들러로 요청을 보낼 때에는, 상세 URI만 지정하면 됩니다.
//		return "redirect:void";						// OK, 3. BaseURI 생략하고, 상세 URI만 지정하는 경우
	}// redirect
	
	// 4. 리턴타입이 String 인 경우 + 스프링이 제공하는 특수 문자열로 "forward:"을 붙이면,
	// 	  결론: 뷰의 이름이 아니라, 요청포워딩을 수행하라!는 의미가 됩니다.
	@GetMapping("/forward")
	String forward() {
		log.debug("forward() invoked.");
		
		// 요청 포워딩 수행 -> 같은 APP 내부에서만 허용
//		return "forward:https://naver.com";			// XX: 1.외부 URL지정 -> 허용되지않음
		
		// 다른 컨트롤러의 핸들러로 요청을 위임(forward)할 때는 Base URI까지 지정해야 합니다.
//		return "forward:/handlerReturnTypes/void"; 	// OK: 2. 완전한 경로매핑지정(BaseURI + DetailsURI)
		
		// 같은 컨트롤러 내부의 다른 핸들러로 요청을 위임(forward)할 때에는,
		// 상세 URI이름만 지정하시면 됩니다.
//		return "forward:void";						// OK: 3. 상세 URI만 지정하는 경우
		
		// 아래처럼, 완전한 뷰파일의 경로를 지정하는 것은 가능하지만,
		// 스프링 MVC의 개념은 모델데이터를 보여주는 "뷰의 이름"만을 통해서
		// 뷰 파일이 수행되게 해야 하기 때문에, 아래와 같이 하시면 절대 안된다!!
		return "forward:/WEB-INF/views/Tae.jsp";	// OK: 4. 포워딩 성공 (절대하지마라!)

	}// forward

	// 5. 뷰의 이름을 반환하지 않고, 일반 참조타입의 객체를 반환하는 경우
	// 	  결론1: "상세 URI"가 자동으로, "뷰의 이름"이 됩니다.
	// 	  결론2: 리턴한 참조타입객체의 Request Scope 공유속성으로 바인딩
	//			이 때, 공유속성의 이름은 핸들러에서 반환한 자바 참조타입명의
	//			첫 문자만 소문자로 바구고, Camel 표깁버이 적용된 것이 됩니다.
	@GetMapping("/object")
//	PersonDTO returnObject() {					// OK, 1: 마치 상세URI가 자동으로 뷰의 이름
	List<Integer> returnObject(){
		log.debug("returnObject() invoked.");
		
		// 1. DTO 생성 및 반환
//		PersonDTO obj = new PersonDTO();
//		obj.setName("Tae");
//		obj.setAge(27);
		
		// 2. List<Integer> 생성 및 반환
		List<Integer> obj = Arrays.<Integer>asList(1,2,3,4,5,6,7);
				
		return obj;
	}// returnObject
	
	// 6. Return Type Using @ResponseBody Annotation.
	// 	  결론: 핸들러가 반환한 자바 참조타입의 객체이면, 어떤 객체이든
	//		   JSON으로 변환 => HTTP response 메시지의 Body(Payload)에 넣어주는 기능 수행
	@GetMapping("/usingResponseBodyAnnotation")
//	@ResponseBody PersonDTO usingResponseBodyAnnotation() {		// OK, 1. 참조타입객체반환
//	@ResponseBody List<Integer> usingResponseBodyAnnotation() {	// OK, 2. 참조타입객체반환
	@ResponseBody boolean usingResponseBodyAnnotation() {		// OK, 3. 기본타입반환
		log.debug("usingResponseBodyAnnotation() invoked.");
		
		// 1st try
//		PersonDTO obj = new PersonDTO();
//		obj.setName("Tae");
//		obj.setAge(27);
		
		// 2nd try
//		List<Integer> obj = Arrays.<Integer>asList(1,2,3);
		
		// 3.
		boolean obj = true;
		
		return obj;
	}// usingResponseBodyAnnotation
	
	
	// 7. Return Spring ResponseEntity<T>
	//    Spring ResponseEntity<T>의 목적: 
	//		HTTP response 의 (1) 응답 상태코드(HTTP status code)
	//		HTTP response 의 (2) 응답 헤더항목들(HTTP headers)
	// 		HTTP response 의 (3) 응답 바디(payLoad)에 넣을 자바 객체까지
	//	 	모든 것을 개발자가 원하는대로, 응답을 제어하는데 사용하는 객체입니다.
	@GetMapping("/responseEntity")
	ResponseEntity<String> 
	responseEntity() {
		log.debug("responseEntity() invoked.");
		
		// 1. HTTP status code
		final HttpStatus statusCode= HttpStatus.OK;
		
		// 2. Content-Type: application/json; charset=utf8
		HttpHeaders headers = new HttpHeaders();
//		headers.add(key,value);			// 새로운 헤더 추가
//		headers.set(key, newValue);		// 기존 헤더 값 변경
//		headers.remove(key);			// 지정된 헤더 삭제
		headers.set("Content-Type","application/json; charset=utf8");
		
		// 3. HTTP response 의 Body(Payload)에 넣을 문자열
		String json = """
				{
					"name": "Tae",
					"age": 23
				}
				""";
		
		// 위의 (1), (2),(3) 을 이용해서, 전송할 HTTP 응답메시지 생성 및 전송
//		return new ResponseEntity<>((3),(2),(1));
		return new ResponseEntity<>(json,headers,statusCode);

	}// responseEntity
	
	
	
}// end class
