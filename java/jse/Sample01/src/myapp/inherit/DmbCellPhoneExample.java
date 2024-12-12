package org.zerock.myapp.inherit;

import java.util.Arrays;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString

@Log4j2
public class DmbCellPhoneExample {

	//목표: 상속 관계의 자식 클래스로부터, 자식 객체를 생성해보고,
	//		이 때 아래의 "대전제"대로 되는지 확인 해보자!
	// *대전제: 상속 관계의 자식 객체 생성시, 무조건 부모 객체부터 먼저 생성됨
	//			(오해마실건, 부모 객체의 인스턴스 필드까지 초기화된다는 의미는 아님)
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));

		//1. 자식 객체 생성
		DmbCellPhone child = new DmbCellPhone(9, "Galaxy","Black");
		log.info("\t+child:{}", child);
		
		
		
		
	}// main

}// end class
