package com.frsummit.HRM.api.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteInterfaceInitializer {
	private static RemoteService service;
	private static Registry registry = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteInterfaceRunner.class);

	public final void initRemoteInterface(String hostIpAddress, int port, String remoteObjectName) throws Exception {

		// Logging - info
		LOGGER.info("Initializing RMI server...");

		try {
			System.setProperty("java.rmi.server.hostname", hostIpAddress);
			System.setSecurityManager(new SecurityManager());

			RemoteInterfaceInitializer.service = new RemoteServiceImpl();

			registry = LocateRegistry.createRegistry(port);

			registry.rebind(remoteObjectName, service);

			System.out.println("[INFO] RMI :: Server is running on port " + port);

			// Logging - info
			LOGGER.info("RMI Server is running on port " + port);

		} catch (Exception e) {

			// Logging - error
			LOGGER.error("Error on the RMI server");

			e.printStackTrace();
		}
	}
}
