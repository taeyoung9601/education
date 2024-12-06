import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
//@NoArgsConstructor

public class SamsungTV {

	static String company;   //제조회사명
	String model;
	
	
	static {  // 정적필드의 초기화만 하라!! => 인스턴스 필드는 건드리지말라!
		Utils.printToConsole("static initializer invoked.");
		
		//Cannot use this in a static context
//		this.company = "삼성전자;            // xx
		company = "삼성전자";				// oo
		
		//Cannot make a static reference to the non-static field model
//		model = "OLED";            // xx: 인스턴스 필드 사용 불가
		
		}// static initializer
	SamsungTV(){
		Utils.printToConsole("Default constructor invoked.");
	}
	
	void instanceMethod(){
		Utils.printToConsole("instanceMethod() invoked.");
	}
	
	static void staticMethod(){
		Utils.printToConsole("instanceMethod() invoked.");
		
		staticMethod();
	}
	
	
	
}// end class
