package org.zerock.myapp.exception;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class TransferExample {

	// 목표: 실제 비즈니스 로직으로 계좌이체를 수행하면서,
	//       발생되는 적절한 비즈니스 예외를 정의하는 방법을 배우고, 
	//       이전에 배웠던 예외처리와 관련된 모든 것을 동시에 같이 사용해보자
	public static void main(String[] args) {
		// ============================== //
		// 계좌이체 알고리즘 구현
		// ============================== //
		try {
		// Step 1. 본인확인
		TransferExample.본인확인("120121-1238722");
		
		// Step 2. 소스계좌의 유효성 검증
		TransferExample.계좌유효성검증("소스계좌번호", 0);

		// Step 3. 타겟계좌의 유효성 검증
		TransferExample.계좌유효성검증("타겟계좌번호", 0);
		
		// Step 4. 이체금액에 대한 소스계좌의 잔고확인
		TransferExample.소스계좌잔고확인("소스계좌번호", 30000);
		
		// Step 5. 이체실행
		TransferExample.이체실행("소스계좌", "타겟계좌", 30000);
		
		} catch (AuthenticationFailedException e) {
			e.printStackTrace();
			
			log.error("본인확인에 실패했습니다.");
		} catch (AccountInvaildException e) {
			e.printStackTrace();
			
			log.error("계좌가 유효하지 않습니다.");
		} catch (InsufficientBalanceException e) {
			e.printStackTrace();	
			
			log.error("잔고가 부족합니다.");
		} catch (TransferException e) {
			e.printStackTrace();
			
			log.error("이체에 실패했습니다.");
		} catch(Throwable t) {		// 그 외에 발생하는 모든 예외 처리(다형성-1), t로 하면 에러까지 다 잡음
			t.printStackTrace();
			
			log.error("이체에 실패했습니다.(알수없는오류: 404)");
		}
	} // main
	
	
	// Step 1. 본인확인
	private static boolean 본인확인(String ssn) throws AuthenticationFailedException{
		log.debug("Step 1. 본인확인수행({}) invoked.", ssn);
		
		boolean isAuthenticated = true;
		
		if(!isAuthenticated) {
			throw new AuthenticationFailedException("ssn is %s".formatted(ssn));
		}
		
		return true;	// 성공
	}
	
	// Step 2. 소스계좌의 유효성 검증
	// Step 3. 타겟계좌의 유효성 검증
	private static boolean 계좌유효성검증(String accountNumber, int type) throws AccountInvaildException {
		log.debug("Step2, Step3. 계좌의 유효성 검증({}, {}) invoked.", accountNumber, type);
		
		// type: 0 = 소스계좌, 1: 타겟계좌
		boolean isAccountValid = (type == 0) ? true : false;
		
		if(!isAccountValid) {
			throw new AccountInvaildException("accountNumber: %s".formatted(accountNumber));
		}
		
		return true; // 검증 성공
	}

	// Step 4. 이체금액에 대한 소스계좌의 잔고확인
	private static boolean 소스계좌잔고확인(String accountNumber, int transferMoney) 
			throws InsufficientBalanceException {
		log.debug("Step4. 소스계좌 잔고확인 - accountNumber: {}, transferMoney: {} invoked.",
				accountNumber, transferMoney);
		
		if(transferMoney > 50000) {	// 예외발생
			throw new InsufficientBalanceException("transferMoney < 50000");
		}
		
		return true;	// 잔고충분
	}
	
	// Step 5. 이체실행
	private static boolean 이체실행(String 소스계좌, String 타겟계좌, int 이체금액)
			throws TransferException {
		log.debug("Step5. 이체실행({},{},{}) invoked", 소스계좌, 타겟계좌, 이체금액);
		
		boolean isTransfered = true;
		if(!isTransfered) {	// 이체실패
			throw new TransferException();
		}
		return true;	// 이체 성공
	}
	
} // end class


// ============================== //
// 계좌이체 로칙 관련 예외 클래스 선언
// ============================== //

// 예외가 런타임 에러인지 체크드 에러인지 먼저 설정하고 상속받아쓴다
// 런타임에러 => Runtime
// checked 에러 => Exception


// 본인인증실패시 발생할 예외
@NoArgsConstructor
final class AuthenticationFailedException extends Exception {
	private static final long serialVersionUID = 1L;
	
//	public AuthenticationFailedException() { super(); }		// lombok으로 대체 가능
	public AuthenticationFailedException( String message) {super(message); }
	public AuthenticationFailedException( Exception causedBy) {super(causedBy); }
}

// 계좌 유효성 검증실패시 발생할 예외
@NoArgsConstructor
final class AccountInvaildException extends Exception {
	private static final long serialVersionUID = 1L;
	
//	public AccountInvaildException() { super(); }		// lombok으로 대체 가능
	public AccountInvaildException( String message) {super(message); }
	public AccountInvaildException( Exception causedBy) {super(causedBy); }
}

// 계좌 잔고부족시 발생할 예외
@NoArgsConstructor
final class InsufficientBalanceException extends Exception {
	private static final long serialVersionUID = 1L;
	
//	public InsufficientBalanceException() { super(); }		// lombok으로 대체 가능
	public InsufficientBalanceException( String message) {super(message); }
	public InsufficientBalanceException( Exception causedBy) {super(causedBy); }
}

// 이체 실패시 발생할 예외
@NoArgsConstructor
final class TransferException extends Exception {
	private static final long serialVersionUID = 1L;
	
//	public TransferException() { super(); }		// lombok으로 대체 가능
	public TransferException( String message) {super(message); }
	public TransferException( Exception causedBy) {super(causedBy); }
}


