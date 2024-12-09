package project;

public class project {
	
	public static void main(String[] args) {
		int 사과 = 1;
		int 인원 = 7;
		int 조각갯수 = 10;
		float 조각크기 = (float) 사과/조각갯수;
		float 먹는사과 = 조각크기 * 인원;
		float 남는갯수 = 사과 - 먹는사과;
		
		double 더블사과 = 1;
		double 더블인원 = 7;
		double 더블조각갯수 =10;
		double 더블조각크기 = 더블사과/더블조각갯수;
		double 더블먹는사과 = 더블조각크기 * 더블인원;
		double 더블남는갯수 = 더블사과 - 더블먹는사과;
		
		System.out.println(인원);
		System.out.println(조각크기);
		System.out.println(먹는사과);
		System.out.println(남는갯수);
		System.out.println(더블조각크기);
		System.out.println(더블먹는사과);
		System.out.println(더블남는갯수);
	}
}
