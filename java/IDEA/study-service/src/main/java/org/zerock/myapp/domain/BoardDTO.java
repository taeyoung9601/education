package org.zerock.myapp.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BoardDTO {     // DTO Pattern

    private Integer seq;                // private final  xx : setter로 값을 변경하기때문에
    private String title;
    private String content;
    private String writer;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)       // if yyyy-MM-dd
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)       // if HH:mm:ss
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)       // if yyyy-MM-ddTHH:mm:ss
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private Integer cnt;

}// end class
