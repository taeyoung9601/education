package org.zerock.myapp.exec;
import java.util.Scanner;

public class Sample26 {
	
	//목표1: do~while 문이 어느 때 필요한가?
	//목표2: 사용자가 키보드에서 입력한 문자열을 쉽게 읽어서 사용할 수 있다!
	//수단: Scanner 객체를 통해서..
	public static void main(String[] args) {
		Utils.printToConsole("메시지를 입력하세요...");
		Utils.printToConsole("프로그램을 종료하려면, q를 입력하세요.");
		
		//외우셔야할 조합키: control + shift+ o (Mac: command_shift+o)
		Scanner scanner = new Scanner(System.in);  // 이 문장 외우세요.
		
		String inputString; // 문자열 타입의 변수 선언(Not 정의)
		
		do {
			System.out.print(">"); // Prompt 만 출력하고 엔터키는 입력하지 않음.
									// 바로 이 Prompt 오른쪽에서, 사용자 입력을 받기 위해서.
			
			inputString = scanner.nextLine();
			Utils.printToConsole("inputString:"+inputString);
		}while(!inputString.equals("q"));  //한 개의 실행문으로 취급되기 때문에, 세미콜론(;)으로 마감처리
		
		//String 타입의 메소드 중에, 위의 equals 메소드는 아래와 같이 사용합니다:
		//String.equals("비교할문자열"): 같으면 true 반환, 틀리면 false 반환
		
		//강사가 만들어 준, 아래의 메소드는 문자열 출력 이후에, System.out.prinln 메소드와
		//똑같이 자동으로 엔터키를 입력합니다.
		Utils.printToConsole("DONE.");
	}// main
	
}// end. class
