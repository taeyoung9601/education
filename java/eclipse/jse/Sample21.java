
public class Sample21 {

	//목표: switch 문에 대해서 이해하고 응용하자! (JS언어와 100% 일치- 문법)
	//		(다중 if문은, 조건식이 동등 비교하는 경우에는, switch문으로 대체가 가능)
	public static void main(String[] args) {
		int diceNumber = (int)(Math.random() *6) +1;
		diceNumber =7;
		
		switch (diceNumber){
		// Break through 현상 - 각 case 에 break 문이 없을 때, 밑으로 계속 밀고 내려가는 현상.
		
		case 1: System.out.println("1"); break;
		case 2: System.out.println("2"); break;
		case 3: System.out.println("3"); break;
		case 4: System.out.println("4"); break;
		case 5: System.out.println("5"); break;
		case 6: System.out.println("6"); break;
		
		default: Utils.printToConsole("Unknown dice number:" + diceNumber); break;
		}
		
	}// main
	
}// end. class
