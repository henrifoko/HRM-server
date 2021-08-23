package com.frsummit.HRM.api.server.converions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.frsummit.HRM.api.server.exception.PostProcessingException;
import com.frsummit.HRM.api.server.util.ModelConverter;

/**
 * 
 */
public class PostprocessingPojoStrategy implements IPostprocessingStrategy {

    /**
     * 
     */
    @Override
    public Object process( Object data ) throws PostProcessingException {
        Method method;
        Object result = null;
        try {
            method = ModelConverter.class.getMethod( "getApiVersion", data.getClass() );
            try {
                result = method.invoke( null, data.getClass() );
            } catch ( IllegalAccessException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new PostProcessingException();

            } catch ( IllegalArgumentException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new PostProcessingException();

            } catch ( InvocationTargetException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new PostProcessingException();

            }
        } catch ( NoSuchMethodException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new PostProcessingException();

        } catch ( SecurityException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new PostProcessingException();

        }
        return result;
    }

}
