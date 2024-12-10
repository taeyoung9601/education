package org.zerock.myapp.singleton;


// 목표: JVM 내에서 오직 무조건 단 1개의 객체만을 생성할 수 있는
// 		 클래스를 선언하는 설계 패턴을 "디자인 패턴" 이라 하고,
// 		 여러 디자인 패턴중에 , Singleton Pattern 이 이 목표에 해당합니다.
public class Singleton {
	
	//클래스 안에서 new 연산자 + 생성자로 객체 생성
	private static Singleton obj = new Singleton(); // 정적 필드: 객체와는 무관하다! (공용)
	
	//외부 클래스에서는, 절대 객체를 생성할 수가 없게 막아버렸다!
	private Singleton() {} // 외부 클래스에서 접근 불가하게 생성자 선언
		
	// 외부에서 언제든 원하면, 이 단 한번 생성된 싱글톤 객체를 필요로 할 경우
	// 객체를 반환해주는 공개된 메소드를 선언
	public static Singleton getInstance() { // 정적 메소드: 모든 객체에서 사용가능한 공용
		return Singleton.obj;
	}
	
}// end class
