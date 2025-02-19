package org.zerock.myapp.listener;

import java.util.Map;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2

// Class contains `required fields`, You have to `force` NoArgsConstructor. (***)
@NoArgsConstructor

@WebListener
public final class RequestListener implements ServletRequestListener {


    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        
        final String requestURI = request.getRequestURI();
        if(requestURI.contains("favicon.ico")) return;
        
        final String requestContextPath = ("".equals(request.getContextPath().trim())) ? "/" : request.getContextPath();
        final String requestId = request.getRequestId();
        final String requestedSessionId = request.getRequestedSessionId();

        String format = "* Request Initialized (RID: %s, SID: %s, ContextPath: %s)".formatted(requestId, requestedSessionId, requestContextPath);
        log.trace(""); 
        log.trace("*".repeat(87)); 
        log.trace(format); 
        log.trace("*".repeat(87));
        
        final String httpMethod = request.getMethod();
        Map<String, String[]> requestParamMap = request.getParameterMap();

        log.trace("1. HTTP Method: {}, Request URI: {}", httpMethod, requestURI);
        log.trace("2. Request Parameters: {}", requestParamMap);
        log.trace("");
    } // requestInitialized

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        String uri = request.getRequestURI();
        if(uri.contains("favicon.ico")) return;

        boolean requestedSessionIdValid = request.isRequestedSessionIdValid();
        final String requestId = request.getRequestId();
        final  String requestedSessionId = request.getRequestedSessionId();

        String format = "* Request Destroyed   (RID: %s, SID: %s, IsValidSID: %s)".formatted(requestId, requestedSessionId, requestedSessionIdValid);
        
        log.trace("*".repeat(87));
        log.trace(format);
        log.trace("*".repeat(87));
    } // requestDestroyed

} // end class
