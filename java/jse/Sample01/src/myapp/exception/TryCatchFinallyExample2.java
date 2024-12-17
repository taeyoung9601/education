package org.zerock.myapp.exception;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class TryCatchFinallyExample2 {

	// 목표: try-catch-finally blocks을 이용한 예외처리 기본에 대해 이해하기
	public static void main(String[] args) /* throws InterruptedException */ {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		try {
			Thread.sleep(1000 * 1);
//		} catch (InterruptedException | IllegalArgumentException e) {	// 1st. method: Multi-Catch
		} catch (InterruptedException e) {		// 다중 Catch1
			e.printStackTrace();
		} catch ( IllegalArgumentException e) {	// 다중 Catch2
			e.printStackTrace();			
		}
	} // main
	
} // end class