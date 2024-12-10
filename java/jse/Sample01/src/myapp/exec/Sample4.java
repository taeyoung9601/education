package org.zerock.myapp.exec;

public class Sample4 {
	
	//변수의 유효 범위
	public static void main(String[] args) {
		int v1 = 15;
		
		if(v1 > 10) {
			int v2;
			
			//모든 연산식은, 연산자의 피연산자들의 타입을 맞춰놓고 수행
			//피연산자 중에 큰 타입쪽으로, 
			
			v2 = v1 - 10; // 대입 연산자 : 무조건 좌측 (Lvalue)의 피연산자 타입으로 맞춤.
		}
		System.out.println(v2);
	}	
	
}