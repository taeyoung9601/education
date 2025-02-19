package org.zerock.myapp.service;

import org.zerock.myapp.exception.UnknownCommandException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j

public class UnknownService implements CommandService{
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws UnknownCommandException{
		log.debug("execute({}) invoked.", req, res);

	}// execute
	
}// end class





//try {
//	//--0----
//	// create command가 수행될 때마다, 신규사원정보를 무작위로 생성해서
//	// DTO 안에 저장하도록 처리합니다.
//	int randomEmpno = new Random().nextInt(2000,3000);
//	dto.setEmpno(randomEmpno);
//	
//	int[] deptno= {10,20,30,40};
//	// 참고로, ThreadLocalRandom == Random 하고 거의 유사한 메소드로 구성
//	int index = ThreadLocalRandom.current().nextInt(deptno.length);
//	dto.setDeptno(deptno[index]);
//	
//	String[] ename = {"Tae", "Young", "Kim"};
//	index = new Random().nextInt(ename.length);
//	dto.setEname(ename[index]);
//	
//	String[] job = {"MANAGER", "SALESMAN", "CLERK", "ANALYST", "PRESIDENT"};
//	index = ThreadLocalRandom.current().nextInt(job.length);
//	
//	dto.setSal(ThreadLocalRandom.current().nextInt(30000,40000)*1.0);
//	
//	//--1----	
//	EmpDAO dao = new EmpDAO();
//	log.info("\t + dao : {}", dao);
//	
//	//--2----
//	boolean isDeleted = dao.remove(dto.getEmpno()); 	// Model
//	log.info("\t+ foundVO:{}" ,isDeleted);
//	
//	//--3---		
//	ModelAndView<String, Object> modelAndView = new ModelAndView<>();
//	modelAndView.addAttribute(MODEL_KEY, isDeleted);
//	modelAndView.addAttribute(VIEW_KEY, "mvc/response");
//	
//	log.info("\t3. modelAndView:{}", modelAndView);
//	
//	
//	return modelAndView;
//}catch(Exception e) {
//	throw new DeleteServiceException(e);
//}// try-catch


