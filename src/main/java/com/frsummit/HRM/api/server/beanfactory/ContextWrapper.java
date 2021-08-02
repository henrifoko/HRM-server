package com.frsummit.HRM.api.server.beanfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ContextWrapper {

	private static ConfigurableApplicationContext context;

	@Autowired
	public ContextWrapper(ConfigurableApplicationContext ac) {
		context = ac;
	}

	public static ConfigurableApplicationContext getContext() {
		return context;
	}

}
