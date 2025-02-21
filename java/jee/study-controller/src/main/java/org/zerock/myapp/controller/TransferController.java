package org.zerock.myapp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

// 하나의 컨트롤러마다, 특정 메뉴나 단위 서비스 요청을 처리하는 컨트롤러로
// 제작이 됩니다. 이 Base URI가 이 컨트롤러 클래스에 선언된 각 메소드의 위의
// 상세 URI와 결합(Base URI + Detail URI)되어, 어떤 요청을 위임받아 처리할지를
// 매핑하게됩니다.(Like Servlet url-pattern mapping)

@RequestMapping("/transfer/")	// Base URI

@RestController
//@Controller
public class TransferController {	//	계좌이체 서비스용 컨트롤러

		
	// Step1. 본인 확인 요청 처리 핸들러
//	@RequestMapping(value = "/checkIdentity", method= RequestMethod.GET)
	@GetMapping("/checkIdentity")					// OK: 실무에서는 단축형 어노테이션으로 사용
	Boolean handleIdentity() {						// 'Handler' (method called.)
		log.debug("handleIdentity() invoked.");
		
		return true;
	}// handleIdentity
	
	// Step2. 계좌 유효성 검증 요청 처리 핸들러
	// 아래와 같이 HTTP method 를 제약하지 않는 형태의 Request Mapping 에 대한
	// "단축형" 어노테이션은 없습니다 (***)
	// 또한 반대로, 2개 이상의 전송방식으로 제약하는 형태의 "단축형" 어노테이션도 없습니다.(***)
	@RequestMapping(value = "/checkAccountValidity")
	Boolean handleAccountValidity() {
		log.debug("handleAccountValidity() invoked.");
		return false;	// 유효하지 않은 계좌임
	}//handleAccountValidity
		
	// Step3. 소스 계좌의 잔고확인 요청 처리 핸들러
//	@RequestMapping(value ="/checkSourceAccountBalance", method = RequestMethod.POST)
	@PostMapping("/checkSourceAccountBalance")
	Boolean handleSourceAccountBalance() {
		log.debug("checkSourceAccountBalance() invoked.");
		
		return true;
	}// checkSourceAccountBalance
	
	// Step4. 이체 수행 요청 처리 핸들러
//	@RequestMapping(path = "/DoTransfer", 
//			method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST} 
//	)
	@GetMapping("/DoTransfer")
	@PostMapping("/DoTransfer")
	@DeleteMapping("/DoTransfer")	// 맨 처음에 나온 한개만 사용가능
	// head, trace : No support
	boolean handleTransfer() {
		log.debug("handleTransfer() invoked.");
		
		int money = 50000;
		int sourceAccount = 100000;
		int targetAccount = 100000;
		
		sourceAccount = sourceAccount - money; 
		targetAccount = targetAccount + money;
		
		
		return true;
	}//handleTransfer
	
	
}// end class
