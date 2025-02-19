package org.zerock.myapp.exception;

import java.io.Serial;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public final class DeleteServiceException extends BizException{

	@Serial private static final long serialVersionUID = 1L;

	public DeleteServiceException(String message) {
		super(message);
	}
	
	public DeleteServiceException(Exception e) {
		super(e);
	}
	
	public DeleteServiceException(String message, Exception e) {
		super(message, e);
	}
	
}
