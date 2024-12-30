package org.zerock.myapp.Samples;

import java.lang.reflect.Method;
import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TTT {

	static {
		System.out.println("============TTT=========");
	}
	
	
	public static void main(String[] args) {
		System.out.println("main({}) invoked.".formatted(Arrays.toString(args)));
		
		Class<TTT> clazz = TTT.class;
		Method[] methods = clazz.getDeclaredMethods();
		log.info("\t+methods:{}", Arrays.toString(methods));
		
		Class<?> parentClazz = clazz.getSuperclass();
		log.info("\t+parentClazz:{}", parentClazz);
		
	}//main
	
	
}// end class
