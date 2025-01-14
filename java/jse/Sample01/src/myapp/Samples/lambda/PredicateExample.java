package org.zerock.myapp.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PredicateExample {

	private static List<Student> students = Arrays.<Student>asList(
			new Student("학생1", "남자", 90),
			new Student("학생2", "여자", 77),
			new Student("학생3", "남자", 87),
			new Student("학생4", "남자", 70),
			new Student("학생5", "여자", 98),
			new Student("학생6", "여자", 92),
			new Student("학생7", "남자", 64)
			);
	
	private static double average(Predicate<Student> testCondition) {
		int count = 0;
		int sum = 0;
		
		for (Student student : students) {
			if(testCondition.test(student)) {
				count++;							// 학생수 카운트
				sum = sum + student.getScore();		// 점수 누적
			} 
		}
		
		return sum /(count * 1.0);
	}
	
	
	// 입력 데이터를, 미리 설정한 조건(이를, Predicates 라고 합니다)에
	// 입력하여, 조건식을 평가하고, 조건을 통과하면 true, 아니면 false 반환하는
	// 비지니스 로직 구현시에, 이표준 함수적 인터페이스로 파이프라인 구축에 활용
	public static void main(String[] args) {
		log.debug("main({}) invoked.",Arrays.toString(args));
		
		//--1
		
		// 남학생들의 점수를 모두 모아서 평균점수 생성하여 출력
		
//		boolean test(T t);
		double maleAverage = PredicateExample.average(s -> s.getGender().equals("남자"));
		log.info("\t + 남학생들의 평균점수는? : {}", maleAverage);
		// 여학생들의 점수를 모두 모아서 평균점수 생성하여 출력
		double femaleAverage = PredicateExample.average(s -> s.getGender().equals("여자"));
		log.info("\t + 여학생들의 평균점수는? : {}", femaleAverage);
	}

}
