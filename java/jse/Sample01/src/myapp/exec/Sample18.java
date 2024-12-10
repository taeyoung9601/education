package org.zerock.myapp.exec;

public class Sample18 {

	public static void main(String[] args) {
		int score = 85;
		
		if(score>=90) {
			Utils.printToConsole("A");
		}else if(score>=80) {
			System.out.println("B");
		}else if(score>=70) {
			System.out.println("C");
		}else {
			System.out.println("D");
		}
		
	} //entry. main
	
} // end. class
