package org.zerock.myapp.annotation;

//@MyAnnotation								// T14 : OK
/* @MyAnnotation */ public				// T14-1: OK 
// @MyAnnotation					// T14-2: OK
enum	/* @MyAnnotation - XX */ 
Week {
	@MyAnnotation 								// T15-1 : OK
	MONDAY, 
	@MyAnnotation SUNDAY 				// T15-2 : OK
	
} // end enum
