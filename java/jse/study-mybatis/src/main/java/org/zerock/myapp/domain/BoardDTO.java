package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Data;

//@ToString
//@Getter @Setter
//@NoArgsConstructor
//@EqualsAndHashCode

@Data
public class BoardDTO {		// Data Transfer Object : 데이터 
	
	// Null 값을 가질 수 있어야 하기 때문에, VO에 나오는 모든 필드는 반드시 참조 타입으로 선언
	private Integer bno; 	//PK : Primary Key  		
	private String title;
	private String content;
	private String writer;

	
	private java.util.Date insertTs;
//	java.util.Timestamp insertTs;
//	java.time.LocalDateTime insertTs;
	
	private Date updateTS;
	
	
	
	
	
	
	
}
