
public class Sample23 {

	// 목표: classical for 문 - 일반적으로 반복 횟수를 이미 아는 경우에 사용
	// 		 (1)이미 아는 경우란, 반복 횟수가 명시적으로 정해져 있어
	//		 (2)주어진 문제의 추론에 의해, 반복 횟수가 추정이 가능한 경우
	public static void main(String[] args) {
		
		//1 ~ 100 까지의 숫자를 더해보자!
		
		//중요 사항: 초기식에서 정의된 카운터 변수는 중괄호 블록 안에서
		// 사용(즉,읽기전용)하는 것은 괜찮으나, 카운터 변수의 값을 수정해서는 안된다!
		// 왜? 예측 불가능한 반복이 발생하기 때문에
		int sum =0;
		
		for(int i=1,j=1;i<=100;++i,j++) {
			// 카운터 변수를 사용하자! 하지만 절대 수정해서는 안된다!
			System.out.println("i="+ i + "j="+ j);
			sum += i;
		}
		// Classical for
		System.out.println(sum);
	}//entry. main
	
}// end. class

class TTT{
	;;;
}