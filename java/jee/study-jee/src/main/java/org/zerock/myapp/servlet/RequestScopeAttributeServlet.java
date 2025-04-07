package org.zerock.myapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serial;

@Slf4j
@NoArgsConstructor

@WebServlet(name = "RequestScopeAttributeServlet", urlPatterns = "/RequestScopeAttribute")
public class RequestScopeAttributeServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("service({},{} invoked.", req.getClass().getSimpleName(), res.getClass().getSimpleName());
	

	try {
		final String key = "_KEY_";
		final String value1 = "VALUE1";
		final String value2 = "VALUE2";
		
		req.setAttribute(key, value1);		// Binding, Re-binding
		req.getAttribute(key);				// Get the specified Attribute
		req.removeAttribute(key);			// Un-binding
		
		// --1: Binding -------
		req.setAttribute(key, value1);		// Bind this attribute into the Request Scope.
		
		// --2: Re-binding-----
		req.setAttribute(key, value2);		// Re-bind this attribute with new value into the Request Scope.
		
		// --3: Unbinding-----
		req.removeAttribute(key);			// Un-bind this attribute from the Request Scope.
		
	}catch(Throwable t) {
		throw new ServletException(t);
	}// try-catch
	
	}// service

}// end class
