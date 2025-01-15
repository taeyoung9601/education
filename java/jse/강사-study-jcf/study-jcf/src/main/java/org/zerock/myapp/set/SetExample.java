package org.zerock.myapp.set;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@Log4j2
public class SetExample {

    // 목표: Set 컬렉션 연습
    // Set 의 특징:
    //   (1) 순서를 보장하지 않는다
    //   (2) 대신, 중복을 허용하지 않습니다.
    //   (3) null 값도 1번만 저장 허용
    public static void main(String[] args) {
        log.debug("main({}) invoked.", Arrays.toString(args));

        //-- 1 ----------------
        Set<String> set = new HashSet<>();   // 기본크기: 10

        //-- 2 ----------------
        set.add("JAVA");
        set.add("JDBC");
        set.add("SERVLET/JSP");
        set.add("JAVA");            // 중복허용????
        set.add("MYBATIS");

        log.info("\t+ set: {}", set);

        //-- 3 ----------------
        log.info("\t+ size: {}", set.size());
        log.info("\t+ contains: {}", set.contains("JDBC"));
        log.info("\t+ contains: {}", set.contains("JPA"));
        log.info("\t+ isEmpty: {}", set.isEmpty());

        // Traverse - 1
        for ( String element : set ) {
            log.info(element);
        } // Enhanced for

        // Traverse - 2
        set.forEach(log::info);

        // 가장 오래된 순회방법 - 반복자(Iterator) 사용
        Iterator<String> iter = set.iterator();
        while(iter.hasNext()) { // Step1. 다음요소가 있습니까?
            String element = iter.next();   // Step2. 다음요소 주세요!!!
        }


        //-- 4 ----------------
        set.remove("JDBC");
        log.info("\t+ set: {}", set);

        //-- 5 ----------------
        // Set 컬렉션을 목적대로 사용이 끝난 직후에는 바로 GC가 되도록
        // Clear 해야 한다.

        set.clear();
        log.info("\t+ set: {}, isEmpty: {}", set, set.isEmpty());

        set = null;


        //-- 6 ----------------
        // 별도의 변경(수정) 메소드는 제공되지 않습니다.
        // 수학의 집합연산을 따라서, 직접 원소값을 수정하는 메소드는
        // 제공되지 않습니다.
        // 때문에, 수정할 원소가 있다면: 삭제 -> 추가 연산을 하시면 됩니다.

    } // main

} // end class

