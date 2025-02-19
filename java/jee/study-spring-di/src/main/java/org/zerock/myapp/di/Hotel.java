package org.zerock.myapp.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data

@Component
public class Hotel {

	@Autowired
	private Chef chef;
	
}
