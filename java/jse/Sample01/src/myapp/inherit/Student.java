package org.zerock.myapp.inherit;


import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@ToString(callSuper = true)
@Log4j2

public class Student extends People {

	public Student(String name, String ssn) {
		super(name, ssn);
		
		log.debug("Student({},{}) invoked.", name, ssn);
		
//		The field People.ssn is not visible 
//  	상속 제외대상: 부모의 멤버 (필드, 메소드)중, private 접근 제한자를 가지면 상속 제외
//		log.info("\t+this.ssn:{}",this.ssn);
		log.info("\t+super.getSsn()",super.getSsn());
		log.info("\t+this:{}",this);
		
		this.name = "Trinity";
		log.info("\t+this.name:{}",this.name);  // OK: 상속받은 필드 사용
		log.info("\t+super.name:{}",super.name); // OK : 상속받은 필드 사용이 아니라 부모 객체의 필드를 직접 사용
		
	}

}//end class
