package org.zerock.myapp.exec;

public class Sample24 {

	// 목표: for문의 카운터 변수의 타입을 무조건 정수로만 해야하는가!
	// 		 실수 타입으로는 못하는가?! -> 정수로 밖에는 사용불가!
	public static void main(String[] args) {
		
		//0.1<=i<= 1.0, 0.1씩 증가 => 기대치 : 10번 반복할 수 있는가
		for(float i = 0.1f;i<=1.0;i=i+0.1f) {
			Utils.printToConsole(i+"");
		}// for
		
		int count = 0;
		for(double i = 0.1;i<=2.0;i=i+0.1) {
			Utils.printToConsole(i+"");
			System.out.println("[" + ++count + "]");
		}// for
		
	}//main
	
}//end. class
