package org.zerock.myapp.util;

import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@RequestMapping("/tools/*")
@RestController("RequestMappingController")
public class CommonToolsController {
	@Autowired private ApplicationContext ctx;	// Spring Context 의존성 주입
	// 요청매핑테이블을 가진, Spring MVC 의 아래 컴포넌트(구성요소)
	@Autowired private RequestMappingHandlerMapping handlerMapping;	 
	
	@PostConstruct
	void preProcess() {	// 1회성 전처리 callback - 이 컨트롤러의 인스턴스 생성 직후에 콜백되는 메소드
		log.debug("preProcess() invoked.");
		
		// 필드에 자동 의존성 주입(DI)가 제대로 되었는지 확인
		Objects.requireNonNull(this.ctx);
		assert this.handlerMapping != null;
		
		log.info("\t + this.ctx:{}", ctx);
		log.info("\t + this.handlerMapping: {}", this.handlerMapping);
	}//preProcess
	
	@PreDestroy
	void postProcess() {	// 1회성 후처리 callback
		log.debug("postProcess() invoked.");
	}// postProcess
	
	//1. Spring MVC framework 의 구성요소인 HandlerMapping 이 내부적으로 Map 컬렉션으로
	// 가지고 있는 모든 컨트롤러와 모든 핸들러의 경로 매핑정보를 json으로 변환해서 응답으로 주는 핸들러
	
	@GetMapping("/mappings")
	// Map<Key,Value> 에서, Key는 매핑된 요청경로, Value는 요청을 처리하는 컨트롤러 + 핸들러
	Map<String, String> mappings(){
		log.info("mappings() invoked.");
		
		// Convert Returned Java Object To The Json.
		return 
			this.handlerMapping
				.getHandlerMethods().entrySet().stream()
				.collect(Collectors.toMap(
						entry-> entry.getKey().toString(), 		// 새로운 Map 요소의 key가 되고
						entry-> entry.getValue().toString()	// 새로운 Value 요소의 Value가 됩니다
			)// toMap
		);// collect
	}// mapping
	
	@GetMapping("/beans")
	Map<String,Object> beans(){
		log.debug("beans() invoked.");
		
		Map<String,Object> map = new Hashtable<>();	// Thread-safe
		
		int totalBeanCount = this.ctx.getBeanDefinitionCount();
		map.put("totalBeanCount", totalBeanCount);
		
		String[] beanNames = this.ctx.getBeanDefinitionNames();
		for(String beanName: beanNames) {
			Class<?> clazz=this.ctx.getType(beanName);
			map.put(beanName, clazz.getName());	
		}// enhanced for
		
		
		return map;
	}// beans
	
	
	
}// end class
