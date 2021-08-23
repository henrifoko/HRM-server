package com.frsummit.HRM.api.server.shared.command;

import java.io.Serializable;

import com.frsummit.HRM.api.server.converions.IPostprocessingStrategy;
import com.frsummit.HRM.api.server.converions.IPreprocessingStrategy;
import com.frsummit.HRM.api.server.shared.InvokerPostprocessingStrategyEnum;
import com.frsummit.HRM.api.server.shared.InvokerPreprocessingStrategyEnum;

/**
 * The Command class contains all the information about the commands to run on
 * the server side. The method to execute and some information about how the
 * parameters should be preprocessed and how the result should be postprocessed.
 * 
 * @author hfoko
 * @see IPostprocessingStrategy
 * @see IPreprocessingStrategy
 */
public class Command implements Serializable {

    private static final long                 serialVersionUID = 6075035004943250991L;

    private String                            beanClassName;
    private String                            methodName;
    private String[]                          paramTypes;
    private Object[]                          params;

    // ========================================
    private InvokerPreprocessingStrategyEnum  preprocessingStrategy;
    private InvokerPostprocessingStrategyEnum postprocessingStrategy;

    /**
     * Unique constructor of the class Command.
     * 
     * @author hfoko
     * @param beanClassName
     *            Class name of the bean containing the remote method to execute
     * @param methodName
     *            The method of the bean that should be execute for providing
     *            the service to the client
     * @param paramTypes
     *            Types of parameters of the method on the server side
     * @param params
     *            Array of parameters of the method, this parameters will be
     *            preprocessed before sending them to the method
     * @param preprocessingStrategy
     *            Preprocessing strategy that will be use for preprocessing
     *            parameters
     * @param postprocessingStrategy
     *            Postprocessng strategy that will be use for postprocessing
     *            object resulting from the execution
     */
    public Command(
            String beanClassName,
            String methodName,
            String[] paramTypes,
            Object[] params,
            InvokerPreprocessingStrategyEnum preprocessingStrategy,
            InvokerPostprocessingStrategyEnum postprocessingStrategy ) {
        this.beanClassName = beanClassName;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;

        // ========================================
        this.preprocessingStrategy = preprocessingStrategy;
        this.postprocessingStrategy = postprocessingStrategy;
    }

    /**
     * Getter of the beanClassName property of the Command class
     * 
     * @return beanClassName
     */
    public String getBeanClassName() {
        return beanClassName;
    }

    /**
     * Setter of the beanClassName property of the Command class
     * 
     * @param beanClassName
     *            className to set
     */
    public void setBeanClassName( String beanClassName ) {
        this.beanClassName = beanClassName;
    }

    /**
     * Getter of the methodName property of the Command class
     * 
     * @return beanClassName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Setter of the methodName property of the Command class
     * 
     * @param methodName
     *            methodName to set.
     */
    public void setMethodName( String methodName ) {
        this.methodName = methodName;
    }

    /**
     * Getter of the paramTypes property of the Command class
     * 
     * @return paramTypes
     */
    public String[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes( String[] paramTypes ) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams( Object[] params ) {
        this.params = params;
    }

    // ========================================
    public InvokerPreprocessingStrategyEnum getPreprocessingStrategy() {
        return preprocessingStrategy;
    }

    public void setPreprocessingStrategy( InvokerPreprocessingStrategyEnum preprocessingStrategy ) {
        this.preprocessingStrategy = preprocessingStrategy;
    }

    public InvokerPostprocessingStrategyEnum getPostprocessingStrategy() {
        return postprocessingStrategy;
    }

    public void setPostprocessingStrategy( InvokerPostprocessingStrategyEnum postprocessingStrategy ) {
        this.postprocessingStrategy = postprocessingStrategy;
    }

}