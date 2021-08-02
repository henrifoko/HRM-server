package com.frsummit.HRM.api.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.frsummit.HRM.api.server.config.GlobalConfig;
import com.frsummit.HRM.api.server.config.GlobalConstance;
import com.frsummit.HRM.api.server.util.ModelConverter;

public class PreProcessingRegularStrategy implements PreProcessingStrategy {

    @Override
    public Object[] process( Object[] data ) {
        Object[] result = new Object[data.length];
        Object elt;
        for ( int i = 0, l = data.length; i < l; i++ ) {
            elt = data[i];
            if ( isPrimitive( data[i] ) ) {
                result[i] = data[i];
            } else if ( isEntity( elt ) ) {
                Method method;
                try {
                    method = ModelConverter.class.getMethod( "getApiVersion", elt.getClass() );
                    try {
                        result[i] = method.invoke( null, result );
                    } catch ( IllegalAccessException e ) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch ( IllegalArgumentException e ) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch ( InvocationTargetException e ) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } catch ( NoSuchMethodException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch ( SecurityException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                result[i] = data[i];
            }
        }
        return result;
    }

    private boolean isPrimitive( Object obj ) {
        if ( GlobalConstance.PRIMITIVE_TYPES.containsKey( obj.getClass().getName() ) ) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isEntity( Object obj ) {
        if ( GlobalConfig.ENTITY_PACKAGE_NAME.equals( obj.getClass().getPackageName() ) ) {
            return true;
        } else {
            return false;
        }
    }

}
