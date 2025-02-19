package org.zerock.myapp.exception;

import java.io.Serial;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public final class SelectServiceException extends BizException {
	
	@Serial private static final long serialVersionUID = 1L;

	public SelectServiceException(String message) {
		super(message);
	}
	
	public SelectServiceException(Exception e) {
		super(e);
	}
	
	public SelectServiceException(String message, Exception e) {
		super(message, e);
	}
	
}
