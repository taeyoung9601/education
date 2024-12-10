package org.zerock.myapp.exec;

public class Sample11 {
	
	//실수 타입의 변수 선언과 이 때 주의할 점에 대해서 알자!
	public static void main(String[] args) {
		
		double var1 = 3.14159;	//F가 없으니, 실수 리터럴의 타입은 double(기본)
		float var2 = 3.14;
		float var3 = 3.14159F;  //F가 붙었으니, 실수 리터럴의 타입은 float
		
		double var4 = 0.1234567890123456789;  // double 타입의 실수 리터럴
		float var5 = 0.1234567890123456789F;	// float 타입의 실수 리터럴
		
		System.out.println(var1);
		System.out.println(var3);
		System.out.println(var4);
		System.out.println(var5);
		
	}
}
