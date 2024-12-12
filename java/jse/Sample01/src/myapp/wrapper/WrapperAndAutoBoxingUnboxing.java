package org.zerock.myapp.wrapper;

import lombok.extern.log4j.Log4j2;

@Log4j2

public class WrapperAndAutoBoxingUnboxing {

	//목표 : Wrapper 타입의 활용과 오토박싱/언박싱의 이해와 응용
	public static void main(String[] args) {
		
		// 오토박싱: 자동으로 기본 타입 값 -> Wrapper 타입의 객체로 만들어주는 것
		Byte byteWrapper = 100;    
		log.info("1.byteWrapper:{}",byteWrapper);
		// 오토언박싱 : Wrapper 타입 객체 -> 대응되는 기본 타입 값으로 만들어주는 것
		byte byteVal = byteWrapper;
		log.info("2.byteVal:{}", byteVal);
		//-------------------------
		Integer intWrapper = 1000;    //Auto- boxing
		int intVal = intWrapper;	  //Auto- unboxing	
		log.info("1.intWrapper:{}",intWrapper);
		log.info("2.intVal:{}", intVal);
		//-----------------
		Short shortWrapper = 370;
		short shortVal = shortWrapper;
		log.info("1.shortWrapper:{}",shortWrapper);
		log.info("2.shortVal:{}", shortVal);
		//-------------------
		Long longWrapper = 1000000000L;
		long longVal = longWrapper;
		log.info("1.longWrapper:{}",longWrapper);
		log.info("2.longVal:{}", longVal);
		//---------------------
		Float floatWrapper = 3.333333F;
		float floatVal = floatWrapper;
		log.info("1.floatWrapper:{}",floatWrapper);
		log.info("2.floatVal:{}", floatVal);
		//---------------------
		Double doubleWrapper = 3.33333333333333333;
		double doubleVal = doubleWrapper;
		log.info("1.doubleWrapper:{}",doubleWrapper);
		log.info("2.doubleVal:{}", doubleVal);
		//----------------------
		Boolean booleanWrapper = true;
		boolean booleanVal = booleanWrapper;
		log.info("1.booleanWrapper:{}",booleanWrapper);
		log.info("2.booleanVal:{}", booleanVal);

	}// main

}// end class
