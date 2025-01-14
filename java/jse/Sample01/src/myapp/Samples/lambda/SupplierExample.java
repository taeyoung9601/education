package org.zerock.myapp.lambda;

import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SupplierExample {

	// 목표 : 두 번째로 많이 사용하는 표준 함수적 인터페이스인
	//		  Supplier 에 대해서 이해하고 사용방법을 익히자!
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		//--1
		//int getAsInt();
		//int()
		IntSupplier intSupplier = () -> (int)(Math.random() * 6 ) + 1;
		int diceNumber = intSupplier.getAsInt();
		log.info("\t + diceNumber : {}", diceNumber);
		
		//--2
//		T get();
		Supplier<String> myNameSupplier = () -> "Tae";
		String myName = myNameSupplier.get();
		log.info("\t + myName: {} ", myName);
		
	}// main

}// end class
