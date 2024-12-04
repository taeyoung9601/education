
public class Sample39 {

	public static void main(String[] args) {
		Week today = Week.TEMPDAY;
		
		//Case1. Using classical switch statement.
		switch(today) {
			case MONDAY: 
			case TUESDAY: 
			case WEDNESDAY: 
			case THURSDAY:
			case FRIDAY:
				Utils.printToConsole("Do train hardly!");
				break;
			case SATURDAY:
			case SUNDAY:
				Utils.printToConsole("Boy's be ambious");
			default:
				break;
			
		} // switch Case1
		
		
		//Case2. Using switch expression
		// (1) Break Through 현상 -> case 의 쉼표로 구분된 매칭 목록으로 변경
		// (2) 각 case의 끝인 콜론(:)이 
		switch(today) {
			case MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY -> {
				Utils.printToConsole("Do train hardly!");
			}
				
			case SATURDAY,SUNDAY -> {
				String name = "Tae";
				int age = 26;
				
				Utils.printToConsole("2.name:%s, age:%s".formatted(name,age));
				Utils.printToConsole("Boy's be ambious");
			}
			default -> { // TEMPDAY
				Utils.printToConsole(today.name());
			}  //optional: 나올수도 있고, 필요없으면 안나와도 그만!
				
		} // switch Case2 end

		// (1) 표현식으로서 사용되는 경우
		String name = "Tae";
		
		int age = 26;
		// default 를 가져야함
		int result =switch(name) {
			case "Trinity" ->{
//				return 1;              // xx : 값을 반환하는 키워드로 return을 사용하지 못함.
				yield 1;
			}
			case "Young" -> 
		
				2;
			
			case "Tae" ->{
//				return 3;
				yield age;
			}
			default -> {              // 필수: 값을 생성 및 반환해야 하는 입장에서는
				yield 100;				// 어떠한 경우든 (즉, 위의 모든 case와 일치하지 않는 경우
			}							// 값을 생성/ 반환해야 하기 때문에, 이 case가 반드시 필요합니다.
		}; // switch expression
		
		System.out.println(result);
		
	}// main

}//end. class
