package org.zerock.myapp.domain;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.Date;

@Value
public class BoardVO {  // VO (Value Object) Pattern

    Integer seq;
    String title;
    String content;
    String writer;
    Date createDate;
    Integer cnt;

}// end class
