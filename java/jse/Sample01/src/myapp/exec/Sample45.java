package org.zerock.myapp.exec;

public class Sample45 {

	public static void main(String[] args) {
		
		House house1 = new House();
		Utils.printToConsole(++House.몇채);
		
		house1.보일러on();  //OK. 인스턴스 메소드
//		House.보일러on();   // XX, 모든 객체에 공유되는 메소드(기능)
							// 	   결국 이 메소드가 호출 가능하다면, 동시에
							//     모든 객체(집)의 보일러가 켜지는거죠..
		
		//================
		
		House house2 = new House();
		Utils.printToConsole(++House.몇채);
		
		
		//================
		
		House house3 = new House();
		Utils.printToConsole(++House.몇채);
		

	}

}
