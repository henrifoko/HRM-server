package com.frsummit.HRM.api.server.converions;

import com.frsummit.HRM.api.server.exception.PostProcessingException;

/**
 * 
 * @author hfoko
 *
 */
public interface IPostprocessingStrategy {

    /**
     * 
     * @param <T>
     * @param data
     * @return
     * @throws PostProcessingException
     */
    public Object process( Object data ) throws PostProcessingException;
}
