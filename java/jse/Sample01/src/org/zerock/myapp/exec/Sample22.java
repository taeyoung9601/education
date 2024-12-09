package org.zerock.myapp.exec;

public class Sample22 {

	public static void main(String[] args) {
		int score = (int)(Math.random()* 21)+81;
		
		if(score >=90) {  //A등급 이상
			
			if(score>=95) {
				System.out.println("A+");
			}else {
				System.out.println("A");
			}// inner if-else
			
		}else {  // B등급 이상
			
			if(score>=85) {
				System.out.println("B+");
			}else {
				System.out.println("B");
			}// Nested if-else
			
		}//outer if-else
		
		
	}// entry. main
	
}// end. class
