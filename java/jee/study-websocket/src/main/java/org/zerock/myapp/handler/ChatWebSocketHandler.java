package org.zerock.myapp.handler;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

public class ChatWebSocketHandler extends TextWebSocketHandler {
	
	// 현재 시점에 유효한 연결세션들을 체계적으로 관리할 목적으로
	// 선언한 Set 컬렉션으로, 요소는 연결된 세션 객체입니다.
	private Set<WebSocketSession> sessions = new HashSet<>();
	
	// 웹 소켓을 이용해서, 클라이언트와 서버의 접속포인트(endpoint) 간에
	// 연결이 생성된 직후 자동 호출되는 콜백 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.debug("afterConnectionEstablished({}) invoked.", session);
		
		this.sessions.add(session);
		this.sessions.forEach(s-> log.info("\t + Active Session:{}", s));
	}//afterConnectionEstablished
	
	// 웹 소켓을 이용해서, 클라이언트와 서버의 접속포인트(endpoint) 간에
	// 연결이 닫힌 직후 자동 호출되는 콜백 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.debug("afterConnectionClosed({},{}) invoked.", session, status);
		
		this.sessions.remove(session);
		this.sessions.forEach(s-> log.info("\t + Active Session:{}", s));
	}//afterConnectionClosed

	// 웹 소켓을 이용해서, 클라이언트로부터 텍스트 메시지가 수신되었을 때
	// 연결이 생성된 직후 자동 호출되는 콜백 메소드	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.debug("afterConnectionClosed({},{}) invoked.", session, message);
		
		// 지금 이 텍스트 메세지를 보낸 클라이언트만 빼고
		// 필드의 컬렉션에 저장된 나머지 모든 클라이언트에게,
		// 지금 받은 텍스트 메시지를 브로드캐스팅(Broadcasting)해야, 채팅이 성립이
		// 되겠죠?! 그래서, 이 메소드의 첫번째 매개변수인 세션은,
		// 현재 수신 메시지를 보낸 클라이언트이기 때문에 브로드캐스팅 대상 세션에서는
		// 제외시켜야 합니다.(왜? 자기가 보낸 채팅 메시지를 다시 받으면 이상하니까)
		
		for( WebSocketSession activeSession : this.sessions) {
			if(activeSession.isOpen() && !activeSession.equals(session)) {
				activeSession.sendMessage(message);
			}
		}
		
	}//handleTextMessage

	
	
	

}// end class
