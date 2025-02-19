package org.zerock.myapp.exception;

import java.io.Serial;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public final class ListServiceException extends BizException{
	
	@Serial private static final long serialVersionUID = 1L;

	public ListServiceException(String message) {
		super(message);
	}
	
	public ListServiceException(Exception e) {
		super(e);
	}
	
	public ListServiceException(String message, Exception e) {
		super(message, e);
	}
	
}
