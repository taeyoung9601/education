import java.util.Scanner;

public class project2 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int 짜장면 = 5500;
		int 짬뽕 = 6500;
		int 볶음밥 = 7000;
		
		String 메뉴;
		int 메뉴가격 = 0;
		
		do {
			System.out.println("안녕하세요 중국집입니다");
			System.out.println("메뉴를 골라주세요.");
			System.out.print(">");
			
			메뉴 = scanner.nextLine();
			System.out.println("메뉴:"+ 메뉴);
			
			switch(메뉴) {
			case "짜장면": 메뉴가격 = 짜장면; break;
			case "짬뽕": 메뉴가격 = 짬뽕; break;
			case "볶음밥": 메뉴가격 = 볶음밥; break;
			default : System.out.println("없는 메뉴입니다. 다시 선택해 주세요.");
			}
			
			
		}while(!(메뉴.equals("짜장면")||메뉴.equals("짬뽕")||메뉴.equals("볶음밥")));
		
		System.out.println(
				"주문하신 메뉴는 "+ 메뉴 +"입니다." + "\t" 
				+ "메뉴 가격은 " + 메뉴가격 + " 원입니다."
		);
		
		// ======================================================메뉴결정
		int 넣은금액;
		System.out.println("계산해드리겠습니다.");
		System.out.print(">");
		넣은금액 = scanner.nextInt();
		
		while(넣은금액 % 1000 % 100 % 10 > 0) {
			
			System.out.println("일의 단위는 받지않습니다.");
			System.out.print(">");
			넣은금액 = scanner.nextInt();
		} 
		
		while(메뉴가격 > 넣은금액) {
			
			int 부족한금액 = 메뉴가격 - 넣은금액;
			System.out.println( 부족한금액 + "원 더 주세요.");
			메뉴가격 = 부족한금액;
			System.out.print(">");
			넣은금액 = scanner.nextInt();
			while(넣은금액 % 10 > 0) {
				
				System.out.println("일의 단위는 받지않습니다.");
				System.out.print(">");
				넣은금액 = scanner.nextInt();
			}
			
		} // end.while
		
		int 잔돈 = 넣은금액 - 메뉴가격;
		int 오백원갯수 = 잔돈 / 500;
		int 백원갯수 = 잔돈 % 500 / 100;
		int 오십원갯수 = 잔돈 % 500 % 100 / 50;
		int 십원갯수 = 잔돈 % 500 % 100 % 50 / 10;
		System.out.println(
				"잔돈:" +
				"500원갯수:"+오백원갯수+ "\t" +
				"100원갯수:"+백원갯수+"\t"+
				"50원갯수:"+오십원갯수+"\t"+
				"10원갯수:"+십원갯수+"\t" 
		);
		System.out.println("주문하신"+ 메뉴 + "나왔습니다.");
		
		
	}// main
	
} // end. class