import java.util.Scanner;

public class project2 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int menu1 = 2030;
		int menu2 = 3520;
		int menu3 = 4730;
		
		String 메뉴;
		int 메뉴가격 = 0;
		
		do {
			System.out.println("메뉴를 골라주세요.");
			System.out.print(">");
			
			메뉴 = scanner.nextLine();
			System.out.println("메뉴:"+ 메뉴);
			
			switch(메뉴) {
			case "menu1": 메뉴가격 = menu1; break;
			case "menu2": 메뉴가격 = menu2; break;
			case "menu3": 메뉴가격 = menu3; break;
			default : System.out.println("없는 메뉴입니다. 다시 선택해 주세요.");
			}
			
			
		}while(!(메뉴.equals("menu1")||메뉴.equals("menu2")||메뉴.equals("menu3")));
		
		System.out.println("메뉴:"+ 메뉴 + "메뉴가격" + 메뉴가격);
		
		
		int 넣은금액;
		System.out.println("돈주세요");
		System.out.print(">");
		넣은금액 = scanner.nextInt();
		
		while(메뉴가격 > 넣은금액) {
			
			int 부족한금액 = 메뉴가격 - 넣은금액;
			System.out.println( 부족한금액 + "원 더 넣어라");
			메뉴가격 = 부족한금액;
			System.out.println("돈주세요");
			System.out.print(">");
			넣은금액 = scanner.nextInt();
			
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
		
		
		
		System.out.println("주문메뉴나옴");
		
	}// main
	
} // end. class