package org.zerock.myapp.exception;

import java.io.Serial;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public final class UnknownCommandException extends BizException{
	
	@Serial private static final long serialVersionUID = 1L;

	public UnknownCommandException(String message) {
		super(message);
	}
	
	public UnknownCommandException(Exception e) {
		super(e);
	}
	
	public UnknownCommandException(String message, Exception e) {
		super(message, e);
	}
	
}
