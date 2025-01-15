package org.zerock.myapp.Q;


import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


@Log4j2
// 물이 흐르는 파이프 형태의 자료구조인 Q에 대해서 알자!
public class QExample {

    public static void main(String[] args) {
        log.debug("main({}) invoked.", Arrays.toString(args));

        Queue<Message> q = new LinkedList<>();

        q.offer(new Message("TO1", "Hello1"));
        q.offer(new Message("TO2", "Hello2"));
        q.offer(new Message("TO3", "Hello3"));

        log.info("\t+ q: {}", q);

        log.info("=".repeat(50));

        Message first = q.peek();
        Message message = q.poll();

        log.info("\t+ first: {}", first);
        log.info("\t+ message: {}", message);

        log.info("\t+ q: {}", q);

        q.forEach(log::info);
    } // main

}
