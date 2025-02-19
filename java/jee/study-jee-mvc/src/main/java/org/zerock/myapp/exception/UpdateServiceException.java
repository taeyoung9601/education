package org.zerock.myapp.exception;

import java.io.Serial;

import lombok.NoArgsConstructor;

@NoArgsConstructor

// non-sealed - 보호막 제거: 이클래스는 누구든지 상속가능
// final - 상속불가
// sealed - 자식클래스도 보호막(상속범위를 제약) 치겠다!

public final class UpdateServiceException extends BizException{

	@Serial private static final long serialVersionUID = 1L;

}
