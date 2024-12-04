
public class Sample12 {

	//목표: 형 변환 중, 자동 형 변환
	public static void main(String[] args) {
		byte byteValue = 10;
		
		int intValue = byteValue;
		System.out.println(intValue);
		
		//---------
		char charValue = '가';  //유니코드 : 44032
		intValue = charValue;
		System.out.println(intValue);
		
		//---------- 작은 타입 -> 큰 타입
		intValue = 500;
		long longValue = intValue;
		System.out.println(longValue);
		
		
		
	}// entry.main
	
}// end.class
