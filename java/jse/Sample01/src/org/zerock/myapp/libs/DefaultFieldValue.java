package org.zerock.myapp.libs;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString            // 객체로 출력 시, 
@NoArgsConstructor // Default Constructor
public class DefaultFieldValue {
	
	//정수 타입의 필드 선언
	byte byteField;
	short shortField;
	int intField;
	long longField;
	
	//실수 타입의 필드 선언
	float floatField;
	double doubleField;
	
	//참조 타입의 필드 선언
	String StringField;
	int[] arrField;
	Gender Gender;
	
	
}// end class

enum Gender { MALE , FEMALE}