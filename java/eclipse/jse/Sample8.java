
public class Sample8 {

	public static void main(String[] args) {
		
		//문자 1개를 저장한다고, 진짜 그 문자가 저장되는게 아니라,
		//문자 1개에 매핑된 정수 코드 값을 저장하는 타입! -> 그래서 정수 타입 중 하나
		char c1 = 'A';	//문자 리터럴의 기본 타입: char(2)
		char c2 = 65;	// 여기서, 65는 ASCII 코드 테이블에서 찾을 수 있는
						// 대문자  'A'에 대한 정수 코드 값 => 문자 집합(Character Set)
		char c3 = '\u0041';  //문자에 매핑된 정수 코드 값을
							// 16진수로 넣으실 때에는, 여기처럼, '\u0000'형식으로
							// 넣습니다. 여기서,  Unicode 문자 집합 의미
		
		char c4 = '가';
		char c5 = 44032;
		char c6 = '\uac00';
		
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(c5);
		System.out.println(c6);
	}
}
