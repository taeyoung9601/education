import java.util.Arrays;
import java.util.Random;

public class array_project {
    public static void main(String[]args){

	        // 1~100 숫자 중 30개 추춯, 중복 제거, 
	int[] randomArr = new Random().ints(30, 1, 100).distinct().limit(10).toArray();

	Utils.printToConsole(Arrays.toString(randomArr));
	
	//3의 배수 구하기
	
   
	  System.out.println("1. 위 randomArr배열에서 3의 배수 구하기");
	  for(int a : randomArr) { 
	     //3의 배수 구하기
	     if(a % 3 == 0) {
	        Utils.printToConsole("> "+a);
	     }      
	  }

	
	
	        // 1~100 숫자 중 30개 추춯, 중복 제거, 
	int[] randomArr1 = new Random().ints(30, 1, 100).distinct().limit(10).toArray();
	Utils.printToConsole(Arrays.toString(randomArr1));
	//소수 구하기
	for(int a : randomArr1) {
	boolean t = true;
	for(int i = 2; i < a; i++) {
	  if(a%i == 0) {
	     t = false;
	     break;
	  }
	}
	
	if(t) {
	  Utils.printToConsole("> "+a);
	}
	}
	
	
	
	        // 1~100 숫자 중 30개 추춯, 중복 제거, 
	int[] randomArr2 = new Random().ints(30, 1, 100).distinct().limit(10).toArray();
	Utils.printToConsole(Arrays.toString(randomArr2));
	//버블정렬하기(내림차순)
	for(int x = 0; x < randomArr2.length; x++) {
	for(int i = 0; i < randomArr2.length-1; i++) {
	  int a = randomArr2[i];
	  int b = randomArr2[i+1];
	  //System.out.println("randomArr["+i+"]: "+randomArr[i] + " // randomArr["+(i+1)+"]: " + randomArr[(i+1)] + " // a: " + a + " // b: " + b);
	  if(a < b) {
	     //System.out.println("a < b");
	     randomArr2[i] = b;
	     randomArr2[i+1]= a;
	  }
	  //System.out.println("randomArr["+i+"]: "+randomArr[i] + " // randomArr["+(i+1)+"]: " + randomArr[(i+1)] + " // a: " + a + " // b: " + b);
	  
	
	}
	}
	Utils.printToConsole(Arrays.toString(randomArr2));
	        
        
        
        
        
        
        
        
        
        
        
    }// main
}// end class
