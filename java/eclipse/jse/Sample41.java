
public class Sample41 {

	public static void main(String[] args) {
		//아니, 생성자를 선언하지도 않았는데, 어떻게 생성자가 호출 가능하죠?
		Car car = new Car();   
//		car.타이어 = new Tire("한국타이어");
		
		Utils.printToConsole("car:"+car);
		
		//객체의 필드 값 모두 출력
		Utils.printToConsole(car.제조회사);
		Utils.printToConsole(car.모델);
		Utils.printToConsole(car.색상);
		Utils.printToConsole(car.최대속도);
		Utils.printToConsole(car.현재속도);
		Utils.printToConsole(car.타이어);
		
	}//main
	
}//end class.
