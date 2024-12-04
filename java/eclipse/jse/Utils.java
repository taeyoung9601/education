//package org.zerock.myapp;

public class Utils {

//	public static void main(String[] args) {
//		String text = "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
//		
//		printToConsole(text);
//		printToConsole(text);
//		printToConsole(text);
//	}
//	
	public static void printToConsole(String s) {
		int delay = 10;

        for (char c : s.toCharArray()) {
            if (c == ' ') System.out.print(" ");
            else System.out.print(c);
            
            try { Thread.sleep(delay); } catch (InterruptedException e) {;;}
        }
        
        System.out.print('\n');
	}

}