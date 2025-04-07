package org.zerock.myapp.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.zerock.myapp.util.MultipartUpload;
import org.zerock.myapp.util.UUIDGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor

@MultipartConfig(
	// 1. 업로드될 파일을 저장할 경로지정.
	//      기본값: 빈 문자열 -> 메모리에 저장
//	location = "C:/temp/upload",
	location = MultipartUpload.location,
	// 2. 전체요청의 최대크기 지정(바이트)
	//     HTTP request 에 포함된 모든 파일들크기 + 요청파라미터들의 합산크기가 이 지정된 크기를
	//     초과하면 업로드 거부
	maxRequestSize = 1024 * 1024 * 20 * 1L,		// 총 20MB = 1바이트 x 1024개 (1KB) x 1024개 (1MB) x 20 = 20MB
	// 3. 업로드할 각 파일의 최대크기 설정(바이트)
	//     이 크기를 초과한 파일이 있으면, 업로드 거부
	//     기본값: -1L => Unlimited size.
	maxFileSize = 1024 * 1024 *5 * 1L,			// 각 업로드 파일의 최대크기 5MB 설정
	// 4. 업로드할 각 파일의 크기가 이 임계값을 초과하면     -> 디스크 활용(저장)
	//                                  초과안하면 -> 메모리에 저장
	//     기본값: 0. 모든 업로드 파일들을 디스크에 저장하는 방식으로 처리 (단위: 바이트)
	//     이 임계치를 이용하여, 한정된 서버메모리를 효율적으로 사용하기 위함
	fileSizeThreshold = 0		// 모든 업로드 파일들은 디스크에 저장 -> location 폴더로 이동
)

@WebServlet("/FileUpload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({}, {}) invoked.", req, res);
		
		try {		// 멀티파트 처리 - Servlet container 가 제공하는 기능을 사용해서...
			// -- 1 ---------------
			req.setCharacterEncoding("utf8");

			// -- 2 ---------------
			String writer = req.getParameter("writer");
			log.info("\t+ writer: {}", writer);

			// -- 3 ---------------
			Collection<Part> multiPart = req.getParts();
			
			// -- 4 ---------------
			multiPart.forEach(part -> {
				// -- 4-1 -----------------
				final String name = part.getName();									// Request Parameter Name (요청파라미터이름)
				final long size = part.getSize();									// 이 파트에 저장된 파일의 크기
				final String contentType = part.getContentType();					// 이 파트에 저장된 파일의 MIMETYPE (예: text/plain)
				final String submittedFileName = part.getSubmittedFileName();		// 이 파트에 저장된 업로드 파일의 이름
				final Collection<String> headerNames = part.getHeaderNames();		// 모든 파트의 헤더명의 목록을 획득

				// -- 4-2 -----------------
				log.info("=".repeat(50));
				log.info("\t1. name: {}", name);
				log.info("\t2. size: {} bytes", size);
				log.info("\t3. contentType: {}", contentType);
				log.info("\t4. submittedFileName: {}", submittedFileName);
				headerNames.forEach(h -> log.info("\t5. {} : {}", h, part.getHeader(h)));

				// -- 4-3 -----------------
				if("uploadFile".equals(name) && !"".equals(submittedFileName)) {
					// 요청파라미터 이름이 uploadFile 이고, 실제 전송된 파일명이 있는
					// 파트에 대해서, @MultipartConfig(location) 위치에 파일저장
					
					// 과제1: 현재날짜폴더가 있으면 -> 저장, 없으면 -> 날짜폴더 생성 -> 저장
					// 과제2:  저장파일명은, 원본파일로 저장하지 않고, UUID 생성해서 저장할 것!
					
					// -- 5-1. Logging CWD(Current Working Directory) Path.
					log.info("\t+ cwd: {}", Paths.get("").toAbsolutePath());
					
					// -- 5-2. Create an upload target directory with today.
					final String today = new SimpleDateFormat("yyyyMMdd").format(new Date());								
					File todayPath = new File(MultipartUpload.location+File.separator+today);
					log.info("\t+ todayPath: {}", todayPath);
					todayPath.mkdirs();
					
					try {
						// -- 5-3. Create an UUID
						final String uuid = UUIDGenerator.generateUniqueKeysWithUUIDAndMessageDigest();
						// -- 5-4. Create an uploaded file with UUID to prevent file overwriting.
						part.write(todayPath + File.separator + uuid);	// C:/temp/upload/yyyyMMdd/UUID 파일저장
						// -- 5-5. @MultipartConfig(fileSizeThreshold) 에 의해 생긴 임시파일삭제	
						part.delete(); 	
					} catch (Exception e) {
						e.printStackTrace();
					}
				} // if
			}); // forEach
		} catch(Throwable t) {
			throw new IOException(t);
		} // try-catch
	} // service

} // end class