package org.zerock.myapp.examples;


import java.util.Date;
import java.util.Scanner;

import org.zerock.myapp.libs.Utils;

public class ChatBot {

	public static void main(String[] args) {
		
		Scanner scanner =  new Scanner(System.in);
		
		Utils.printToConsole("안녕하세요. 무엇을 도와드릴까요?");
		
		while(true) {
			System.out.print(">>");
			String input = scanner.nextLine();
			
			if("안녕".equalsIgnoreCase(input)|| "Hello".equalsIgnoreCase(input)) {
				Utils.printToConsole("안녕하세요! 저는 챗봇이랍니다. 반가워요");
			}else if("날씨".equalsIgnoreCase(input)|| "Weather".equalsIgnoreCase(input)) {
				Utils.printToConsole("오늘 날씨는 눈이 왕창 올 예정입니다.");
			}else if("시간".equalsIgnoreCase(input) || "Time".equalsIgnoreCase(input)) {
				Utils.formatToConsole("현재 시간은 {0} 입니다", new Date());
			}else if("종료".equalsIgnoreCase(input) || "quit".equalsIgnoreCase(input)) {
				Utils.printToConsole("종료!");
				break;
			}else {
				Utils.formatToConsole("죄송합니다. 이해를 못했습니다.({0})", input);
			}// if-else if-else
		
		}//while
	
		Utils.printToConsole("대화가 종료되었습니다");
		
		scanner.close();
	}// main
	
}// end class