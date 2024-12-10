package org.zerock.myapp.libs;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utils {	
	private static final String GREEN = "\u001B[32m";
	private static final String RESET  = "\u001B[0m";

	
	public static void main(String[] args) {
		String text = "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
		
		Utils.printToConsole(text);				
		Utils.printToConsole("0123456789", "0123456789" , "0123456789", null, 1 + 2, "Yoseph" + 23, true, ' ', "");
		Utils.printToConsole();
		
		Utils.formatToConsole("name: {0}, age: {1}", "Yoseph", 23);
	}
	
	public static synchronized void formatToConsole(String template, Object ... args) {
		String message = MessageFormat.format(template, args);
		Utils.printToConsole(message);
	}
	
	public static synchronized void printToConsole(Object ... args) {
		StringBuffer sb = new StringBuffer();
		
		if(args.length > 0) {
			sb.append( new SimpleDateFormat( "* HH:mm:ss.SSS ").format(  new Date() ) + RESET );
			
			String t = Thread.currentThread().getName();
			sb.append(String.format("[ %-15s ] - ", t));
		}
		
		for(Object o : args) sb.append(o);
        if(sb.length() > 0) sb.append('\n');
        
		int delay = 10;
		
        for (char c : sb.toString().toCharArray()) {
            System.out.print(c);
            
            try { Thread.sleep(delay); } catch (Exception _ignored) {}
        }
	}

}
