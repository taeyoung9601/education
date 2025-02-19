package org.zerock.myapp.exception;

import java.io.Serial;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public final class InsertServiceException extends BizException {

	@Serial private static final long serialVersionUID = 1L;

	public InsertServiceException(String message) {
		super(message);
	}
	
	public InsertServiceException(Exception e) {
		super(e);
	}
	
	public InsertServiceException(String message, Exception e) {
		super(message, e);
	}
	
}
