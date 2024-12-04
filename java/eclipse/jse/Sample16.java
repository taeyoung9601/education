
public class Sample16 {

	//목표:NaN(Not-a-number)에 대해서 이해하자!
	//		어떤 상황에서 발생하는지와 어떻게 체크할 수 있는지
	public static void main(String[] arg) {
		
		int x = 5;
		double y = 0.0;
		
		//나머지는 , 먼저 나눗셈이 제대로 수행되어야,
		// 그 결과로 몫과 나머지가 나오는 것이고,
		// 이중에, 나머지를 되돌려주는 연산자가 %이죠.
		// 그런데, 지금 코드는 나눗셈을 수행하면 ,무한대가
		// 발생하기 때문에, 나머지를 주고 싶어도 도저히 줄 수가
		// 없는 상태 맞죠?!
		
		double z = x % y;  //나머지 연산
		if(Double.isNaN(z)) {
			System.out.println("Outlier로 NaN이 발생했습니다.");
			System.out.println("이후의 코드를 절대로 수행해서는 안됩니다.");
			
			return;
		}
		System.out.println(z);
	}//entry.main
	
}// end.class
