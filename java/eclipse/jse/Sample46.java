
public class Sample46 {

	//목표: static initializer 의 특징을 이해하자!
	public static void main(String[] args) {
		Utils.printToConsole("main(args) invoked.");
		
		SamsungTV tv = new SamsungTV();
		Utils.printToConsole(tv.company);
		
		
	}//main
	
}//end class
