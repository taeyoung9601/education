package org.zerock.myapp.generic;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ToString(callSuper = true)

public class HighStudent extends Student {

	public HighStudent(String name) {
		super(name);

		log.debug("HighStudent({})invoked.",name);
	}//constructor

}//end class
