package org.zerock.myapp.generic;

import java.util.Arrays;

import org.zerock.myapp.util.Utils;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class BoxExample {

	// 목표: 제네릭을 사용하지 않았을 때, 
	//           상자객체 안에, 사과객체를 만들어 넣는것!
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));

		Box box = new Box();
		box.set("Yoseph");				// 상자안에 문자열 객체 저장		
		
		Utils.printToConsole(box);
		Utils.printToConsole(box.get());

		// -------------------
		Utils.printToConsole("=".repeat(40));
		
		Apple apple = new Apple();
		box.set(apple);						// 상자안에 사과 객체 저장		
		
		Utils.printToConsole(box);
		Utils.printToConsole(box.get());		
		
		// -------------------
		Utils.printToConsole("=".repeat(40));
		
		box.set(23);		
		Utils.printToConsole(box);
		
		
	} // main

} // end class
