package com.frsummit.HRM.api.server.beanfactory;

import com.frsummit.HRM.api.server.exception.BeanNotFoundException;

/**
 * 
 * @author hfoko
 *
 */
public interface BeanFactory {

    /**
     * 
     * @param type
     * @return
     * @throws BeanNotFoundException
     */
    public Object getInstance( Class<?> type ) throws BeanNotFoundException;
}
