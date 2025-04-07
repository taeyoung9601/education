package org.zerock.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j

@WebServlet( name = "CartDeleteServlet", urlPatterns = "/CartDelete")
public class CartDeleteServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{}) invoked", req.getClass(), res.getClass());
		
try {// 세션 금고 상자(= Session Scope)에 저장된 장바구니(Set collection)을 삭제
	// 1st. method: Set collection 만 삭제 -> 세선ID + 금고상자(=Session Scope)은 유효
	// 2nd. method: 세션금고상자(=Session Scope)자체를 삭제 -> 세션 ID도 무효화

	//--
	//-- 요청처리
	//----------
	HttpSession sess = req.getSession(false);	// 이 서블릿에서는 세션에 관한한 아무 작업도 하면 안됨
	if(sess == null) {
		res.sendError(400, "No available session found."); 	// Bad Request
	}
	
	// 1st. method
//	sess.removeAttribute(CART_KEY); // 장바구니만 삭제
			
	// 2nd. method
	// 세션 ID + 세션스코프(=금고상자) + 금고상자안에 들어있는 모든 공유 속성 삭제
	sess.invalidate();
	
	//--
	//-- 응답처리
	//----------
	// 아래 2라인 boilerplate code가 지겨우면, 앞에 필터를 만들고,
	// 필터의 전처리에서, 아래 코드를 공통로직으로 수행
	res.setCharacterEncoding("utf8");
	res.setContentType("text/html; charset=utf8");
	
	@Cleanup PrintWriter out = res.getWriter();
	
	out.println("");
	
	out.flush();
	
		}catch(Throwable t) {
			throw new IOException(t);
		}
		
	}// service

	
}// end class
