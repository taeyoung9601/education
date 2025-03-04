package org.zerock.myapp.runner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.zerock.myapp.persistence.EmpMapper;

import jakarta.annotation.Resource;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@Component
public class CustomCLIRunner implements CommandLineRunner{
	@Autowired private SqlSessionFactory sqlSessionFactory;
	@Autowired private SqlSession sqlSession;
	@Autowired private SqlSessionTemplate sqlSessionTemplate;
//	@Autowired 
//	@Resource(type = EmpMapper.class) 						// 빈의 타입에 매칭되는 빈을 주입
//	@Resource(name = "empMapper")							// 빈의 이름으로 주입
	@Resource(name = "empMapper", type = EmpMapper.class)	// 이름 + 타입
	private EmpMapper empMapper;

	@Override
	public void run(String... args) throws Exception {
		log.debug("run() invoked.");
		
		log.info("\t1+ this.sqlSessionFactory:{}", this.sqlSessionFactory);
		log.info("\t2+ this.sqlSession:{}", this.sqlSession);
		log.info("\t3+ this.sqlSessionTemplate:{}", this.sqlSessionTemplate);
		log.info("\t4 + this.empMapper:{}", this.empMapper);
	}// run
	
	
	
}// end class
