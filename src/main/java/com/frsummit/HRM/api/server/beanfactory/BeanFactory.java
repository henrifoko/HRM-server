package com.frsummit.HRM.api.server.beanfactory;

import com.frsummit.HRM.api.server.exception.BeanNotFoundException;

public interface BeanFactory {

	public Object getInstance(Class<?> type) throws BeanNotFoundException;
}
