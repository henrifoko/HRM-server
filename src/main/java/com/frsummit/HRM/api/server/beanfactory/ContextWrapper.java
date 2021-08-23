package com.frsummit.HRM.api.server.beanfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 
 * @author hfoko
 *
 */
@Service
public class ContextWrapper {

    private static ConfigurableApplicationContext context;

    /**
     * 
     * @param ac
     */
    @Autowired
    public ContextWrapper( ConfigurableApplicationContext ac ) {
        context = ac;
    }

    /**
     * 
     * @return
     */
    public static ConfigurableApplicationContext getContext() {
        return context;
    }

}
