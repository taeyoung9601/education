package org.zerock.myapp.exception;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class TryCatchFinallyExample {

	// 목표: try-catch-finally blocks을 이용한 예외처리 기본에 대해 이해하기
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
//			java.lang.Class clazz = java.lang.Class.forName("java.lang.String");
		
		Scanner scanner = new Scanner(System.in);
		
		try { // 단독사용 불가 <-> try-with-resources 단독사용가능
			int intNumber = Integer.parseInt("100ㅁ");	// 1st. method
			
			log.info("Enter your age: ");
//			int intNumber = scanner.nextInt();			// 2nd. method
			
			log.info("\t+ intNumber: {}", intNumber);
//		} catch(NumberFormatException | InputMismatchException e) {	// Multi-Catch
			
		} catch(InputMismatchException | NumberFormatException e) {
			e.printStackTrace();			// 필수: 무조건 수행(모든 Catch block에서)
			
			log.info("------------2-------------"); // 예외처리로직
		} finally {		// try block 예외발생여부와 상관없이, 실행이 보장되는 block -> 자원해제
			if(scanner !=null) scanner.close();
			
			log.info("Done.");
		} // try-catch-finally
		
	} // main

} // end class
