package org.zerock.myapp.exec;

public class Sample6 {

	//Goal: 정수 타입인 1바이트 크기의 byte에 배우자
	public static void main(String[] args) {
		byte var1 = -128;
		byte var2 = -30;
		byte var3 = 0;
		byte var4 = +30;
		byte var5 = +127;
//		byte var6 = +128;   // xx, 변수의 도메인을 위반할 때에는 , 리터럴 그대로 넣지 못함
		//변수의 타입은 2가지를 결정한다:
		//1. 저장 가능한 값의 유형( 정수냐!,실수냐!)
		//2. 저장 가능한 값의 범위 (이를 변수의 "도메인(Domain)"이라 부름)
		
		System.out.println(var1);
		System.out.println(var2);
		System.out.println(var3);
		System.out.println(var4);
		System.out.println(var5);
	}
}
