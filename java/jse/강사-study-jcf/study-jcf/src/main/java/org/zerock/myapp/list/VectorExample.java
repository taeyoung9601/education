package org.zerock.myapp.list;


import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Vector;


@Log4j2
public class VectorExample {

    // 목표: Vector 컬렉션을 경험해보자!!
    public static void main(String[] args) {
        log.debug("main({}) invoked.", Arrays.toString(args));

        List<Board> boards = new Vector<>();

        Board vo = new Board(77L, "TITLE", "Yoseph", new Date());
        boards.add(vo);

        log.info("\t+ boards: {}", boards);
    } // main

}
