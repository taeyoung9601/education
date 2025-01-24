package org.zerock.myapp;

import java.util.Arrays;
import java.util.stream.IntStream;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class App {
    
	public static void main(String[] args) {
		log.debug("main({}) invoked.",Arrays.toString(args));
		
		//--1
		// Sequential Number
		// void accept(int value);
		// void(int value)
//		IntStream.range(1, 10).forEach( seq -> {
//			log.info("seq: {} ", seq);
//		}); // forEach
		
		//--2
		//내부 > 외부
//		IntStream.rangeClosed(1, 10).forEach( seq -> {
//			log.info("seq: {} " , seq);
//		});
		
		//--3
		//CPU의 모든 코어를 이용해서, 수행할 타스크를
		// 각 코어별로 나누어 수행시키고, 최종 결과만 다시 취합해서
		// 되돌려주는 소위 "병렬처리"도 이 IntStream이 가능합니다.
		// 하지만, 아래의 rangeXXX 메소드는 Single Core 기반의
		// "순차처리"(병렬처리의 반댓말)로 수행됩니다.
//		IntStream.rangeClosed(1, 10).forEachOrdered( seq -> {
//			log.info("seq: {} " , seq);
//		});
		
		//--4
		IntStream.of(77, 12, 4, 90, 13).forEach(number -> {
			log.info("number: {} " , number);
		});
		
		
	}
	
}
