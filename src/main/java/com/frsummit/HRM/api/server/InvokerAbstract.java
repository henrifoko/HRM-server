package com.frsummit.HRM.api.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

abstract public class InvokerAbstract extends UnicastRemoteObject implements RemoteService {

	private static final long serialVersionUID = 10730005760004433L;

	public InvokerAbstract() throws RemoteException {
		//
	}
}
