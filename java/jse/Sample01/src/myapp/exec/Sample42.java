package org.zerock.myapp.exec;

public class Sample42 {

	// 목표: 클래스 선언 시, 생성자로 초기화되지 않은
	// 필드에 기본 값을 확인(자바 타입별 기본 값)
	public static void main(String[] args) {
		DefaultFieldValue obj = new DefaultFieldValue();
		
		Utils.printToConsole(obj);
		
		
	}//main
	
}//end class
