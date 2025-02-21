package org.zerock.myapp.tools;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor
public class CommonAppEventListener implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		log.debug(">>> onApplicationEvent({})", event.getClass().getSimpleName());
	} // onApplicationEvent

} // end class

