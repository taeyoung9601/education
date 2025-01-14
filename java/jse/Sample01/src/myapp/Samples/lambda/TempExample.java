package org.zerock.myapp.lambda;

import java.util.Arrays;
import java.util.stream.IntStream;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TempExample {

	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		IntStream.rangeClosed(1, 10).forEach(seq -> {
			log.info(seq);
		});

	}

}
