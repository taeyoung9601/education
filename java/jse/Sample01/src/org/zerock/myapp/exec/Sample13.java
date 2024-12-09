package org.zerock.myapp.exec;

public class Sample13 {
	
	//목표 : 강제 형 변환 - 큰 타입 -> 작은 타입으로 변환
	public static void main(String[] args){
		int intValue = 44032;  // '가' 문자 리터럴의 유니코드 값
		
		//----------------
		char charValue = (char)intValue; // Lvalue (char,2) = Rvalue(int, 4)
		System.out.println(charValue);  // 무손실 강제 형 변환
		
		//-----------------
		long longValue = 299299900;
		intValue = (int)longValue;
		System.out.println(intValue); // 무손실 강제 형 변환
		
		//-----------------   // double(8)
		double doubleValue = 3.14159;
		
		//실수 -> 정수로 변환하겠다! 란 의미는
		//결국, 실수의 소숫점 이하 자릿수를 모두 버리겠다!!
		// (절삭, Truncation => 반올림도 내림도 아니고 그냥 잘라내는 것)
		
		// 주의사항 : 아래 코드가 대표적으로, 실수의 소숫점 자리수를 버리고, "정수"로 바꾸는 대표적인 코드
		intValue = (int)doubleValue;
		System.out.println(intValue);    // 손실 강제 형 변환
	}// entry.main
	
}// end.class
