package org.zerock.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.zerock.myapp.handler.ChatWebSocketHandler;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

//@EnableWebSocket
@Configuration						// Java Configuration Class + Spring context bean registered.
public class WebSocketConfig implements WebSocketConfigurer {
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		log.debug("registerWebSocketHandlers({}) invoked.");
		
		// 우리는 아래 이미 스프링부트에 구현된 텍스트 메시지를
		// 주고받는 핸들러를 상속해서, 우리만의 로직을 더 추가하고자 합니다.
		// 즉, 채팅 메시지 송/수신 처리용 핸들러
//		TextWebSocketHandler handler;
		
		// ChatWebSocketHandler는 우리가 구현할 웹 소켓 핸들러 입니다.
		registry.addHandler(new ChatWebSocketHandler(), 
				// 클라이언트가 접속할 접속 포인트를 경로 형태로 지정
				"/chat/room1"	 	// 채팅방1의 용도로 우리는 사용하자
		).setAllowedOrigins("*");	// SSE에서 나왔던, @CrossOrigin 어노테이션과 동일한 사유로
									// 호출하는 것으로, 소위 "동일출처원칙(CORS policy)"의 위반을
									// 허용시키는 설정( 위반해도 눈 감아줄게란 설정)
		registry.addHandler(new ChatWebSocketHandler(), "/chat/room2").setAllowedOrigins("*");
		
		registry.addHandler(new ChatWebSocketHandler(), "/chat/room3").setAllowedOrigins("*");	
		
	}// registerWebSocketHandlers
	
	// 위의 클라이언트가 접속가능한 경로(위의 채팅방 경로)를
	// 클라이언트가 발견할 수 있도록 노출(export) 시켜주는 빈을 만들어서
	// 스프링 컨텍스트에 등록해야 합니다.
	@Bean
	ServerEndpointExporter serverEndpointExporter() {
		log.debug("serverEndpointExporter() invoked.");
		return new ServerEndpointExporter();
	}// serverEndpointExporter

	
	
}// end class
