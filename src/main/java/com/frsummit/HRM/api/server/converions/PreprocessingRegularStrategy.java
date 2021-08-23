package com.frsummit.HRM.api.server.converions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.frsummit.HRM.api.server.config.GlobalConfig;
import com.frsummit.HRM.api.server.exception.PreProcessingException;
import com.frsummit.HRM.api.server.util.ModelConverter;

/**
 * 
 * @author hfoko
 *
 */
public class PreprocessingRegularStrategy implements IPreprocessingStrategy {

    /**
     * 
     */
    @Override
    public Object[] process( Object[] data ) throws PreProcessingException {
        Object[] result = new Object[data.length];
        Object elt;
        for ( int i = 0, l = data.length; i < l; i++ ) {
            elt = data[i];
            if ( isPrimitive( data[i] ) ) {
                result[i] = data[i];
            } else if ( isEntity( elt ) ) {
                Method method;
                try {
                    method = ModelConverter.class.getMethod( "getServerVersion", elt.getClass() );
                    try {
                        result[i] = method.invoke( null, elt );
                    } catch ( IllegalAccessException e ) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        throw new PreProcessingException();

                    } catch ( IllegalArgumentException e ) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        throw new PreProcessingException();

                    } catch ( InvocationTargetException e ) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        throw new PreProcessingException();

                    }
                } catch ( NoSuchMethodException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw new PreProcessingException();

                } catch ( SecurityException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw new PreProcessingException();

                }
            } else {
                result[i] = data[i];
            }
        }
        return result;
    }

    /**
     * 
     */
    private boolean isPrimitive( Object obj ) {
        return obj.getClass().isPrimitive();
    }

    /**
     * 
     */
    private boolean isEntity( Object obj ) {
        return GlobalConfig.ENTITY_PACKAGE_NAME.equals( obj.getClass().getPackageName() );
    }

}
