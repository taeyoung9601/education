package org.zerock.myapp.lambda;

@FunctionalInterface
public interface MyFunctionalInterface2 {
	
//	public abstract int sub(int number1, int number2); 	// 뺄셈연산 메소드
//	public abstract double mul(double number1, double number2);
//	public abstract double div(double number1, double number2);
	public abstract int mod(int number1, int number2);	
	
	
}// interface
