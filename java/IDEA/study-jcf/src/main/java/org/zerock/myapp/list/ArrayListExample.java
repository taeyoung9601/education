package org.zerock.myapp.list;


import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Log4j2
public class ArrayListExample {


    // 목표: ArrayList<T> 의 사용법을 알자
    public static void main(String... args) {
        log.debug("main({}) invoked.", Arrays.toString(args));


        // 기본크기: 10
        @Cleanup("clear")
        List<String> list = new ArrayList<>();  // 1st. method

//        List<String> list = Arrays.<String>asList( "1", "2", "3", "4", "5", "6");
////                                            FQCN 반환 (Arrays$ArrayList)
//                                       ------------------------
//        log.info("\t+ type of list: {}", list.getClass().getName());


        // 리스트의 맨 마지막에 추가
        list.add("JAVA");
        list.add("JDBC");
        list.add("SERVLET/JSP");

        // 밀고 땡기는 현상발생
        list.add(2, "DATABASE");
        list.add("MYBATIS");

        list.add(null);
        list.add(null);

        log.info("\t+ list: {}", list);

        // CRUD 조작
        int size = list.size();
        log.info("\t+ size: {}", size);

        String item = list.get(3);
        log.info("\t+ item: {}", item);

        // Traverse (순회)
        for(String element : list) {    // Iterable<T> 해야 합니다.
            // 컬렉션의 요소를 수정해서는 안됩니다.
            log.info(element);
        } // enhanced for

        // Traverse with Classical for
        for(int counter = 0; counter < list.size(); ++counter) {
            // 컬렉션의 요소를 수정해서는 안됩니다.
            log.info(list.get(counter));
        }

        // Traverse with forEach method and Method Reference.
        list.forEach(log::info);

        // JDBC -> JPA 수정
        list.set(1, "JPA");
        log.info("\t+ list: {}", list);

        // 요소삭제 with index - 1
        list.remove(1);
        log.info("\t+ list: {}", list);

        // 요소삭제 with object - 2
        list.remove("MYBATIS");
        log.info("\t+ list: {}", list);

        // 리스트 컬렉션을 다 사용하고 난 직후에, 가능한 빨리
        // GC가 될 수 있도록 반드시 clear 시켜야 한다!!!
//        list.clear();
//        list = null;
//
//        log.info("\t+ list: {}", list);


    } // main

} // end class

