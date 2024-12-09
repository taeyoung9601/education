package org.zerock.myapp.libs;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utils {

	public static void main(String[] args) {
		String text = "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
		
		Utils.printToConsole(text);				
		Utils.printToConsole("0123456789", "0123456789" , "0123456789", null, 1 + 2, "Yoseph" + 23, true, ' ', "");
		Utils.printToConsole();
	}
	
	public static synchronized void printToConsole(Object ... args) {
		StringBuffer sb = new StringBuffer(); 
		
		if(args.length > 0) {
			sb.append( new SimpleDateFormat("* HH:mm:ss.SSS ").format( new Date() ) );
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