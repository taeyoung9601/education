package org.zerock.myapp.examples;

import java.util.Random;
import java.util.Scanner;

import org.zerock.myapp.libs.Utils;

// 가위(1)/바위(2)/보(3) 게임
public class SimpleGame {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		
			try {
				Utils.printToConsole("가위/바위/보 게임을 시작합니다.");
			    
			   
			    while(true) {
			    	Utils.printToConsole("가위(1), 바위(2), 보(3), 종료(7)을 선택하세요.");
			    	System.out.print(">>");
				    int userChoice = scanner.nextInt();
				    int computerChoice = new Random().nextInt(1, 4); // [1,4): half-open
		//		    int computerChoice = new Random().nextInt(3) + 1;  // Ditto: 상동
				    
				    Utils.printToConsole("userChoice({0}), computerChoice({1})", userChoice, computerChoice);
				    
				    if(userChoice == computerChoice) {
				    	Utils.printToConsole("비겼습니다");
				    }else if( (userChoice == 1 && computerChoice == 3)||
				    		(userChoice == 2 && computerChoice ==1) ||
				    		(userChoice == 3 && computerChoice == 2)) {
				    	Utils.printToConsole("이겼습니다");
				    }else if( userChoice ==7) {
				    	Utils.printToConsole("이제 꺼볼게요");
				    	break;
				    }
				    else {
				    	Utils.printToConsole("졌습니다.");
				    } //  if- else 문
			    
			    }//while 문 
			}finally {
				if(scanner != null) {
					scanner.close();
				}
			}// finally 
		
		
	}//main
	
	
}// end class
