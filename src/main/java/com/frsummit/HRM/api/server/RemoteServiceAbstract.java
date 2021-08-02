package com.frsummit.HRM.api.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.frsummit.HRM.api.server.beanfactory.BeanFactory;
import com.frsummit.HRM.api.server.beanfactory.BeanFactoryImpl;
import com.frsummit.HRM.api.server.config.GlobalConstance;

abstract public class RemoteServiceAbstract {

    /**
     * 
     */
    private PreProcessingStrategy  preProcessingStrategy;

    /**
     * 
     */
    private PostProcessingStrategy postProcessingStrategy;

    /**
     * 
     */
    private BeanFactory            beanFactory;

    public RemoteServiceAbstract() {

        beanFactory = new BeanFactoryImpl();
    }

    /**
     * 
     * @param <T>
     * @param daoClassName
     * @param methodName
     * @param paramTypes
     * @param params
     * @param returnType
     * @return
     */
    public <T> T execTemplateMethod(
            String beanClassName,
            String methodName,
            String[] paramTypes,
            Object[] params,
            Class<T> returnType ) {
        // Step 0
        // Initialization

        Object result = null;
        Class<?> beanClass;
        Object beanInstance;
        Method method;

        String absoluteMethodName = beanClassName + "." + methodName;
        String methodKey = absoluteMethodName;
        String daoInstanceKey = beanClassName;

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
                return null;
            }
        }
        System.out.println( "Log - Initialisation des classes terminée" );

        // Step 1
        // In the standard implementation, this step aims to convert the remote
        // version of the object received from the API to their local version on
        // the server
        Object[] conformParams = preProcessingStrategy.process( params );

        // Step 2
        // Get the bean from the container or from another source depending on
        // the implementation and the container on which this patch
        // is deployed
        beanInstance = getBean( beanClassName );

        // Step 3
        // Get the method object from the class name
        method = beanClass.getMethod( methodName, classes );

        // Step 4
        // Execute the business logic treatment using the bean instance and the
        // local version of the parameters obtained from the previous step
        result = method.invoke( beanInstance, params );

        // Step 5
        // Conversion of the process result object to his remote version
        return postProcessing( result );

        try {
            System.out.println( "Log + Récupération des instances" );
            System.out.println( "Log + Création d'une instance" );

            System.out.println( "Log - Création de l'instance terminée" );
            System.out.println( "Log - Récupération des instances terminée" );

            System.out.println( "Log + Invocation de la méthode" );

            System.out.println( "Log - Invocation de la méthode terminée" );
        } catch ( IllegalAccessException e ) {
            e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        } catch ( NoSuchMethodException e ) {
            e.printStackTrace();
        } catch ( SecurityException e ) {
            e.printStackTrace();
        } catch ( IllegalArgumentException e ) {
            e.printStackTrace();
        } catch ( InvocationTargetException e ) {
            e.printStackTrace();
        }

        // System.out.println(result);
        try {
            return fitObject( result, returnType );
        } catch ( ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException
                | IllegalAccessException e ) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * This is the first step of the generic algorithm. The purpose of this
     * function is to perform a set of preliminary drags before performing the
     * processing requested by the client.
     * 
     * @param params
     *            - The parameters provided by the client on the call
     * @return Object[] - The parameters once converted by the preprocessor
     */

    public Object[] preProcessing( Object[] params ) {
        return this.preProcessing( params );
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
     * @param <T>
     *            - The type of objects returned after processing
     * @return T - The result of the conversion
     */
    public <T> T postProcessing( Object obj ) {
        return this.postProcessing( obj );
    }

}
