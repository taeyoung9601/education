import java.util.Arrays;

public class Sample44 {

	public static void main(String[] args) {
		Utils.printToConsole("main(%s) invoked.".formatted(Arrays.toString(args)));
		
		Calculator calc = new Calculator();
		
		calc.powerOn();
		
		//사칙연산 메소드가 모두 'Instance Method' 일 떄
		//
		
//		Utils.printToConsole(calc.add(10,20));
//		Utils.printToConsole(calc.minus(10,20));
//		Utils.printToConsole(calc.곱셈(10,20));
//		Utils.printToConsole(calc.나눗셈(10,20));
		
		//사칙연산 메소드가 모두 'Static Method' 일 떄
		// 사용법: 참조 타입명.정적메소드()
		
		Utils.printToConsole(calc.add(10,20));
		Utils.printToConsole(calc.minus(10,20));
		Utils.printToConsole(calc.곱셈(10,20));
		Utils.printToConsole(calc.나눗셈(10,20));
		
		calc.powerOff();
		
		
	}
}//end class
