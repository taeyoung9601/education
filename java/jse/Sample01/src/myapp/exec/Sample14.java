package org.zerock.myapp.exec;

public class Sample14 {
	
	
	//목표 : 안전한 강제 형 변환 수행
			// 
			// 데이터의 손실 발생여부를 체크한 후에,
			// 무손실이면 => 강제 형 변환 수행,
			// 손실이면 -> 의사 결정자의 결정대로 수행
	
	public static void main(String[] args) {
		int intValue = -600;
		
		//int(4) > byte(1) 변환
		//변환: 안전한(즉, 손실이 없는) 강제 형 변환 필요
		// 사전체크: 데이터의 손실이 있는지/없는지 확인
		if(intValue< Byte.MIN_VALUE || intValue> Byte.MAX_VALUE) {
			System.out.println("손실이 발생할 수 있습니다.");
			System.out.println("그러니, 의사 결정을 해주세요.");
		}else {  //byte 타입의 domain 안에 들어있는 값이다! -> 무손실
			byte byteValue = (byte)intValue;
			System.out.println(byteValue);
		}
	}// entry.main
	
}// end.class
