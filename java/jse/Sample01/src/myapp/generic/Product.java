package org.zerock.myapp.generic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Log4j2

@ToString
@Setter
@Getter
@NoArgsConstructor
public class Product<K, M> {
	private K kind;
	private M model;
	
	
} // end class
