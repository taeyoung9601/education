package org.zerock.myapp.tools;

import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/api")

@RestController("commonApiController")
public class CommonApiController {
	@Autowired private RequestMappingHandlerMapping handlerMapping;
	@Autowired private ApplicationContext context;
	
	
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
		log.info("\t+ this.context: {}", this.context);
	} // postConstruct
		
	@GetMapping("request/mappings")
	public Map<String, String> getRequestMappingTable1() {
		log.trace("getRequestMappingTable1() invoked.");
		
		return this.handlerMapping.getHandlerMethods().entrySet().stream()
					.collect(Collectors.toMap(entry -> entry.getKey().toString(), entry -> entry.getValue().toString()));
	} // getMappings
	
	@GetMapping("list/beans")
	Map<String, Object> listBeans() {
		log.trace("listBeans() invoked.");

		Map<String, Object> map = new Hashtable<>();
		
		int beanCount = this.context.getBeanDefinitionCount();
		map.put("beanCount", beanCount);	
		
		String[] beanNames = this.context.getBeanDefinitionNames();
//		map.put("beanNames", beanNames);
		
		for(String beanName : beanNames) {
			Class<?> clazz = this.context.getType(beanName);
			map.put(beanName, clazz.getName());
		} // enhanced for
		
		return map;
	} // listBeans
	
} // end class


