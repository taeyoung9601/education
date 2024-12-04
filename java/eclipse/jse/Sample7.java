
public class Sample7 {

	//목표: Garbage(=소위, 쓰레기 값)에 대해서 
	public static void main(String[] args) {
		byte var1 = 125;
		int var2 = 125;
		
		for(int i=0;i<5;i++) {
			++var1;
			++var2;
			
			// + 연산자는 3가지가 있습니다:
			// 1. 산술 연산자로 덧셈
			// 2. 부호 연산자
			// 3. 문자열 연결 연산자(String concatenation operator)
			System.out.println("var1:" + var1 + "\t" + "var2:" +var2);
		}
		
		//위의 for문이 끝난 이후로는, 더 이상 var1, var2 변수가
		// 프로그램이 끝날 때까지 전혀 사용되지 않고 있습니다.
		// 이렇게 프로그램이 실행되는 동안, 어느 시점부터 더 이상 사용되지 않는
		// 변수의 값을 가비지(Garbage,쓰레기)라고 합니다.
		// 이 가비지는 반드시 청소(clean up)되어야 한다!
	}
}
