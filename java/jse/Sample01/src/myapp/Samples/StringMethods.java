package org.zerock.myapp.Samples;

import java.util.Arrays;

import org.zerock.myapp.libs.Utils;

import ch.qos.logback.classic.pattern.Util;

public class StringMethods {

	//목표: 최대한 원시 데이터 타입인 문자열 조작할 수 있는 메소드들을 알자!
	public static void main(String[] args) {
		
		//1. String.charAt(int index) - 특정 인덱스에 위치한 1개의 문자를 반환
		final String testStr = "0123456789";
		Utils.formatToConsole("1.Stirng.charAt(index): {0}",testStr.charAt(0));
		
		//2. String.concat(String str2) - 인자 값으로 들어온 문자열과 연결 시킨 문자열 반환
		Utils.formatToConsole("2.String.concat(otherStr):{0}" , testStr.concat("Tae"));
		
		//3. String.contains(searchStr) - 인자 값으로 지정한 문자열이 존재하는지를 논리값으로 반환
		Utils.formatToConsole("3.String.contains(searchStr):{0}", testStr.contains("10"));
		
		//4. String.endsWith(String suffix) - 접미사(suffix)로 문자열이 끝나는지 검사하여 논리값으로 반환
		Utils.formatToConsole("4.String.endsWith({0}):{1}","789", testStr.endsWith("789") );
		
		//5. String.equals(Object obj) - 인자 값으로 들어온 객체의 문자열과 같은지 비교(단, 대소가림)
		Utils.formatToConsole("5.String.equals({0}):{1}", new String(testStr), testStr.equals(new String(testStr)) );
		
		//6. String.equalsIgnoreCase - 두 문자열만 비교(대신, 대소는 따지지 않습니다.)
		Utils.formatToConsole("6.String.equalsIgnoreCase({0}):{1}", "Tae", "Tae".equalsIgnoreCase("tae"));
		
		//7. String.format(String template, Object ...args) - 지정된 템플릿 안에, 원하는 위치에, 인자 값 출력하여 반환
		//  (참고) 이 메소드는 static method 입니다. 포멧팅 기호(1) %s (2) %d (3) %f
		Utils.formatToConsole("7.String.format({0},{1}):{2}",
				"name: %s, age: %s",
						Arrays.toString(new String[]{"Tae", "26" }),
						String.format("name: %s, age: %s","Tae",26)
						);
		
		//8. String.indexOf(String substr) - 지정된 문자열이 있으면, 그 시작 인덱스 번호를 반환
		//   편집기 구현시, find/replace 기능 구현에 사용됩니다.
		Utils.formatToConsole("String.indexOf({0}):{1}","345", testStr.indexOf("345"));
		
		
		//9. String.isBlack() - 문자열이 비어있는지(빈 문자열)또는 공백으로만 되어있는지 확인
		//						외부에서 들어온 데이터가 비어있는지, 혹 공백 문자열인지 확인하는 것은 필수
		Utils.formatToConsole("9.String.isBlank():{0}", "".isBlank());
		
		//10. String.length - 문자열의 길이를 반환
		 Utils.formatToConsole("String.length():{0}", testStr.length());

		//11. String.repeat(int count) - 지정된 횟수만큼, 문자열 반복해서 출력
		Utils.formatToConsole("11.String.repeat({0}):{1}", 3, testStr.repeat(3));
		
		
		//12. String.replace(searchStr, replaceStr) - 검색어로 찾은 모든 문자열을, 지정된 대체 문자열로 대체함
		//	  편집기 구현 시, find-replace 기능 구현에 사용됩니다.
		Utils.formatToConsole("12.String.replace({0},{1}):{2}", "345", "abc", testStr.replace("345", "abc"));
		
		//13. String.split(String separators) - 지정된 구분자로 문자열을 쪼개서, 토큰(token) 생성하여
		//		이들 토큰으로 구성된 배열 반환
		Utils.formatToConsole("13.String.split({0}):{1}", "," , Arrays.toString("0,1,2,3,4,5,6,7,8,9".split(","))); // 문자열 tokenizing
	}

}
