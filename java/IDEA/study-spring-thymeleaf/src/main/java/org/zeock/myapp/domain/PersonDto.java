package org.zeock.myapp.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class PersonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Integer age;
}
