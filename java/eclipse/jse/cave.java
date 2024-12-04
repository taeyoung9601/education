	
public class cave {
	
   public static void main(String[] args) {  
	   
      int 동굴길이  = 137;                  
      System.out.println(동굴길이);
      
      int 날짜 = 1;            
      System.out.println(날짜);    
      
      int 올라가는높이 = 17;         
      System.out.println(올라가는높이);  
      
      int 떨어지는높이 = 7;
      System.out.println(떨어지는높이); 
      
      int 현재위치 = 0;
      System.out.println(현재위치);
         
      for(; ;) {
    	
         현재위치+= 올라가는높이;
         System.out.println(현재위치+","+날짜+"지난날자");
         
         if (현재위치>동굴길이) {
            
               break;
         }
         현재위치-=떨어지는높이;
         날짜++;
         
      }
   }
}
