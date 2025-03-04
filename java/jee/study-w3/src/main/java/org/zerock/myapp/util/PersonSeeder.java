package org.zerock.myapp.util;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j

@Component
public class PersonSeeder {
	@Autowired private JdbcTemplate template;
	
	public void insertData() {
		log.debug("insertData() invoked.");
		
		// 1. DQL: JdbcTemplate.query(), 2: DML: JdbcTemplate.update, 3. ALL SQL: JdbcTemplate.execute();
		this.template.execute("TRUNCATE TABLE person");
		
		IntStream.rangeClosed(1, 30).forEach(seq -> {
			String name = "NAME_"+ seq;
			Integer age = 23 + seq;
			this.template.execute("INSERT INTO person (name,age) VALUES ('%s',%d)".formatted(name,age));
		});// forEach
		
		
	}// insertData
	
}
