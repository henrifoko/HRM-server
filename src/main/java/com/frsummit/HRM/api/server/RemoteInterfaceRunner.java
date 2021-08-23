package com.frsummit.HRM.api.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.frsummit.HRM.api.server.config.GlobalConfig;

/**
 * 
 */
@Component
public class RemoteInterfaceRunner extends RemoteInterfaceInitializer implements CommandLineRunner {

    /**
     * 
     */
    @Override
    public void run( String... args ) throws Exception {
        initRemoteInterface(
                GlobalConfig.SERVER_IP_ADDRESS,
                GlobalConfig.RMI_PORT,
                GlobalConfig.REMOTE_SERVER_OBJECT_NAME );
    }

}
