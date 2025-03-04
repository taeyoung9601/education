package org.zerock.myapp.controller;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.MessageDTO;
import org.zerock.myapp.domain.MessageVO;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@RequestMapping("/message")
@RestController
public class MessageController {
	
//	@Autowired
//	@Resource(name ="jdbcTemplate") 
//	@Resource(type =JdbcTemplate.class) 
	@Resource(name ="jdbcTemplate", type =JdbcTemplate.class)
	private JdbcTemplate jdbcTemplate;
//	public MessageController(String name, int age) {
//		
//	} 생성자는 개발자가 아닌 스프링부트에서 생성하기때문에 개발자가 원하는 매개변수가 있는 생성자를 스프링부트에선 만들어주질못함
	
	@PostConstruct
	void postConstruct() {
		log.debug("postConstruct() invoked.");
		
		log.info("\t + this.jdbcTemplate:{}",this.jdbcTemplate);
	}
	
	@GetMapping("/getMessages")
	List<MessageVO> getMessages() {
		log.debug("getMessage() invoked.");
		
		final String sql = "SELECT id,text FROM message ORDER BY id DESC";
		
		// 
		List<MessageVO> list= this.jdbcTemplate.<MessageVO>query(sql,(rs,i) -> { 
			log.debug("mapRow({},{}) invoked.", rs,i);
			MessageVO vo = new MessageVO(rs.getInt("id"), rs.getString("text"));
			
			return vo;
			});
		
		return list;
	}// getMessages
	
	@PostMapping("/postMessages")
	boolean postMessage(@RequestBody MessageDTO dto){
		log.debug("postMessage({}) invoked.", dto);
		
//		this.jdbcTemplate.insert();
//		this.jdbcTemplate.delete();
		
		// IF DML, use 'update()' method.
		int affectedRows = this.jdbcTemplate.update("INSERT INTO message (text) VALUES(?)", dto.getText());
		
		return (affectedRows == 1);
	}// postMessage
	
	
}// end class
