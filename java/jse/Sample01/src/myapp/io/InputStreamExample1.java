package org.zerock.myapp.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InputStreamExample1 {

	// 목표 : 바이트 기반의 입력스트림 ( InputStream ) 에 대해서 경험하자
	public static void main(String[] args) throws IOException {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		// 파일로부터, 파일의 데이터를 읽어내자!
		final String path = "C:/app/workspaces/eclipse/jse/Sample01/src/org/zerock/myapp/io/InputStreamExample1.java";
		@Cleanup
		InputStream is = new FileInputStream(path);
		
		log.info("is : {} " , is);
		
		int readByte;					// 입력스트림의 각 바이트를 하나씩 손으로 퍼내자!
		
		while(true) { 					// 중요) 입출력의 끝을 EOF(End-Of-File)이라고 합니다..
										// 이 EOF 문자는 ASCII 테이블에 정의되어 있습니다.
			readByte = is.read();		// 손으로 하나씩 바이트를 퍼내자
			if(readByte == -1) {		// EOF : 탈출조건 - 더이상 읽어낼 바이트가 없으면
				break;
			}
			
			System.out.print((char)readByte);
		}//while		
		
	} // main

} // end class
