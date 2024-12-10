package org.zerock.myapp.examples;

import java.util.Random;
import java.util.Scanner;

import org.zerock.myapp.libs.Utils;

public class GuseeTheNumber {

	// 목표: 숫자 맞추기
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		
		//Step1. 컴퓨터가 자신의 임의의 숫자를 하나 정합니다.
		int computerNumber = random.nextInt(1, 100+1); // [1,101) : Half-open 
//		Utils.formatToConsole("computerNumber({0})", computerNumber);
		
		//Step2. 게임에 필요한 변수를 정의(또는 선언)합니다.
		int guess;					// 여러분이 생각한 숫자를 저장할 변수
		int attempts = 0;			// 몇번만에 맞추었는지를 보여주기 위한 변수(시작값:0)
		
		//Step3. 게임을 시작할 메시지(=이를 프롬프트, prompt라고 하죠. 입력을 유도하는 메시지 총칭)
		Utils.printToConsole("(1 - 100) 까지의 범위 중에 하나의 숫자를 맞춰보세요!");
		
		//Step4. 컴퓨터가 정한 숫자를 맞추기까지 계속 반복 수행
		try {
			
			do {
				Utils.printToConsole("숫자를 입력하세요");
				System.out.print(">>");
				
				guess = scanner.nextInt();
				++attempts;
				
				if(guess > computerNumber) {
					Utils.printToConsole("입려한 숫자보다 낮습니다");
				}else if (guess < computerNumber) {
					Utils.printToConsole("입력한 숫자보다 높습니다.");
				}else {
					Utils.formatToConsole("정답입니다.(guess:{0},computerNumber:{1}, attempts:{2})", guess, computerNumber, attempts);
				}
				
			}while(computerNumber != guess);
			
		}finally {
		Utils.printToConsole("Done");
		scanner.close();
		}
		
	}// main
	
}//end class
