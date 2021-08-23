package com.frsummit.HRM.api.server.shared.invoker;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.frsummit.HRM.api.server.converions.IPostprocessingStrategy;
import com.frsummit.HRM.api.server.shared.command.Command;

/**
 * 
 * @author hfoko
 *
 */
public interface InvokerInterface extends Remote {

    /**
     * THe remote method for invoking server methods from the information
     * provided by the object command sent. This method is the entry point of
     * the API to the server. Any server execution triggered by the client, goes
     * through this method. the command object contains all the information
     * about the method to execute, its parameters, and other information such
     * as the processing to be done on the result. Refer to {@link Command} for
     * more information about the Command class. The method returns the result
     * of executing the server-side method, after post processing is applied.
     * Refer to {@link IPostprocessingStrategy} for more information about the
     * post processing step. The throwing of the exception
     * {@link RemoteException} is a requirement for using this as a remote
     * method.
     * 
     * @param command
     *            The unique parameter of this method is the command object
     * @return Method execution result after post processing
     * @throws RemoteException
     *             Exception thrown if any error occurs during the exec method
     *             execution.
     */
    public Object exec( Command command ) throws RemoteException;

}
