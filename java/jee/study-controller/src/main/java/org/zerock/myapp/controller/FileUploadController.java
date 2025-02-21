package org.zerock.myapp.controller;

import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

// 응용: 파일 업로드 컨트롤러와 업로드 처리하는 핸들러 구현
@RequestMapping("/fileUpload/*")		// Base URI

@Controller
public class FileUploadController {
	 private final String uploadDir = "C:/Temp/upload/";
	// 아래의 핸들러와 같이, 요청처리를 목적으로 하지 않고,
	// 단순히 뷰(템플릿엔진에 따라 JSP 또는 타임리프)를 응답으로 주는 핸들러를
	// 특별히 스프링에서는, "뷰컨트롤러(View-Controller)"라고 합니다.
	@GetMapping("/upload")
	void upload() {										// View-Controller
		log.debug("upload() invoked.");
		
		// 리턴타입이 없으니, Request URI가 곧 "뷰의이름"이 된다!
	} // upload
	
	// 실제 파일 업로드 처리하는 핸들러
	@PostMapping("/doit")
	void doit(
			// 요청파라미터 이름과 같은 이름의 매개변수 선언으로 해당 요청파라미터의
			// 값을 얻어낼 수 있다!(기본)
			/*@RequestParam("uploader")*/ String uploader,
			List<MultipartFile> files	// MultipartFile: Interface
	) {	// 일반 요청파라미터는 매개변수로 얻으면 된다!
		log.debug("doit({},{}) invoked.", uploader, files);
		
		// (주의사항) 스프링부트 설정에서, 파일업로드 설정시
		// spring.servlet.multipart.location = C:/temp 설정을 통해서, 업로드된 파일을
		// 저장할 폴더를 지정했죠. 하지만, 여기서 이 폴더는 업로드 처리를 위한
		// **임시폴더**이지, 최종 파일저장 경로가 아닙니다(***)
		// 스프링부트에서 파일업로드 될 최종 저장경로는, 개발자가 임의대로
		// 경로를 지정할 수 있도록, 프로그램 코드로 설정하도록 하였습니다.
		
		files.forEach(mf -> {
			log.info("1. name:{}", mf.getName());		// 1. 요청파라미터 이름 출력
			log.info("2. size: {}", mf.getSize());		// 2. 각 업로드될 파일의 크기
			log.info("3. isEmpty:{}", mf.isEmpty());	// 3. 현재 파트에 파일이 있는지/없는지 확인
			// 4. 현재 파트의 바디(Payload)에 있는 컨텐츠의 유형(파일 유형)을 확인
			log.info("4. contentType:{}", mf.getContentType());
			log.info("5. originalFilename", mf.getOriginalFilename());
			log.info("=".repeat(30));
	         
	        // 6. 실제 개발자가 임의로 지정한 경로에 각 파트의 파일 저장 처리
	        if(!mf.isEmpty()) {
	        	try { 
	        		// target: File 또는 Path 타입의 객체로, 저장될 파일의 경로를 지정해야한다.
	        		// (1) File 객체로 target 지정
	        		// (2) Path 객체로 target 지정
	               
	        		// (1) 
	        		//File target = new File(uploadDir + mf.getOriginalFilename());
	               
	        		// (2) 
	        		Path target = Path.of(uploadDir + mf.getOriginalFilename());
	               
	               
                	// 지정된 경로의 파일로 최종 저장(임시파일에저장후 ->
                	// 임시파일을 target 경로로 이동 및 원본파일로 이름변경
                	// (Atomic Job)
                	mf.transferTo(target);}
	            catch(Exception e) {
	            	e.printStackTrace();
	            } // try-catch
	            
	         } // if

		});	// List Collection Traverse Stub Code
		
	} // doit
	
	
	

} // end class
