package org.zerock.myapp.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class OutputStreamExample2 {

	// 목표: 출력용 양동이의 일부만 사용하는 경우
	public static void main(String[] args) throws IOException {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		final String path = "C:/temp/TTT.dat";
		@Cleanup OutputStream os = new FileOutputStream(path);
		
		final String message = "012345678901234567890123456789";	
//		final String message = "김태영";	
		
		// 참고로 문자열로 문자 집합을 지정하실 때, 아래 경우 모두 같게 인정됩니다:
		// (1) UTF-8 (2)UTF8 (3)utf-8 (4)utf8
		byte[] bucket = message.getBytes();			// string -> byte 0 // MS949 
//		byte[] bucket = message.getBytes("MS949");	// wintodw default character set
//		byte[] bucket = message.getBytes("cp949");	// wintodw default character set
		
//		byte[] bucket = message.getBytes("utf8");	
//		byte[] bucket = message.getBytes("ISO-8859-1");
//		byte[] bucket = message.getBytes("KSC5601");		// 과거에 사용했었던 한글전용 문자집합
//		byte[] bucket = message.getBytes("EUC-KR");			// 상동
		os.write(bucket,10,10); 					
		os.flush();
		
	}

}
