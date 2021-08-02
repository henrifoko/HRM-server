package com.frsummit.HRM.api.server;

public interface PostProcessingStrategy {

    public <T> T process( Object data );
}
