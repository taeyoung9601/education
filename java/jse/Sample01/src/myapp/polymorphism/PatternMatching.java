package org.zerock.myapp.polymorphism;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PatternMatching {

	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
//		People obj = new People();
//		log.info("\t1.obj:{},type:{}",obj, obj.getClass().getName());
//		obj = new Student();
//		log.info("\t2.obj:{},type:{}",obj, obj.getClass().getName());
//		//...
//		
//		log.info("\t3.obj instance of People:{}",obj instanceof People);
//		log.info("\t4.obj instance of Student:{}",obj instanceof Student);
//		
//		

//		obj = new AirPlane();    // xx : 상속관계 아님.
//		log.info("\t3.obj:{}",obj);
		
		// 다형성-1 : 단순 부모의 자식 타입만 대입가능한게아니라,
		//			  상속의 계층  구조에 들어가 있다면, 다 대입 가능한 성질입니다.
		People people = new Student();
		
		
		people = new Worker();
		people = new HighStudent();
		people = new MiddleStudent();
		
		if(people instanceof Worker worker) {
			log.info("-----------1-------:{}",worker);
		}else if (people instanceof MiddleStudent middleStudent) {
			log.info("-----------2-------:{}",middleStudent);
		}else if (people instanceof HighStudent highStudent) {
			log.info("-----------3-------:{}",highStudent);
		}
		
		
		
		
//		people = new Airplane();  // xx : 다형성-1 위배
		
		
		

	}// main

}// end class


enum Gender {MALE, FEMALE}

class People {}
class Student extends People{}
class HighStudent extends Student{}
class MiddleStudent extends Student{}
class Worker extends People{}




