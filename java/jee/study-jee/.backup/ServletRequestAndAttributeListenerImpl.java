package org.zerock.myapp.listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@WebListener
public class ServletRequestAndAttributeListenerImpl implements ServletRequestAttributeListener, ServletRequestListener {

	@Override
    public void requestInitialized(ServletRequestEvent event)  { 
    	log.debug("requestInitialized({}) invoked." , event);
    	
    	HttpServletRequest req = (HttpServletRequest)event.getServletRequest();
    	String requestId = req.getRequestId();
    	
    	
    	log.info("[{}] === 새로운 요청이 들어왔습니다.", requestId);
    }

	@Override
    public void requestDestroyed(ServletRequestEvent event)  { 
    	log.debug("requestDestroyed({}) invoked." , event);
    	
    	String requestId = event.getServletRequest().getRequestId();
    	log.info("[{}] === 요청 처리 완료.", requestId);
    }
	
	
	@Override
    public void attributeAdded(ServletRequestAttributeEvent event)  { 
    	log.debug("attributeAdded({}) invoked." , event);
    	
    	final String key = event.getName();
    	Object value = event.getValue();				// 다형성-1
    	final String requestId = event.getServletRequest().getRequestId();
    	
    	log.info("\t+-----------------");
    	log.info("\t Binding - RequestId({}),key({}),value({}),type({})",requestId ,key,value,value.getClass().getName());
    	log.info("\t+-----------------");
    }
	
	
	@Override
    public void attributeReplaced(ServletRequestAttributeEvent event)  { 
    	log.debug("attributeReplaced({}) invoked." , event);
    	
    	final String key = event.getName();
    	Object value = event.getValue();				// 다형성-1
    	final String requesId = event.getServletRequest().getRequestId();
    	
    	log.info("\t+-----------------");
    	log.info("\t ReBinding - RequestId({}),key({}),value({}),type({})",requesId,key,value,value.getClass().getName());
    	log.info("\t+-----------------");
    	
    }
	
	
	@Override
    public void attributeRemoved(ServletRequestAttributeEvent event)  { 
    	log.debug("attributeRemoved({}) invoked." , event);
    	
    	final String key = event.getName();
    	Object value = event.getValue();
    	final String requesId = event.getServletRequest().getRequestId();
    	
    	log.info("\t+-----------------");
    	log.info("\t UnBinding - RequestId({}),key({}),value({}),type({})",requesId,key,value,value.getClass().getName());
    	log.info("\t+-----------------");
    }
	
	
}
