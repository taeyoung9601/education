package org.zerock.myapp.generic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString
@Getter

//XX: 비지니스 로직에 부합되지 못함
//	  왜?? 이 제네릭 클래스로부터, 과정이 만들어질 때,
//	  학생들을 저장할 배열을 생성자 매개변수(즉, 외부에서)로 받는게 아니라,
//	  이 과정이 수용가능한 최대 학생수를 받아다가 배열을 만들어야 하기 때문에
//	  lombok 의 어노테이션으로는 적용이 안된다
//@AllArgsConstructor

//타입 파라미터 T의 의미: 해당 과정(Course)에 지원자의 유형을
// 구체 타입으로 받는 역할
public class Course<T> {
	private String name;
	private T[] students; // 모든 지원자 유형별 학생들을 저장할 배열
	

	@SuppressWarnings("unchecked")
	public Course(String name, int maxCountOfStudents) {
		log.debug("Course({},{}) invoked.", name, maxCountOfStudents);

		this.name = name;
		
		// *주의할 점: 일반 배열 만들듯이, 배열을 아래와 같이 선언하시면,
		// 무조건 아래의 컴파일 오류가 발생합니다.
		// 때문에, 타입 파라미터를 원소로 가지는 배열을 생성하실 때에는 아래와 같이
		// 2단계로 생성하셔야합니다:
		// Step1. Object 를 원소로 가지는 배열을 먼저 생성
		// Step2. Step1 에서 생성한 Object[] => T[] 배열로 "강제 형변환" 해야 함
		
		//this.students = new T[maxCountOfStudents];
		
		Object[] tempArr = new Object[maxCountOfStudents]; // Step1
		this.students = (T[]) tempArr;						// Step2
		

	}// Constructor
	
	public void addApplicant(T applicant) {
		log.debug("addApplicant({}) invoked.", applicant);
		
		//배열에서 빈 자리에 지원자를 넣어줘야 하기 대문에
		//Classical for 문으로 순회(Traverse)하면서, 빈 자리에 지원자 넣어줌
		for(var index = 0; index < students.length; ++index) {
			if(students[index] == null ) {  // 빈자리라면..
				students[index] = applicant;
				break;
			}
		}
	}// addApplicant

} // end class
