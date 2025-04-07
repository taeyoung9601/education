package org.zerock.myapp.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.zerock.myapp.domain.PersonDto;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@RequestMapping("/sse/*")
@Controller
public class SSEController implements ApplicationListener<ContextClosedEvent> {
   // SseEmitter 객체는 클라이언트와의 연결을 유지하며 이벤트를 전송하는 역할 
   private Set<SseEmitter> emitters = new HashSet<>();
   
   // Important : this handle must return SseEmitter object. (protocol) 
   @CrossOrigin(origins = {"https://localhost"}) // 보안 관련
   @GetMapping("/time")
   SseEmitter streamTime(PersonDto dto) { 
      log.debug("streamTime() invoked.");
      
      // -- 1 ------------------
      // The parameter of a Constructor means Emitter's lifetime
      // This emitter is a resource object. thus it must be close
      SseEmitter emitter = new SseEmitter(0L);  // 0L : No expiration
      this.emitters.add(emitter);				// Add new SseEmitter into the Set.

      // For each event, event callback invoked.
      emitter.onCompletion(() -> emitter.complete()); //When event sending completed through the Emitter. -> close emitter
      // complete 가 발생했을 때 flush 가 발생하기 때문에 complete 가 꼭 필요함.
      
      emitter.onError(e -> emitter.completeWithError(e));       // When error occurred from the Emitter. -> close emitter with error
      // 에러 출력
      
      emitter.onTimeout(() -> emitter.complete());    // When the Emitter timeout expired. -> close emitter
      
      // -- 2 ------------------
      // Using thread pool,the specified task scheduled by fixed rate.
      Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
            () -> {
               // 3L 마다 실행시킬건데 여기서 무엇을 할 것이냐?
               try {
            	   emitter.send("Current Time:" + LocalTime.now());
            	   
//            	   emitter.send(dto);                                                         // 2-1             // 2-1
//                 emitter.send(dto, MediaType.APPLICATION_JSON);                             // 2-2
//                 emitter.send(SseEmitter.event().data(dto, MediaType.APPLICATION_JSON));    // 2-3

            	   
               } 
               catch (IOException _ignored) {} // << 매개변수 타입이 Object 하나 => 모든 것을 다 보낼 수 있음.
            },         			// The Task: Lambda Expression
            0,                  // No initial delay.
            3L,               // 3L -> 3 time unit -> 3 seconds -> each 3 seconds
            TimeUnit.SECONDS  // Time Unit
            );
      
      
      
      // -- 3 ------------------
      return emitter;
   }

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		log.debug("onApplicationEvent({}) invoked.", event);
		
		this.emitters.forEach(sse-> sse.complete());	// Close All SseEmitter Resource Object.
		this.emitters.clear();							// Clear Set<E> collection.
		
	}

   
} // end class
