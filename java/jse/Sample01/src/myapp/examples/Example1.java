package org.zerock.myapp.examples;

public class Example1 {

	// CLI 기반의 지속적인 한줄 로그 출력 예제
	public static void main(String[] args) throws InterruptedException {
		
//		final String ESC = "\u001B";
		
		
		for(var i = 0; i<=100; i++) {
//			1st. method
//			System.out.print("\rProgress: "+ i + "% "); 
			
			//응용 :
			//내 만들어 본 코드
//			System.out.print("\rProgress: "+ i + "% "); 
//			for(int j=0; j< i/10; j++) {
//				if (i % 10 == 0) {
//					System.out.print("■");
//				}; 
//			}
			
			//강사님 코드
			if(i % 10 == 0) {
				System.out.print("\r"+ "*".repeat(i/10) + "->" + i + "%");
			}
			
			
			//2nd. method
//			System.out.print(ESC + "[G");
//			System.out.print("Progresss: " + i + "%");
			
			Thread.sleep(100);  // unit = milliseconds(1000ms = 1second)
			
		}// for
		
		System.out.println("\nDone.");
	}// main
	
}// end class
