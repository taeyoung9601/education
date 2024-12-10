package org.zerock.myapp.libs;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;



/*
	 * 모델링 객체로부터, 무조건 속성과 행위를 추출할 수 있는 것은 아닙니다.
	 * 1. 어떤 객체는 업무 요건에 의해서, 속성만 추출해야 하는 경우도 있고,
	 * 2. 어떤 객체는 반대로 행위(기능)만 추출해야 하는 경우도 있고
	 * 3. 당연히 어떤 객체는 속성+행위(기능)을 모두 추출해야 하는 경우도 있습니다.
	 * 
	 * 따라서, 모델링 결과대로 선언하는 클래스도, 필드와 메소드가 있을 수도 있고
	 * 없을 수도 있음을 잊지 마세요.
	 */


//@RequiredArgsConstructor
@ToString
//@NoArgsConstructor
@AllArgsConstructor

public class Car {

	
	//(1) 고유속성
	String 제조회사 = "현대" ;
	
	/*@NonNull*/ String 모델;
	/*@NonNull*/ String 색상;
	/*final*/ int 최대속도;
	
	//(2) 상태속성
	int 현재속도;
	
	
	//(3) 부품속성 - 다른 클래스 타입의 필드로 나온다!
	Tire 타이어;
	
	//개발자가 클래스 선언 시, 명시적으로 생성자를 선언하지 않으면,
	//*자바 컴파일러가 누가 자동으로 소위 "Default Constructor(기본 생성자)"를 컴파일 시에
	//만들어 넣어줍니다. 
	// 이 때 자동으로 만들어 지는, 기본 생성자(Default Constructor(기본 생성자)는
	// 아래와 같은 규칙에 따라 만들어집니다:
	//(1) 당연히 생성자이니, 생성자 이름은 무조건 클래스명과 동일
	//(2) 생성자 매개변수는 하나도 없습니다.
	//(3) 생성자 블록(중괄호 블록)내에서 하는 일은 하나도 없습니다.
	//(4) 클래스 선언부에 만일 public 같은 키워드가 있으면, 이를
	//    기본 생성자에 그대로 붙입니다.
	
	//=> 개발자가 클래스 선언 시, 단 1개라도 명시적인 생성자를 선언함녀
	// 	 자바 컴파일러는 절대 생성자에 관해서 개입하지 않는다!!
	public Car(){  //Default Constructor	
		Utils.printToConsole("Car() invoked.");
		
	}
	
	Car(String 모델){        //Overloading
		// 다른 생성자 호출 => 중괄호 블록 안에서 반드시 가장 첫 번째 실행 문장이어야한다!
		this(모델,null); //다른 생성자에게 내가 초기화 할 필드의 초기화 위임!!
		
		Utils.printToConsole(String.format("Car(%s) invoked.", 모델));
//		this.모델 = 모델;  => this() 문법으로 다른 생성자 호출 => 중복 코드 제거
	}
	
	Car(String 모델,String 색상){
//		this();  // XX : 기본 생성자 호출로 돌아가면, 맡은 2개의 필드 초기화 할 수 없기 때문
		this(모델,색상,0); // 이 2개의 필드를 초기화 해줄 수 있는 다른 생성자에게 위임
		
		Utils.printToConsole(String.format("Car(%s,%s) invoked.",모델,색상));
		
//		this.모델 = 모델;
//		this.색상 = 색상;
		
	}
	
//	Car(String 모델,String 색상,int 최대속도){
////		this();  // XX : 기본 생성자 호출로 돌아가면, 맡은 2개의 필드 초기화 할 수 없기 때문
//		this(모델,색상,최대속도); // 이 2개의 필드를 초기화 해줄 수 있는 다른 생성자에게 위임
//		
//		Utils.printToConsole(String.format("Car(%s,%s,&s) invoked.",모델,색상,최대속도));
//		
////		this.모델 = 모델;
////		this.색상 = 색상;
//	}
	
}// end class
