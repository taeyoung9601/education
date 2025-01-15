package org.zerock.myapp.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InputStreamExample3 {

	// 목표 : 양동이 (byte[] 배열)로 입력 스트림에서 여러 바이트들을 퍼내자
	public static void main(String[] args) throws IOException {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		final String path = "C:/Temp:/back_city.png";
		@Cleanup InputStream is = new FileInputStream(path);
		
		//--1
		int fileSize = is.available();
		log.info("\t + fileSize: {} ", fileSize);
		
		byte[] bucket = new byte[30];
		
		int readBytes;					// 한번의 양동이로 퍼낸 바이트의 개수
		String data = ""; 				// 변환된 문자열을 계속 누적
		
		while(true) { 					
			readBytes = is.read(bucket,9,10);		// (bucket, offset, length) 시작위치(offset) : 배열의 시작인덱스 번호
													//							length : 지정된 개수만큼 채워라
			//--2
			log.info("\t + readBytes : {}, bucket: {}", readBytes, Arrays.toString(bucket));
			
			if(readBytes == -1) {
				break;
			}
			
			//양동이로 퍼낸 바이트들 -> 문자열로 변환하여 출력
			// 이 때 byte -> String 으로 변환 시, 별도로 인코딩 문자셋을 지정하지않으시면,
			// 기본으로 운영체제의 문자셋(즉, 문자집합)으로 MS949로 인코딩 된다.
			data = data + new String(bucket,0,readBytes);
			
		}//while
		
		System.out.println(data);
		
	} // main

} // end class

