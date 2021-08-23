package com.frsummit.HRM.api.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;

import com.frsummit.HRM.api.server.beanfactory.BeanFactory;
import com.frsummit.HRM.api.server.beanfactory.BeanFactoryImpl;
import com.frsummit.HRM.api.server.config.GlobalConstance;
import com.frsummit.HRM.api.server.converions.IPostprocessingStrategy;
import com.frsummit.HRM.api.server.converions.IPreprocessingStrategy;
import com.frsummit.HRM.api.server.exception.ClientException;
import com.frsummit.HRM.api.server.exception.PostProcessingException;
import com.frsummit.HRM.api.server.exception.PreProcessingException;
import com.frsummit.HRM.api.server.exception.ServerException;

/**
 * 
 */
public class InvokerContext {

    /**
     * 
     */
    public static final long        serialVersionUID = 5367579065715128032L;

    /**
     * 
     */
    private IPreprocessingStrategy  preprocessingStrategy;

    /**
     * 
     */
    private IPostprocessingStrategy postprocessingStrategy;

    /**
     * 
     */
    private BeanFactory             beanFactory;

    /**
     * 
     */
    public InvokerContext() throws RemoteException {
        beanFactory = new BeanFactoryImpl();
    }

    /**
     * 
     */
    public IPreprocessingStrategy getPreprocessingStrategy() {
        return preprocessingStrategy;
    }

    /**
     * 
     */
    public void setPreprocessingStrategy( IPreprocessingStrategy preprocessingStrategy ) {
        this.preprocessingStrategy = preprocessingStrategy;
    }

    /**
     * 
     */
    public IPostprocessingStrategy getPostprocessingStrategy() {
        return postprocessingStrategy;
    }

    /**
     * 
     */
    public void setPostprocessingStrategy( IPostprocessingStrategy postprocessingStrategy ) {
        this.postprocessingStrategy = postprocessingStrategy;
    }

    /**
     * 
     * @param beanClassName
     * @param methodName
     * @param paramTypes
     * @param params
     * @param returnType
     * @return
     * @throws ServerException
     * @throws InvocationTargetException
     */
    public Object execTemplateMethod(
            String beanClassName,
            String methodName,
            String[] paramTypes,
            Object[] params )
            throws ServerException,
            ClientException {
        // Step 0
        // Initialization

        Object result = null;
        Object beanInstance = null;
        Method method = null;

        System.out.println( "Log + Initialisation des classes" );
        Class<?>[] classes = new Class[paramTypes.length];

        String className;
        for ( int i = 0, l = paramTypes.length; i < l; i++ ) {
            className = paramTypes[i];
            try {
                if ( GlobalConstance.PRIMITIVE_TYPES.containsKey( className ) ) {
                    classes[i] = GlobalConstance.PRIMITIVE_TYPES.get( className );
                } else {
                    classes[i] = Class.forName( className );
                }
            } catch ( ClassNotFoundException e ) {
                e.printStackTrace();

                throw new ServerException( "The class " + className + " was not found. Check parameter type.", e );
            }
        }

        System.out.println( "Log - Initialisation des classes terminée" );

        // Step 1
        // In the standard implementation, this step aims to convert the remote
        // version of the object received from the API to their local version on
        // the server
        Object[] conformParams = null;
        try {
            conformParams = preprocessingStrategy.process( params );
        } catch ( PreProcessingException e1 ) {
            e1.printStackTrace();

            throw new ServerException( "An error occurs during the preprocessing step.", e1 );
        }

        // Step 2
        // Get the bean from the container or from another source depending on
        // the implementation and the container on which this patch
        // is deployed
        try {
            beanInstance = getBean( beanClassName );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();

            throw new ServerException( "The class " + beanClassName + " was not found.", e );
        }

        // Step 3
        // Get the method object from the class name
        try {
            method = beanInstance.getClass().getMethod( methodName, classes );
        } catch ( NoSuchMethodException e ) {
            e.printStackTrace();

            throw new ServerException( "The method " + methodName + " was not found on the bean " + beanClassName + ".",
                    e );
        } catch ( SecurityException e ) {
            e.printStackTrace();

            throw new ServerException( "An security error occurs after trying to get the method object.", e );
        }

        // Step 4
        // Execute the business logic treatment using the bean instance and the
        // local version of the parameters obtained from the previous step
        try {
            if ( method != null ) {
                result = method.invoke( beanInstance, conformParams );
            } else {
                throw new ServerException( "Bean method object is null." );
            }
        } catch ( IllegalAccessException e ) {
            e.printStackTrace();

            throw new ServerException( "Tried to make an access to secured resource.", e );
        } catch ( IllegalArgumentException e ) {
            e.printStackTrace();

            throw new ServerException( "There is a problem with method params provided.", e );
        } catch ( InvocationTargetException e ) {
            e.printStackTrace();

            Throwable cause = e.getCause();
            if ( cause instanceof ClientException ) {
                throw (ClientException) cause;
            } else {
                throw new ServerException( "An error occur during the bean method execution.", cause );
            }
        }

        // Step 5
        // Conversion of the process result object to his remote version
        try

        {
            return postprocessing( result );
        } catch ( PostProcessingException e ) {
            e.printStackTrace();

            throw new ServerException( "Error during server postprocessing.", e );
        }
    }

    /**
     * This is the first step of the generic algorithm. The purpose of this
     * function is to perform a set of preliminary drags before performing the
     * processing requested by the client.
     * 
     * @param params<Object[]>
     *            - The parameters provided by the client on the call
     * @return Object[] - The parameters once converted by the preprocessor
     * @throws PreProcessingException
     */
    public Object[] preprocessing( Object[] params ) throws PreProcessingException {
        return preprocessingStrategy.process( params );
    }

    /**
     * Here, the goal is to retrieve the instance of the object on which the
     * reflexive call will execute. This can be done in several ways depending
     * on the environment on which the application is running. It can be
     * retrieved from the container that creates the instance when I start my
     * application (case of spring for example), or the instance can be created
     * at the time of its use.
     * 
     * @return Object - The bean of the class passed as a parameter
     * @throws ClassNotFoundException
     *             - This error is raised if the bean is not found in the
     *             container
     */
    public Object getBean( String beanClassName ) throws ClassNotFoundException {

        Class<?> beanClass = Class.forName( beanClassName );
        return beanFactory.getInstance( beanClass );
    }

    /**
     * The purpose of the postProcessing is to convert the data from the
     * business logic into remote objects that can be transferred via the RMI
     * channel. This method is generic. And will have different implementations
     * depending on the objects returned
     * 
     * @param -
     *            The server object which have to be converted
     * @return T - The result of the conversion
     * @throws PostProcessingException
     */
    public Object postprocessing( Object obj ) throws PostProcessingException {
        return this.postprocessingStrategy.process( obj );
    }

}
