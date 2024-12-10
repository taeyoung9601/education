package org.zerock.myapp.examples;

import java.util.Scanner;

import org.zerock.myapp.libs.Utils;

public class CurrentConverter {

	//환율변환기
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		//외부에서 실시간으로 (란 말은, 일정한 주기로 계속해서 받아오는 것을 말합니다)
		//환율데이터를 가져오면, 금상첨화이죠.. 가능합니다. 공공데이터포털에 있는 Open API
		//가 엄청 많구요. 그 중에 환율 정보를 제공하는 API도 많이 있습니다. 우리가 후에
		// 스프링부트 배울 때, 외부 공공 데이터포털API와 연동해서 데이터 가져올겁니다.
		
		//가정: 현재환율 ( USD기준) : $1 = 1,450DNJS
		double exchangeRate = 1450;
		
		try {
			//Step1. 사용자로부터 변환할 달러화를 입력받음.
			Utils.printToConsole("Input your USD dollars:");
			System.out.print("\t>>");
			
			//Step2. 사용자가 입력한 달러 -> 원화로 변환
			//		 이 때, 가정한 환율데이터를 이용해야죠. 즉, 원화 = USD * 환율이 되겠죠.
			double usd = scanner.nextDouble();		
			
			//Step3. 환율 정보와 입력받은 달러를 기반으로 원화로 변환
			double won = usd * exchangeRate;
			
			Utils.formatToConsole(">> 입력하신 {0} 달러는 , 현재환율 ({1}) 기반으로,총 {2}원 입니다.", usd, exchangeRate, won);
			
		}finally {
			scanner.close();
			Utils.printToConsole("Done");
		}
		
	}//main

}// end class
