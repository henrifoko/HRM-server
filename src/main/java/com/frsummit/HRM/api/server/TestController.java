package com.frsummit.HRM.api.server;

import java.rmi.RemoteException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frsummit.HRM.api.server.shared.InvokerPostprocessingStrategyEnum;
import com.frsummit.HRM.api.server.shared.InvokerPreprocessingStrategyEnum;
import com.frsummit.HRM.api.server.shared.command.Command;

@RestController
public class TestController {

    @GetMapping( "/api/test/user.get" )
    public Object getUser() {
        String bean = "com.frsummit.HRM.service.UserService";
        String method = "findUserByEmail";
        Command command = new Command(
                bean,
                method,
                new String[] { "java.lang.String" },
                new String[] { "henrifoko@gmail.com" },
                InvokerPreprocessingStrategyEnum.REGULAR,
                InvokerPostprocessingStrategyEnum.POJO );

        Invoker invoker;
        Object obj = null;
        try {
            invoker = new Invoker();
            obj = invoker.exec( command );
        } catch ( RemoteException e ) {
            e.printStackTrace();
        }
        System.out.println( obj.getClass().getName() );
        return obj;
    }

    @GetMapping( "/api/test/users" )
    public Object getAllUsers() {
        String bean = "com.frsummit.HRM.service.UserService";
        String method = "findAllUsers";
        Command command = new Command(
                bean,
                method,
                new String[] {},
                new String[] {},
                InvokerPreprocessingStrategyEnum.REGULAR,
                InvokerPostprocessingStrategyEnum.POJO_LIST );

        Invoker invoker;
        Object obj = null;
        try {
            invoker = new Invoker();
            obj = invoker.exec( command );
        } catch ( RemoteException e ) {
            e.printStackTrace();
        }
        System.out.println( obj.getClass().getName() );
        return obj;
    }
}
