package org.zerock.myapp.exception;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2   
public class ArrayIndexOut {

   // 목표 : 두번째로 자주 발생하는 Runtime 예외로 배열의 접근 가능한 인덱스 범위를 초과할 때 발생하는 예외.
   public static void main(String ...args) {
      log.debug("main({}) invoked." , Arrays.toString(args));
      
      // 인덱스의 범위 : 0 - Array.length - 1 
      log.info("\t+ args[7]: {}", args[7]);
   }
}