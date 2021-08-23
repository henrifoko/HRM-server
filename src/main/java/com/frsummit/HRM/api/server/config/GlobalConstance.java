package com.frsummit.HRM.api.server.config;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.frsummit.HRM.api.server.converions.IPostprocessingStrategy;
import com.frsummit.HRM.api.server.converions.IPreprocessingStrategy;
import com.frsummit.HRM.api.server.converions.PostprocessingPojoListStrategy;
import com.frsummit.HRM.api.server.converions.PostprocessingPojoStrategy;
import com.frsummit.HRM.api.server.converions.PostprocessingRegularStrategy;
import com.frsummit.HRM.api.server.converions.PreprocessingRegularStrategy;
import com.frsummit.HRM.api.server.shared.InvokerPostprocessingStrategyEnum;
import com.frsummit.HRM.api.server.shared.InvokerPreprocessingStrategyEnum;

/**
 * 
 * @author hfoko
 *
 */
@Component
public class GlobalConstance {

    /**
     * 
     */
    public static final HashMap<String, Class<?>>                                           PRIMITIVE_TYPES         = new HashMap<String, Class<?>>();

    /** 
     *
     */
    public static final HashMap<InvokerPreprocessingStrategyEnum, IPreprocessingStrategy>   PRE_PROCESSING_METHODS  = new HashMap<InvokerPreprocessingStrategyEnum, IPreprocessingStrategy>();

    /**
     * 
     */
    public static final HashMap<InvokerPostprocessingStrategyEnum, IPostprocessingStrategy> POST_PROCESSING_METHODS = new HashMap<InvokerPostprocessingStrategyEnum, IPostprocessingStrategy>();

    /**
     * 
     */
    public GlobalConstance() {
        PRIMITIVE_TYPES.put( "int", int.class );
        PRIMITIVE_TYPES.put( "byte", byte.class );
        PRIMITIVE_TYPES.put( "long", long.class );
        PRIMITIVE_TYPES.put( "char", char.class );
        PRIMITIVE_TYPES.put( "void", void.class );
        PRIMITIVE_TYPES.put( "short", short.class );
        PRIMITIVE_TYPES.put( "float", float.class );
        PRIMITIVE_TYPES.put( "double", double.class );
        PRIMITIVE_TYPES.put( "boolean", boolean.class );

        PRE_PROCESSING_METHODS.put( InvokerPreprocessingStrategyEnum.REGULAR, new PreprocessingRegularStrategy() );

        POST_PROCESSING_METHODS.put( InvokerPostprocessingStrategyEnum.REGULAR, new PostprocessingRegularStrategy() );
        POST_PROCESSING_METHODS.put( InvokerPostprocessingStrategyEnum.POJO, new PostprocessingPojoStrategy() );
        POST_PROCESSING_METHODS.put( InvokerPostprocessingStrategyEnum.POJO_LIST,
                new PostprocessingPojoListStrategy() );
    }

}
