package org.zerock.myapp.list;

import lombok.*;

import java.util.Date;


@Value  // Value Object Class 선언시 사용
public class Board {    // 하나의 게시글을 읽기전용으로 보관하는 클래스
    private Long id;
    private String subject;
    private String writer;
    private Date createdDate;


} // end class

