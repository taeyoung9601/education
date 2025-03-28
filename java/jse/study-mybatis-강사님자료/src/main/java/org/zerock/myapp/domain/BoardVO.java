package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Value;

// VO - Value Object
// (1) 목적: DQL문의 수행결과 테이블의 1개의 행을 저장할 수 있는
//                읽기전용(read-only) 객체를 생성할 클래스
// (2) lombok의 @Value 어노테이션으로 한방에 만들 수 있습니다:
//      즉, 아래의 어노테이션을 하나로 합친게 이 어노테이션입니다.
//		  @ToString
//		  @Getter
//		  @AllArgsConstructor
//		  private fields
//      POJO
//      JavaBeans 이어야 함.
//      컬렉션에 저장가능하도록 반드시 중복판정 2단계 알고리즘에 대응해야함
//      (즉, equals 와 hashCode 메소드 오버라이딩 해야 됨)
// (3) 필드는 조회대상 테이블의 모든 컬럼을 필드로 선언하시면 되되,
//       주의할 점은 반드시, 대상 테이블의 스키마에 나온 컬럼순서와 타입에 맞게
//       자바타입의 필드로 선언하셔야 합니다. 그래야 마이바티스가
//       실제 발생된 결과셋을 -> VO 객체의 필드에 넣어줄 수 있습니다.

@Value
public class BoardVO {
	// NULL 값을 가질 수 있어야 하기 때문에,
	// VO에 나오는 모든 필드는 반드시 참조타입으로 선언해야 합니다.
	Integer bno;
	String title;
	String content;
	String writer;
	
	// 컬럼명이 복합단어(즉, 2개 이상의 단어로 구성)인 경우에는
	// 첫단어는 소문자로 시작하고, 두번째 단어부터는 Camel 표기법을 따라야 한다.
//	java.util.Date insertTs;						// OK
//	java.sql.Timestamp insertTs;				// OK
	java.time.LocalDateTime insertTs;		// JODA TIME (LocalDate, LocalTime, LocalDateTime)
	
	Date updateTs;

	
}








