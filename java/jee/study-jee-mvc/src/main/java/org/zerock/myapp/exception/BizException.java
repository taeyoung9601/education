package org.zerock.myapp.exception;

import java.io.Serial;

import lombok.NoArgsConstructor;

/*
 * sealed class
 * 이 키워드가 붙은 클래스는, 자신을 상속받을 수 있는 하위 타입을 제약하는
 * 클래스를 쉴드 클래스라고 합니다. 즉 아무에게나 부모 클래스가 안되겠다는
 * 것으로 하위타입의 범위를 제약할 때 사용합니다.
 */

@NoArgsConstructor
public sealed class BizException extends Exception 
	permits 
	InsertServiceException,		//신규 사원 등록 로직 수행시 발생 가능한 예외
	SelectServiceException, 	// 특정 사원 상세조회 로직 수행시 발생 가능한 예외
	UpdateServiceException, 	// 특정 사원 수정 로직 수행시 발생 가능한 예외
	DeleteServiceException, 	// 특정 사원 삭제 로직 수행시 발생 가능한 예외
	ListServiceException, 		// 전체 사원 목록 조회 로직 수행시 발생 가능한 예외
	UnknownCommandException		// 알 수 없는 Command일 경우 발생 가능한 예외
{
	
	@Serial private static final long serialVersionUID = 1L;
	
	
	public BizException(String message) {
		super(message);
	}
	
	public BizException(Exception e) {
		super(e);
	}
	
	public BizException(String message, Exception e) {
		super(message, e);
	}
	
}// end class
