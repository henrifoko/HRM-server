package com.frsummit.HRM.api.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class RMIInitializer implements CommandLineRunner {
	private static RemoteServiceInterface service;
	private static Registry registry = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(RMIInitializer.class);

	@Override
	public void run(String... args) throws Exception {
		try {
			System.setProperty("java.rmi.server.hostname", "192.168.56.1");
			System.setSecurityManager(new SecurityManager());

			RMIInitializer.service = new RemoteServiceImpl();

			registry = LocateRegistry.createRegistry(9082);

			registry.rebind("hrm_generic_service", service);

			System.out.println("[INFO] RMI :: Server is ready");

			// Logging
			LOGGER.info("RMI Server is ready");
		} catch (Exception e) {
			System.out.println("Error on the server " + e);
		}
	}

}
