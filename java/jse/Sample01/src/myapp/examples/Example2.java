package org.zerock.myapp.examples;

public class Example2 {

	// 로그에 색상(color)을 입히자! - 컬러로그 출력
	public static void main(String[] args) {
		
		final String RESET = "\u001B[0m";
		
		final String BLACK = "\u001B[30m";
		final String RED = "\u001B[31m";
		final String GREEN = "\u001B[32m";
		final String YELLOW = "\u001B[33m";
		final String BLUE = "\u001B[34m";
		final String PURPLE = "\u001B[35m";
		final String SKY = "\u001B[36m";
		final String LIGHTGRAY = "\u001B[37m";

		String outputMessage = "Hello, World!";
		
//		System.out.println(RED + outputMessage + RESET);
//		System.out.println(GREEN + outputMessage + RESET);
//		System.out.println(YELLOW + outputMessage + RESET);
//		System.out.println(BLUE + outputMessage + RESET);
//		System.out.println(PURPLE + outputMessage + RESET);
//		System.out.println(SKY + outputMessage + RESET);
//		System.out.println(LIGHTGRAY + outputMessage + RESET);
		
//		for(int i=0; i<6; i++) {
//			System.out.print("\r"+ "*".repeat(i*2));
//		}
		
		int []star = {1,2,3,4,5,6};
		
		for(int i : star) {
			System.out.print("".repeat( 6-i ) +  GREEN + "\r"+ "*".repeat( (i-1)*2 + i ) + RESET + "".repeat( 6-i));
		}

		
		
		
		
		
		
	}// main
	
}// end class
