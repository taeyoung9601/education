package org.zerock.myapp.exception;

import java.util.Arrays;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class UserDefinedExceptionExample {

	// 목표: 
	//  (1) 내가 선언한 메소드가 예외를 발생시킬 수 있음을 표시하는 방법
	//  (2) 실제 예외를 강제적으로 발생시키는 방법
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		Calculator calc = new Calculator();
		
		int result = calc.add(100, -129);
		log.info("\t result: {}", result);
	}
	
}


@Log4j2
@NoArgsConstructor
final class Calculator {
		
	public int add(final int num1, final int num2)
		throws IllegalArgumentException {	// byte: -128 - +127
		log.debug("add({}, {}) invoked.", num1, num2);
		
		if ((num1 < Byte.MIN_VALUE || num1 > Byte.MAX_VALUE) || 
				(num2 < Byte.MIN_VALUE || num2 > Byte.MAX_VALUE)) {	// 처리불가한 값이 들어옴
			// 예외 발생시켜야함
			// 강제예외발생 문법: throw new <발생시킬예외클래스생성자호출>
			
			// (1) 예외메시지를 만들지 않고 예외객체 생성 및 던지기
//			throw new IllegalArgumentException(); 
			
			// (2) 예외메시지를 만들지 않고 예외객체 생성 및 던지기
//			throw new IllegalArgumentException( "num1: %s, num2: %s".formatted(num1, num2)); 
			
			// (3) 다른 예외객체를 만들어서, 객체를 만들어서, 예외객체의 생성자에 넣어주는 방법
			throw new IllegalArgumentException(new IllegalStateException("Invalid arguments"));
		} // if
		
		return num1 + num2;
	} // add
	
	public int sub(final int num1, final int num2) throws IllegalArgumentException {	// byte: -128 - +127
		log.debug("sub({}, {}) invoked.", num1, num2);
		
		if ((num1 < Byte.MIN_VALUE || num1 > Byte.MAX_VALUE) || 
				(num2 < Byte.MIN_VALUE || num2 > Byte.MAX_VALUE)) {	// 처리불가한 값이 들어옴
			// 예외 발생시켜야함
		}
		
		return num1 - num2;
	}
} // end class