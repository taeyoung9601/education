package org.zerock.myapp.generic;

import java.util.Arrays;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString(callSuper = true)
@Log4j2

public class WildcardExample {

	//목표: 제네릭에서 구체 타입으로 지정 가능한, 와일드 카드타입인 '?' 기호의
	//		용법과 Rounded 타입 파라미터(범위가 제약된 타입 파라미터)를 함께
	//		이해하자!
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		//Step1. 모든 사람이 지원 가능한 과정(Course)를 생성하고 이용.
		Course<Person> course1 = new Course<>("일반인", 5); // <>: 타입추론 연산자
		course1.addApplicant(new Person("일반인-1"));
		course1.addApplicant(new Worker("근로자-1"));
		course1.addApplicant(new Student("학생-1"));
		course1.addApplicant(new HighStudent("고등학생-1"));
		course1.addApplicant(new HighStudent("고등학생-2"));
		log.info("\t1. course1:{}", course1);
		
		
		//Step2. 근로자만 지원가능한 과정 생성, 지원자도 추가
		Course<Worker> course2 = new Course<>("근로자과정", 5);
		course2.addApplicant(new Worker("근로자-1"));
//		course2.addApplicant(new Student("학생-1"));  // 상속관계가 아님 -> 다형성- 1 적용안됨
		log.info("\t2.course2:{}", course2);
		
		
		//Step3. 학생들만 지원 가능한 과정 생성, 지원자도 추가
		Course<Student> course3 = new Course<>("학생과정", 5);
		course3.addApplicant(new Student("학생-1"));
		course3.addApplicant(new Student("학생-2"));
		course3.addApplicant(new HighStudent("고등학생-3"));
		
		log.info("\t3.course3:{}", course3);
	
		
		//Step4. 고등학생만 지원가능한 과정 생성 및 지원자 추가
		
		Course<HighStudent> course4 = new Course<>("고등학생과정", 5);
		course4.addApplicant(new HighStudent("고등학생-1"));
		course4.addApplicant(new HighStudent("고등학생-2"));
		course4.addApplicant(new HighStudent("고등학생-3"));
		
		log.info("\t4.course4:{}", course4);
	
		
		//Step5. 이후로는 이 실행클래스에, 제네릭 와일드카드가 적용한 메소드를
		//		 선언하겠습니다. 이 메소드의 용도는 위에서 만든 과정을 교육 시스템에
		//		 등록하는 메소드입니다.
		
		WildcardExample.registerCourse(course1);  // course1: Person 대상과정
		
		//왜? 과정등록 메소드의 매개변수 타입인, Course<T>는 전혀 상속관계가 없는
		// 단순 제네릭 타입 클래스 일뿐, 그 이상도/ 이하도 아니다!!
		// 즉, 상속계층구조를 이용한 다형성-1은, Course<T> 제네릭 클래스에서는
		// 전혀 찾아볼 수가 없다. 왜? 그냥 클래스일뿐이라..
		WildcardExample.registerCourse(course2);  // course2: Worker 대상과정
		WildcardExample.registerCourse(course3);  // course3: Student 대상과정
		WildcardExample.registerCourse(course4);  // course4: HighStudent 대상과정
	}// main
	
	
	//모든 대상의 과정을 교육부 시스템에 등록하는 메소드
	// 매개변수의 타입이 Course<Person>으로 되어 있어서, 일반인 과정만
	// 등록 가능하다면, Course<Person, Worker, Student, HighStudent>게 지정할 수만 있다면
	// 우리가 원하는 대로, 위에서 생성된 모든 과정은 등록가능하지 않을까요?
	// 그런데, 이런 문법은 제네릭에서는 없습니다. 그럼 어떻게 하면,
	// 하나의 구체타입으로 1개 이상의 서로 다른 여러 구체 타입을 지정할 수 만 있다면
	// 좋을텐데, 무슨 방법이 없을까?? -> 그래서 나온게, 바로 와이들카드 타입인 '?' 입니다.
	
	private static void registerCourse(
//		Course<Person, Worker, Student, HighStudent> course  // xx
//		Course<?> course									// ok : 모든 지원자 타입의 과정수용 + 엉뚱한 과정도 수용
//		Course<? extends 부모타입> course
//		Course<? extends Student> course  // 학생 과정 + 고등학생과정 2개만 수용가능한 매개변수	
		Course<? extends Person> course  
			
//		Course<? super 자식타입> course
//		Course<? super HighStudent> course // 고등학생 과정 + 학생 과정 + 일반인 과정
//		Course<? super Worker> course      // 재직자 과정 + 일반인 과정
		) {
		
		log.debug("registerCourse({}) invoked.", course);
		
	}// registerCourse
	
	
	
	

}// end class
