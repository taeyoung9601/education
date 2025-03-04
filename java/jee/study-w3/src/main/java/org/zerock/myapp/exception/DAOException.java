package org.zerock.myapp.exception;

import java.io.Serial;

// Custom User-defined Exception for DAOs in the Persistence Layer
public class DAOException extends Exception {

	@Serial private static final long serialVersionUID = 1L;

	public DAOException(String message) {
		super(message);
	}
	
	public DAOException(Exception original) {
		super(original);
	}
	
	public DAOException(Exception original, String message) {
//		super(original, message);
		super(message, original);
	}
	
}// end class
