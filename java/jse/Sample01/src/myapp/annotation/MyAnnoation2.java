package org.zerock.myapp.annotation;


// 서로 다른 어노테이션은 하나의 타입에 N개를 붙일 수가 있습니다.
/* 예:
@ToString
@Setter
@Getter
@Log4j2
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
*/

//@MyAnnoation2						// 같은 어노테이션은 1개만 허용

// 어노테이션은 아래와 같이 가로로 배열해서 붙여도 허용됩니다.
@MyAnnoation2 @MyAnnotation
public @interface MyAnnoation2 {
	;;
} // end annotation
