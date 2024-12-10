package org.zerock.myapp.B;

import org.zerock.myapp.A.A;
import org.zerock.myapp.libs.Utils;

//import org.zerock.myapp.A.B;  // XX : The type org.zerock.myapp.A.B is not visible 

public class C {
		//(1) 고유속성
		//(2) 상태속성
		//(3) 부품속성
		
//		B b;  // XX: B cannot be resolved to a type
		
	A a;

	public static void main(String[] args) {
		
		A a = new A(); // OK: A 클래스는 public 이니까..
		
		// XX : The field A.name is not visible
		// XX : The field A.age is not visible
		Utils.printToConsole(a.name, a.age);
		
	}
	
} // end class
