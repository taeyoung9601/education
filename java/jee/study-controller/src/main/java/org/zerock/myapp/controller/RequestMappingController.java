package org.zerock.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@NoArgsConstructor
@Slf4j

//@RequestMapping("/request")
//@RequestMapping("/request/")
@RequestMapping("/request/*")
@RestController
public class RequestMappingController {

	// 1. 정적 경로를 이용한 요청 매핑
	@GetMapping("/static/path")
	String staticPath() {
		log.debug("staticPath() invoked.");
		return "staticPath";
	} // staticPath
	
	// 2. 경로 변수를 이용한 요청 매핑
	//	  요청이 들어왔을 때, 이 경로변수를 통해서, 동적인 값을 받을 수 있음
	@GetMapping("/users/{id}/{age}")	// {PathVariable}를 매핑경로에 포함시킴
//	String pathVariable(@PathVariable String id){		// @PathVariable : 매개변수에 포함해야 값이 나옴
//	String pathVariable(@PathVariable String userId){	// XX : 
//	String pathVariable(@PathVariable("id") String userId){	// OK : Recommended
	String pathVariable(
			@PathVariable("id") String userId,
			@PathVariable("age") Integer myAge
		){
		log.debug("pathVariable({}) invoked.", userId);
		log.debug("pathVariable({}) invoked.", myAge);
		return "pathVariable";
	}// pathVariable
	
	// 3. 와일드카드 문자(*,**)를 이용한 경로매핑
	@GetMapping("/files/*")					// OK: /files/1.jpg, /files/2.jpg, /files/package.json
	String wildCard1() {
		log.debug("wildCard1() invoked.");
		
		return "wildCard1";
	}// wildCard1
	
	// 4. 와일드카드 문자(*,**)
	//	  *: 오직 1개의 단어만 매핑
	//   **: 하위 경로까지 다 포함해서 매칭
	//	주의사항: 만일, 요청경로가 와일드카드 2개와 매칭되는 경우에는, 하나(*) 와일드카드가 우선합니다.
	@GetMapping("/files/**")					// OK: /files/1.jpg, /files/a/b/c/1.jpg
	String wildCard2() {
		log.debug("wildCard2() invoked.");
		
		return "wildCard2";
	}// wildCard2
	
	// 5. Path Variable + Regular Expression (정규 표현식)
	@GetMapping("/users/{id:[0-9]+}")
	String regularExpression(@PathVariable("id") int id) {
		log.debug("regularExpression({}) invoked.", id);
		return "regularExpression";
	}// regularExpression
	
	// 6. Path Mapping With File Extension
	//	  파일의 확장자를 이용한 경로매핑 (쓰지마세요)
	
	//  6-1 정규 표현식임(파일의 확장자가 .hwp 또는 .txt만 허용)
//	@GetMapping("/file/{filename1:.+\\.hwp}")			// 최소 파일명은 1자 이상이어야함
//	String fileExtension1(@PathVariable("filename1") String filename1) {
//	 	log.debug("fileExtension1({}) invoked.", filename1);
//		return "fileExtension1";
//	}// fileExtension1
		
	// 6-2 
	@GetMapping("/file/{filename2:.+\\.(?:hwp|txt)}")			// 최소 파일명은 1자 이상이어야함
	String fileExtension2(@PathVariable("filename2") String filename2) {
	 	log.debug("fileExtension2({}) invoked.", filename2);
		return "fileExtension2";
	}// fileExtension2

//	// 7. Path + Query String 경로 매핑	--> 잘못된 경로 매핑
//	// 이유 : 매핑할 경로에는 Query String 넣으면 안된다! (**) // 허용되지않는다
//	@GetMapping("/search?q=Springboot")
//	public String getMethodName() {
//		log.debug("pathWithQueryString() invoked.");
//		return "pathWithQueryString";
//	}// pathWithQueryString
	
	
	
	
	
}// end class
