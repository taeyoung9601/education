package org.zerock.myapp.anonymous;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2


public class CalculatorImpl implements iCalculator {

	@Override
	public int add(final int number1,final int number2) {
		log.debug("add({},{}) invoked.",number1,number2);
		return number1 + number2;
	}

	@Override
	public int sub(final int number1,final int number2) {
		log.debug("add({},{}) invoked.",number1,number2);
		return number1 - number2;
	}

}
