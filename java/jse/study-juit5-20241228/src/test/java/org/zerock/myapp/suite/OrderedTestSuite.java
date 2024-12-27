package org.zerock.myapp.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import lombok.NoArgsConstructor;

//(주의) 테스트 공정은 여러 개의 테스트 시나리오로 구성되죠.
// 때문에, 아래의 테스트 수트(즉, 하나의 테스트 시나리오)를 통해
// 테스트 공정 설계대로, 여러 시나리오를 여러 테스트 수트를 통해서
// 그대로 만들고 실행시키고 검증할 수가 있게 됩니다.


//하나의 테스트 시나리오 역할을 하는 테스트 수트
@Suite 								//The annotation defines a Test Suite.
@SuiteDisplayName("My Test Suite")	//The name of a Test suite.

// 이 테스트 수트에 포함시킬, 테스트 케이스(즉, 테스트 클래스)를
// 필요한 만큼 포함(컨테이너에 저장) 시킵니다.

// 1st. method: include all required test classes with 'Clazz' objects.
@SelectClasses({   		// * Recommended
	Case1Tests.class,
	Case2Tests.class,
	Case3Tests.class,
})

// 2nd. method: include all required test classes with 'FQCN' strings.
//@SelectClasses(names = {
//		"org.zerock.myapp.suite.Case1Tests",
//		"org.zerock.myapp.suite.Case2Tests",
//		"org.zerock.myapp.suite.Case3Tests",
//})

//3rd. method : include all required test classes with 'package' strings.
//@SelectPackages({   				// *Not Recommended
//	"org.zerock.myapp.suite"
//}) // 3-> 1 -> 2, 즉 패키지로 포함시키면, 각 패키지 안의 테스트 클래스의
//	// 실행 순서는 보장되지 않습니다. (***)

@NoArgsConstructor
public class OrderedTestSuite { //Only container role.
	;; // Nothing to do.
}//end class
