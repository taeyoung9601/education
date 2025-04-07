package org.zerock.myapp.schedule;

import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j

// 스프링부트가 제공하는 스케쥴된 타스크(Task)
@Component
public class ScheduledTask {
	
	// (1) 지정된 주기에 따라, 반복적으로 수행되는 타스크
	// 	   클라이언트 요청에 의해서 수행되는 타스크가 아니라, 오로지
	// 	   지정된 주기대로 자동으로 수행되는 메소드이기 때문에,
	//     가. 매개변수도 없고
	//     나. 리턴 타입도 없습니다.
	// 	   (중요) 주기에 맞게 이전에 수행된 타스크 (즉, 이 메소드 호출)가 아직 끝나지 않았더라도
	// 	   		 스케쥴링된 대로 구동되는 타스크랍니다.
	// 			 즉, 쉽게 말하면, 이전 타스크의 완성여부와 상관없이 스케쥴대로 무조건 구동시킴
	
	@Scheduled(fixedRate = 3L, timeUnit = TimeUnit.SECONDS)
	void taskByFixedRate() {
		log.debug(">>>>>>>>>>>>>>>>taskByFixedRate() invoked.");
	}// taskByFixedRate
	
	@Scheduled(fixedDelay = 1L, timeUnit = TimeUnit.MINUTES)
	void taskByFixedDelay() {
		log.debug(">>>>>>>>>>>>>>>>taskByFixedDelay() invoked.");
	}// taskByFixedDelay
	
	// Cron task: crontab table, configure job schedule.
	@Scheduled(cron="0 * * * * ?")
	void cronTask(){
		log.debug(">>>>>>>>>>>>>>>>cronTask() invoked.");
	}// cronTask
	
}// end class
