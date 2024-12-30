package org.zerock.myapp.Samples;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClazzExample {

	public static void main(String[] args) throws ClassNotFoundException{
		log.debug("main({}) invoked.",Arrays.toString(args));
		
		//Clazz 객체를 얻는 3가지 방법(반드시 연마할 것!)
		
		// 1 Person 참조타입(클래스)에 대한, Clazz 객체를 얻어보자!
		
		//1st. method with <기본/참조타입명>.class 를 이용하는 방법
		java.lang.Class<Person> personClazz1 = Person.class;
		log.info("\t+personClazz1:{}", personClazz1);
		
		// 2nd. method with <참조타입변수명>.getClass()를 이용하는 방법
		Person person = new Person("Tae", 23);
		java.lang.Class<? extends Person> personClazz2 = person.getClass();
		log.info("\t+personClazz2:{}", personClazz2);
		
		// 3rd. method with java.lang.Class.forName(FQCN) 정적 메소드 호출
		String FQCN = "org.zerock.myapp.Samples.Person";
		java.lang.Class<?> personClazz3 = Class.forName(FQCN); // 메소드 영역의 객체 획득
		log.info("\t+personClazz3:{}", personClazz3);
		
		assert personClazz1 == personClazz2;
		log.info("\t+personClazz1 == personClazz2 :{}", personClazz1 == personClazz2);
		
		assert personClazz3 == personClazz2;
		log.info("\t+personClazz3 == personClazz2 :{}", personClazz3 == personClazz2);
		
		//-------------------
		//기본타입에 대한 Clazz 객체를 얻어보자
		Class<Integer> intClazz = int.class;      	// int 타입에 대한 타입정보를 가지고있는 Clazz 객체 반환
		Class<Void> voidClazz = void.class;			// 메소드 리턴 타입으로 사용되는 void에 대한 Clazz 객체 반환

		
	} // main
	
} // end class


@ToString
@Getter
@Setter
@AllArgsConstructor
class Person{
	private String name;
	private int age;
}
