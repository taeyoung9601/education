package org.zerock.myapp.map;


import lombok.extern.log4j.Log4j2;

import java.util.*;


@Log4j2

// 실전: 동기화 처리된 Hashtable 을 사용하셔야 합니다.
public class HashMapExample {

    public static void main(String... args) {
        log.debug("main({}) invoked.", Arrays.toString(args));

        // Step1. Map<K, V> 컬렉션 객체 생성
        // 타입파라미터 K : Key 의 구체타입, V : Value 의 구체타입
        Map<String, Integer> map = new HashMap<>();

        // Step2. 새로운 요소(key=value pair) 추가
        map.put("학생1", 88);    // Map.Entry 객체로 만들어짐
        map.put("학생2", 90);
        map.put("학생3", 80);
        map.put("학생4", 72);

        map.put("학생3", 82); // OK, 수정

        log.info("\t+ map: {}", map);

        // Step3. 검색
        log.info("\t+ size: {}, isEmpty: {}",
                map.size(), map.isEmpty());

        log.info("\t+ containsKey: {}", map.containsKey("학생3"));
        log.info("\t+ containsValue: {}", map.containsValue(72));

        log.info("\t+ get(key): {}", map.get("학생2"));

        Set<String> keys = map.keySet();
        keys.forEach(log::info);

        Collection<Integer> values = map.values();
        values.forEach(log::info);

        Set< Map.Entry<String, Integer> > entries = map.entrySet();
        entries.forEach(log::info);

        log.info("=".repeat(50));
        map.forEach(log::info);

        // Step4. 삭제
        log.info("=".repeat(50));
        map.remove("학생1");
        map.forEach(log::info);

        map.clear();
        map = null;
    } // main

}
