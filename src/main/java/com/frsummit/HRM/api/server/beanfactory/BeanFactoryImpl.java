package com.frsummit.HRM.api.server.beanfactory;

import com.frsummit.HRM.api.server.exception.BeanNotFoundException;

/**
 * 
 * @author hfoko
 *
 */
public class BeanFactoryImpl implements BeanFactory {

    /**
     * @author hfoko
     * 
     *         This is the description of the method
     * @param name
     * @param type
     * @return
     * @throws ClassNotFoundException
     */
    public Object getInstance( Class<?> type ) throws BeanNotFoundException {
        Object bean = ContextWrapper.getContext().getBean( type );

        return bean;
    }
}
