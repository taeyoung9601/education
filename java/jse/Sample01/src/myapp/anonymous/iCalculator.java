package org.zerock.myapp.anonymous;


public interface iCalculator {

	//강제규격 - 추상 메소드 2개 선언
	public abstract int add(int number1,int number2); // 덧셈 기능
	public abstract int sub(final int number1,final int number2); // 뺄셈 기능
	
	
	
}// end interface
