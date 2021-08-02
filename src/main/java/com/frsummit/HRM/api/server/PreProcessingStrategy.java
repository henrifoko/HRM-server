package com.frsummit.HRM.api.server;

public interface PreProcessingStrategy {

    /**
     * Performs all preliminary processing on the parameters. These processes
     * (conversions for example) will result in objects that will be transmitted
     * back to the server.
     * 
     * @param data
     *            - The data that have to be processed/converted
     * @return Object[] - data converted
     */
    public Object[] process( Object[] data );
}
