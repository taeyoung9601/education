package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Data;


// @ToString
// @Getter
// @Setter
// @NoArgsConstructor
// @EqualsAndHashCode

@Data
public class BoardDTO {	// Data Transfer Object: 데이터 전달 객체
	private Integer bno;		// PK : Primary Key
	private String title;
	private String content;
	private String writer;
	
	private java.util.Date insertTs;
//private 	java.util.Timestamp insertTs;
//private 	java.time.LocalDateTime insertTs;
	
	private Date updateTs;

	
}








