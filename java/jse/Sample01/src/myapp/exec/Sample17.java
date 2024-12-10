package org.zerock.myapp.exec;


public class Sample17 {

	// 목표 : 단순 If문(only if block)
	public static void main(String[] args) {
		int score = 84;
		
		if(score>= 90) {
			System.out.println("A");
		}
		
		if(score <90) {
			System.out.println("B");
			System.out.println("C");
			//이후에도 다른 실행 문장이 수백줄 있음..
		}
		
	} // entry, main
	
}// end. class