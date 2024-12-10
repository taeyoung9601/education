package org.zerock.myapp.exec;
import java.io.IOException;

public class Sample25 {

	// 키보드의 입력을 받는 CLI 기반의 메뉴 프로그램 만들어보자!
	public static void main(String[] args) throws IOException {
		boolean run = true; // 플래그(flag) 변수
		int speed = 0;		//상태 변수: 즉, 시간 흐름에 따라 값이 변함
		int keyCode = 0;	// 키보드에서 입력된 값(=이를 키코드, key code라고 함) 저장
		
		while(run) {
			if(keyCode != 10 && keyCode != 13) {
			Utils.printToConsole("=================================");
			Utils.printToConsole("(1) 증속 | (2)감속 | (3)중지(종료)");
			Utils.printToConsole("=================================");
			System.out.print("선택> ");
			}
			
			//키보드에서 입력된 값 얻는 실행문
			// ASCII 코드테이블대로, 키보드에서 입력된 키값(=Key code)
			// 여러분이 자바 입출력(Java I/O)을 배우시기 전까지는 외워야 함
			keyCode = System.in.read();  // *중요 : 여러분이 키보드의 키를 누르기 전까지는 멈춤
			
			if(keyCode == 49) {// 메뉴 1(증속) 을 선택하면...
				Utils.printToConsole("현재속도는"+ ++speed);
			}else if(keyCode == 50) { //메뉴 2(감속) 선택하면..
				Utils.printToConsole("현재속도는"+ --speed);
			}else if(keyCode == 51){ // 메뉴 3(종료)선택하면..
//				run = !run; 
				run = false;   // 이렇게 플래그변수(boolean)로 자연스럽게 반복 종료가 좋음
//				break;  // 사실상, 반복문을 종료시키는 최후의 수단
			}// 다중 if문
			
			
		}// infinite loop
		
		Utils.printToConsole("DONE");
		
		
	}// main
	
}// end. class
