package org.zerock.myapp.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class OutputStreamExample1 {

	// 목표: 바이트 기반의 출력 스트림 사용법을 익히자!
	public static void main(String[] args) throws IOException {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		final String path = "C:/temp/TTT.dat";
		@Cleanup OutputStream os = new FileOutputStream(path);
		
		// 한 바이트씩 출력스트림에 출력
//		final String myName = "Tae";		// 이 문자열을 -> 바이트열로 변환해야 함.
//		byte[] data = myName.getBytes();  	// (주의) 위 변환 시 역시 문자셋(문자집합)을 지정하지않으면
											// 		기본 운영체제의 문자셋(MS949) 사용
		
		final String myName = "태영";
//		byte[] data = myName.getBytes("UTF-8");
//		byte[] data = myName.getBytes("ISO-8859-1");	// ASCII
		byte[] data = myName.getBytes("MS949"); 		// 윈도우의 기본문자집합
		
		for(byte b : data) {
			os.write(b);					// 매개변수타입: int
		}
		
		// 출력 스트림을 닫기전에,
		// 반드시 최소한 한번은 flush를 수행해야 합니다. (중요)
		os.flush();
		
	}

}
