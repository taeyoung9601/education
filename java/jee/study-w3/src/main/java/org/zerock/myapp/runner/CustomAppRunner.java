package org.zerock.myapp.runner;

import java.sql.Connection;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@Component
public class CustomAppRunner implements ApplicationRunner{
	@Autowired private DataSource dataSource;
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.debug("run() invoked.");
		
		log.info("\t+ this.dataSource:{}", this.dataSource);
		log.info("\t+ this.dataSource type:{}", this.dataSource.getClass().getName());
		
		Objects.requireNonNull(this.dataSource);
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		log.info("\t + conn : {}", conn);
		
	}
	
	
	
}// end class
