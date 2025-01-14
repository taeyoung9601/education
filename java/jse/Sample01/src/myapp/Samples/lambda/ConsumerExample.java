package org.zerock.myapp.lambda;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.ObjIntConsumer;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConsumerExample {

	// 목표 : 가장 많이 사용하는 표준 함수적 인터페이스 중에,
	// 		  매개변수로 데이터(객체)를 하나 받고, 소비하는 비지니스로직 구현에 사용되는
	// 		  Consumer 함수적 인터페이스의 사용용도를 알자!
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		//--1
		//		void (T t);
		Consumer <String> consumer =  s -> log.info("{}8",s);
		consumer.accept("JAVA");
		
		
		//-- 2
//	    void accept(T t, U u);
		BiConsumer<String,String> biConsumer = (s1,s2) -> log.debug("{}", s1 +s2);
		biConsumer.accept("JAVA", "21");
		
		//--3
//	    void accept(double value);
		DoubleConsumer doubleConsumer = d -> log.debug("PI : {} ", d);
		doubleConsumer.accept(3.14159);
		
		//--4
//		void accept(T t, int value);
		ObjIntConsumer<String> objIntConsumer = (name, age) -> log.debug("name: {}, age : {}" , name, age);
		objIntConsumer.accept("Tae", 27);
	}
	
}
