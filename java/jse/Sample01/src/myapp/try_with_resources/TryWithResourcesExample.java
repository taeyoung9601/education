package org.zerock.myapp.try_with_resources;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import org.zerock.myapp.libs.Utils;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class TryWithResourcesExample {

	// 목표: 자원객체(AutoCloseable 인터페이스 구현객체)를 자동으로 close()메소드 호출을
	//           통해, 반드시 자원해제가 되어, 자원의 누수같은 치명적인 영향이 없도록 보장
	//           해주는 try-with-resources block의 이해와 응용
	public static void main(String[] args) throws IOException, InterruptedException {
		log.debug("main({}) invoked.", Arrays.toString(args));

//		@Cleanup
//		@Cleanup("close")
//		@Cleanup("shutdown")	: AutoClosable 하지 않은 자원객체도 동일하게 자동으로 close할 수 있음

		// try-with-resources 블록과 같은 기능을 수행
		Scanner scanner = new Scanner(System.in);

		// try-with-resources 블록은 
		//   가. 단독으로 사용이 가능하고
		try ( scanner; ) {
			Utils.printToConsole("나이를 입력해주세요:");		// prompt message
			
			int myAge = scanner.nextInt();			
			log.info("\t+ myAge: {}", myAge);
		}
		
		// ----------------

		//   나. catch, finally block과도 함께 사용가능합니다.
//		try ( scanner; ) {
//			;;
//		} catch(Exception e) {
//			;;
//		} finally {
//			;;
//		}
		
		// ----------------
		
		Resource1 res1 = new Resource1();
		Resource2 res2 = new Resource2();
		Resource3 res3 = new Resource3();
		
		try (    res1; res2; res3;    ) {
			;;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}


@Log4j2
@NoArgsConstructor
class Resource1 implements AutoCloseable {
	@Override
	public void close() throws Exception {
		log.debug("close() invoked.");
	}	
}


@Log4j2
@NoArgsConstructor
class Resource2 implements AutoCloseable {
	@Override
	public void close() throws Exception {
		log.debug("close() invoked.");
	}	
}


@Log4j2
@NoArgsConstructor
class Resource3 implements AutoCloseable {
	@Override
	public void close() throws Exception {
		log.debug("close() invoked.");
	}	
}
