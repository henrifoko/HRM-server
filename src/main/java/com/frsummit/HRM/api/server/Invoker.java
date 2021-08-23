package com.frsummit.HRM.api.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.frsummit.HRM.api.server.config.GlobalConstance;
import com.frsummit.HRM.api.server.exception.ClientException;
import com.frsummit.HRM.api.server.exception.ServerException;
import com.frsummit.HRM.api.server.shared.command.Command;
import com.frsummit.HRM.api.server.shared.invoker.InvokerInterface;

/**
 * 
 */
public class Invoker extends UnicastRemoteObject implements InvokerInterface {

    /**
     * 
     */
    private static final long serialVersionUID = -2670716697296871165L;

    /**
     * 
     */
    private InvokerContext    invokerContext;

    /**
     * 
     */
    public Invoker() throws RemoteException {
    }

    /**
     * 
     */
    @Override
    public Object exec( Command command ) throws RemoteException {
        // Create the invoker context
        invokerContext = new InvokerContext();

        // Set the pre processing strategy
        invokerContext.setPreprocessingStrategy(
                GlobalConstance.PRE_PROCESSING_METHODS.get(
                        command.getPreprocessingStrategy() ) );

        // Set the post processing strategy
        invokerContext.setPostprocessingStrategy(
                GlobalConstance.POST_PROCESSING_METHODS.get(
                        command.getPostprocessingStrategy() ) );

        // Run the server process
        Object result = null;

        try {
            result = this.invokerContext.execTemplateMethod(
                    command.getBeanClassName(),
                    command.getMethodName(),
                    command.getParamTypes(),
                    command.getParams() );
        } catch ( ServerException e ) {
            e.printStackTrace();

            throw new RemoteException( "Server exception.", e );
        } catch ( ClientException e ) {
            e.printStackTrace();

            throw new RemoteException( "Client exception.", e );
        }

        return result;
    }
}
