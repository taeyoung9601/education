package org.zerock.myapp.exec;

public class Sample15 {
	
	//목표: 이상치(비정상적인 값), 무한대 , Nan가
	// 발생하는 경우와, 런 타임시에 어떻게 변수의 값이
	// 이상치가 발생했는지를 알 수가 있는지 방법을 배웁니다.
	public static void main(String[] args) {
		int x = 5;
		double y = .0;  // 0.0 == .0
		
		//모든 숫자 계산은, 가능하면 정수로 변환해서 계산하고
		// 그 결과를 다시 실수로 역변환하는 과정으로 하셔야한다!!
		double z = x/y;
		System.out.println("1.z:" + z);
		
		if(Double.isInfinite(z)) {  // z변수 값이 무한대인 경우에 해당
			System.out.println("이상치가 발생하였습니다!!");
			System.out.println("더이상의 계산은 하시면 안됩니다!!");
			
//			return;
		}
		
		
		//----------
		//어느 숫자를 정수 0으로 나누는 경우
		// 수학에서는 분자를 정수0(분모 값)으로 나누는 경우는 없습니다.
		int temp =100;
		int result = temp / 0;
		System.out.println("2.result:"+ result);
		
	} // entry.main
	
} // end.class
