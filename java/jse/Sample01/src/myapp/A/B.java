package org.zerock.myapp.A;

import org.zerock.myapp.libs.Utils;

// 클래스는 오로지 public or default 접근 제한자만 붙일 수 있다!
class B {

	//같은 패키지에 있는 B클래스에서, A객체의 필드에 접근(사용) 
	public static void main(String[] args) {
		A a = new A();
		Utils.printToConsole(a.name, a.age);
	}
	
}// end class
