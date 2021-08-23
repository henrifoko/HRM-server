package com.frsummit.HRM.api.server.converions;

import com.frsummit.HRM.api.server.exception.PreProcessingException;

/**
 * 
 * @author hfoko
 *
 */
public interface IPreprocessingStrategy {

    /**
     * Performs all preliminary processing on the parameters. These processes
     * (conversions for example) will result in objects that will be transmitted
     * back to the server.
     * 
     * @param data
     *            - The data that have to be processed/converted
     * @return Object[] - data converted
     */
    public Object[] process( Object[] data ) throws PreProcessingException;
}
