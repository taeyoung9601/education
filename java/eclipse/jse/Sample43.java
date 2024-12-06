
public class Sample43 {

	//목표 : 오버로딩된 다양한 생성자를 이용해서 객체를 생성하자!
	public static void main(String[] args) {
		//(1) 기본 생성자 만으로 객체 생성
//		Car car1 =new Car();
//		Utils.printToConsole("1. car1:"+ car1);
		
		//(2) 모델필드만 초기화 해주는 생성자로 객체 생성
		Car car2 = new Car("아반테");
		Utils.printToConsole("2. car2:", car2);
		
		//(3) 모델, 색상 2개 필드만 초기화 해주는 생성자로 객체 생성
		Car car3 = new Car("아반테","PINK");
		Utils.printToConsole("3. car3:", car3);
		
		//
		Car car4 = new Car("아반테","PINK", 130);
		Utils.printToConsole("4. car4:", car4);
		
		
	}// main
	
}// end class
