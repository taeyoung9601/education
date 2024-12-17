package org.zerock.myapp.exception;

import java.io.IOException;
import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class NullPointerExcaption {

   // 실행예외 : (1) NullPointerException이 어떤 상황에서 발생하는지 이해하자
   public static void main(String[] args) throws IOException {
      log.debug("main({}) invoked.",Arrays.toString(args));

      // 예상하지 않는 값이 들어갔을 경우 생기는 오류. null, infinity, NaN
      String data = null;
      log.info("\t+data: {}",data);
      // data.toString() 넣으면 NullPointerException 오류 발생
      
//      try {
//         System.in.read();
//      } catch (IOException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      } // 키보드의 키코드 반환 메소드
      
   } // main

} // end class
