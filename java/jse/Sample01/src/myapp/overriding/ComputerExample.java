package org.zerock.myapp.overriding;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2


public class ComputerExample {

	
	// 이번 예제가 다형성을 이해하는데 아주 중요합니다.
	// 사실 지금 배우시는 것이, "다형성-1"이라고 합니다.
	// 참고로, 다형성은 "다형성-1 + 다형성-2" 로 구현됩니다.
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		final int r = 10;
		
		//자식 객체 생성: "부모 없는 자식 없다! 자식이 부모보다 먼저 태어나지 못한다"
		
		//(1) 부모 객체 생성 & 원의 면적 계산 => 문제: 정밀도가 약간 떨어짐
		Calculator calc = new Calculator();  //부모 객체
		log.info("\t1.calc:{}",calc);
		log.info("\t2.calc.areaCircle({}):{}",r, calc.areaCircle(r));
		
		//(2) 자식 객체 생성 & 원의 면적 다시 계산 => 재 정의된 메소드 호출
		Computer computer = new Computer();
		log.info("\t3.computer:{}",computer);
		log.info("\t4.computer.arearCircle({}):{}",r,computer.areaCircle(r));
		
		
		//(3) **다형성-1 : 상속관계(전제조건)에서는 부모 타입의 참조변수에는, 모든 자식/후손타입의 객체가 대입가능하다!!
		//		가. (대전제) 모든 연산자는, 피연산자의 타입을 반드시 동일하게 맞추어 놓고 연산
		//		나. 형변환 : a. 자동(promotion) 형변환: 작은 타입 -> 큰 타입으로 변환
		//					 b. 강제(casting) 형변환: 큰 타입 -> 작은 타입으로 쪼개서 변환(데이터손실)
		
		// 부모 (상위)클래스: Calculator
		// 자식 (하위)클래스: Computer
		Calculator calculator = new Computer();              // 다형성 1
		// 부모타입          >  자식타입
		log.info("\t+calculator:{}", calculator);
		
		//myName() 메소드는, 자식객체에서 재정의하지 않았고 
		// 참조변수는 부모타입이기 때문에, 부모타입에 선언된 메소드가 호출됨
		// 즉, 아무리 다형성-1이 만족된 상태라고 해도, 호출한 메소드가 
		// 자식타입 객체에서 재정의된 메소드가 아니라면, 참조타입변수의
		// 타입대로 부모클래스에 선언된 메소드가 호출됩니다.
		log.info("\t5.calculator.myName():{}",calculator.myName());
		
		//(4) **다형성-2 : 다형성-1이 성립된 상태를 전제로, 자식객체의 재정의된 메소드가 무조건 호출된다.
		
		double area = calculator.areaCircle(r);   // r= 10
		log.info("\t1.area:{}",calculator.areaCircle(r));     
		
		
		Calculator calculator2 = new Calculator();
		log.info("\t2.area:{}",calculator2.areaCircle(r));    // 다형성 관련 x = 그냥 부모 호출
		
		Computer computer2 = new Computer();
		log.info("\t3.area:{}",computer2.areaCircle(r));    // 다형성 관련 x = 그냥 자식 호출
		
		

	}// main

}//end class
