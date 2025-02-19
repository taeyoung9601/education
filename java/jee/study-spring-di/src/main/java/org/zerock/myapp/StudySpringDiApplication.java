package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;



// 실행 클래스 명 = 프로젝트명 (Camel-Caese) + Application(접미사)

//@Log4j2
@Slf4j

// 실행클래스 자체도, 구동시 인스턴스를 하나 생성해서,
// Spring Context(=이를, 스프링 빈즈 컨테이너라고 부르죠?)에 자동 주입 가능한
// 
@NoArgsConstructor

@SpringBootConfiguration		// = @Configuration
//@EnableAutoConfiguration
//@ComponentScan

@SpringBootApplication
public class StudySpringDiApplication extends ServletInitializer{
	// 자동으로 만들어진 위 ServletInitializer 를 extends 해야만,
	// 외부의 WAS에 배포되어, 하나의 웹 어플리케이션으로도 서비스가 가능해짐

	public static void main(String[] args) {
		SpringApplication.run(StudySpringDiApplication.class, args);
		log.debug("main({}) invoked.", Arrays.toString(args));
	}

} // end class
