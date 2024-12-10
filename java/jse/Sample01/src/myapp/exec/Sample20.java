package org.zerock.myapp.exec;

public class Sample20 {

	public static void main(String[] args) {
		//주사위를 굴리는 행위 -> 무작위로 정수를 하나 구하는 것과 똑같다.
		//Math.random(); // 0.0 <= x < 1.0 [0.0,1.0) - Half open
		
		int eye = (int) (Math.random()*6)+1;
				
		if(eye == 1) {
			System.out.println("1");
		}else if(eye == 2){
			System.out.println("2");
		}else if(eye == 3) {
			System.out.println("3");
		}else if(eye == 4) {
			System.out.println("4");
		}else if(eye == 5) {
			System.out.println("5");
		}else {
			System.out.println("6");
		} // if-else if-else
				
	}//entry. main
	
}//end. class
