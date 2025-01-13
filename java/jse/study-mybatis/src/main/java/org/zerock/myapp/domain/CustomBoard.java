package org.zerock.myapp.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
@ToString

public class CustomBoard {
	Integer id;
	String bTitle;
	String bWriter;
	Date createdDate;
	Date updatedDate;
	String bContent;
	
	
	public void setMyId(Integer myId) {
		log.debug("setMyId({}) invoked.", myId);
		this.id=myId;
	}
	
	public void setMyTitle(String myTitle) {
		log.debug("setMyTitle({}) invoked.", myTitle);
		this.bTitle=myTitle;
	}
	
	public void setMyWriter(String myWriter) {
		log.debug("setMyWriter({}) invoked.", myWriter);
		this.bWriter=myWriter;
	}
	
	public void setMyCreatedDate(Date myCreatedDate) {
		log.debug("setMyCreatedDate({}) invoked.", myCreatedDate);
		this.createdDate=myCreatedDate;
	}
	
	public void setMyUpdatedDate(Date myUpdatedDate) {
		log.debug("setMyUpdatedDate({}) invoked.", myUpdatedDate);
		this.updatedDate=myUpdatedDate;
	}
	
	public void setMyContent(String myContent) {
		log.debug("setMyContent({}) invoked.", myContent);
		this.bContent=myContent;
	}
}
