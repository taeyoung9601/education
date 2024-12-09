package project;
import java.util.InputMismatchException;
import java.util.Scanner;

public class project2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int 짜장면 = 5500;
        int 짬뽕 = 6500;
        int 볶음밥 = 7000;

        String 메뉴;
        int 메뉴가격;

        System.out.println("안녕하세요 중국집입니다");

        do {
            System.out.println("짜장면, 짬뽕, 볶음밥 중 메뉴를 골라주세요.");
            System.out.print(">");

            메뉴 = scanner.nextLine();
            System.out.println("메뉴:" + 메뉴);
            
            
            메뉴가격 = switch (메뉴) {
                case "짜장면" -> 짜장면;
                case "짬뽕" -> 짬뽕;
                case "볶음밥" -> 볶음밥;
                default->{
                    System.out.println("없는 메뉴입니다. 다시 선택해 주세요.");
                    yield 0;
                }
            };
            
        } while (메뉴가격 == 0);

        System.out.println(
            "주문하신 메뉴는 " + 메뉴 + "입니다." + "\t" +
            "메뉴 가격은 " + 메뉴가격 + " 원입니다."
        );

        // ======================================================메뉴결정
        int 넣은금액 = 0;
        System.out.println("계산해드리겠습니다.");
        System.out.println("돈 주세요.(원)");
        int 누적금액 = 0;
        
        do {
        	try {
	            System.out.print(">");
	            넣은금액 = scanner.nextInt();
	            
	            if (넣은금액 % 10 > 0) {
	                System.out.println("일의 단위는 받지않습니다.");
	            } else {
	            	누적금액 += 넣은금액;
	            	if ( 메뉴가격> 누적금액) System.out.println(메뉴가격-누적금액 + "원 더 주세요.");
	            } 
        	} catch(InputMismatchException e) {
        		System.out.println("숫자만 입력해주세요.");
//        		System.out.println("e: "+e);
                scanner.nextLine(); // 잘못된 입력을 제거
        	}
        	
        } while (넣은금액 % 10 > 0 || 메뉴가격 > 누적금액);

        int 잔돈 = 누적금액 - 메뉴가격;
        System.out.println("잔돈은 " + 잔돈 + " 입니다.");
        
        int 천원갯수 = 잔돈 / 1000;
        int 오백원갯수 = 잔돈 % 1000 / 500;
        int 백원갯수 = 잔돈 % 500 / 100;
        int 오십원갯수 = 잔돈 % 100 / 50;
        int 십원갯수 = 잔돈 % 50 / 10;
        System.out.println(
            "잔돈:" + "\t" +
            "1000원갯수: " + 천원갯수 + "\t" +
            "500원갯수: " + 오백원갯수 + "\t" +
            "100원갯수: " + 백원갯수 + "\t" +
            "50원갯수: " + 오십원갯수 + "\t" +
            "10원갯수: " + 십원갯수 + "\t"
        );
        System.out.println("주문하신 " + 메뉴 + " 나왔습니다.");
        
    } // main
} // class