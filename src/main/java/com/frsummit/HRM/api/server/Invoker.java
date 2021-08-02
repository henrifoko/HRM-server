package com.frsummit.HRM.api.server;

import java.rmi.RemoteException;

public class Invoker extends InvokerAbstract {

    private static final long serialVersionUID = 1L;

    public Invoker() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public Object exec( String daoClassName, String methodName, String[] paramTypes, Object[] params,
            Class<?> returnType ) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

}
