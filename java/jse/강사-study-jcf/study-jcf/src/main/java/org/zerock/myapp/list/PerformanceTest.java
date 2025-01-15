package org.zerock.myapp.list;


import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@Log4j2
public class PerformanceTest {

    // List<T> 인터페이스의 구현객체의 선택에 따른,
    // 성능비교 (시간측정)
    public static void main(String[] args) {
        log.debug("main({}) invoked.", Arrays.toString(args));

        // ArrayList vs LinkedList
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        long startTime;
        long endTime;


        // --- 2 ------------------------------
//        startTime = System.currentTimeMillis();     // milliseconds: 1/1000
        startTime = System.nanoTime();                // nanoseconds: 1/10억

        for(int i = 0; i < 10000000; i++) {
            linkedList.add(0, String.valueOf(i));
        }

        endTime = System.nanoTime();
        log.info("2. LinkedList's Elapsed Time: {}", (endTime - startTime)); // Delta: ns

        // --- 1 ------------------------------
//        startTime = System.currentTimeMillis();     // milliseconds: 1/1000
        startTime = System.nanoTime();                // nanoseconds: 1/10억

        for(int i = 0; i < 10000000; i++) {
            arrayList.add(0, String.valueOf(i));
        }

        endTime = System.nanoTime();
        log.info("1. ArrayList's Elapsed Time: {}", (endTime - startTime)); // Delta: ns

    } // main

}
