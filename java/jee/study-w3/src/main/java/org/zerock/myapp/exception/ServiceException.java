package org.zerock.myapp.exception;

import java.io.Serial;

// 컨트롤러 exception은 따로 만들면 안된다.
// Custom User-defined Exception for Services in the Business Layer
public class ServiceException extends Exception {

	@Serial private static final long serialVersionUID = 1L;

	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Exception original) {
		super(original);
	}
	
	public ServiceException(Exception original, String message) {
//		super(original, message);
		super(message, original);
	}
	
}// end class
