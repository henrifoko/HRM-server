package com.frsummit.HRM.api.server.converions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.frsummit.HRM.api.server.exception.PostProcessingException;
import com.frsummit.HRM.api.server.util.ModelConverter;

/**
 * 
 * @author hfoko
 *
 */
public class PostprocessingPojoListStrategy implements IPostprocessingStrategy {

    /**
     * 
     * @param pojoList
     */
    @Override
    public Object process( Object pojoList ) throws PostProcessingException {
        List<Object> resultList = new ArrayList<>();
        Iterable<?> collection = null;
        if ( pojoList instanceof Iterable<?> ) {
            collection = (Iterable<?>) pojoList;
        }
        Object tempResult = null;
        Method method;
        for ( Object item : collection ) {
            try {
                method = ModelConverter.class.getMethod( "getApiVersion", item.getClass() );
                try {
                    tempResult = method.invoke( null, item );
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
            resultList.add( tempResult );
        }
        return resultList;
    }

}
