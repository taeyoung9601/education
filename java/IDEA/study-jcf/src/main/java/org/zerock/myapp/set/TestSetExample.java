package org.zerock.myapp.set;


import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Log4j2
// 목표: Set 컬렉션의 중복판정 알고리즘 테스트 용도
public class TestSetExample {

    public static void main(String[] args) {
        log.debug("main({}) invoked.", Arrays.toString(args));

        Set<Member> set = new HashSet<>();

        Member member1 = new Member("Yoseph", 23);
        Member member2 = new Member("Yoseph", 23);

        set.add(member1);
        set.add(member2);

        log.info("\t+ set: {}", set);

        log.info("Member1 - hashCode: {}, equals: {}",
                member1.hashCode(),
                member1.equals(member2)
        );

        log.info("Member2 - hashCode: {}, equals: {}",
                member2.hashCode(),
                member2.equals(member1)
        );
    } // main

} // end class
