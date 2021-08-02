package com.frsummit.HRM;

import org.springframework.boot.SpringApplication;

import com.frsummit.HRM.api.server.beanfactory.ContextWrapper;
import com.frsummit.HRM.model.User;
import com.frsummit.HRM.service.UserService;

// @SpringBootApplication
public class TestApplication {

    public static void main( String[] args ) {
        SpringApplication.run( TestApplication.class, args );
    }

    // @Override
    public void run( String... args ) throws Exception {
        UserService us = (UserService) ContextWrapper.getContext().getBean( "userService" );
        User user = us.findUserById( "api_user_test" );

        System.out.println( "-----------------------------------------" );
        System.out.println( user.getEmail() );

        us.updateUserProperty( "api_user_test", "email", "api.user@test" );

        user = us.findUserById( "api_user_test" );
        System.out.println( user.getEmail() );
        System.out.println( "-----------------------------------------" );

        byte[] array = { 1, 2, 3, 4, 5, 6 };
        System.out.println( array.getClass().getTypeName() );
        System.out.println( array.getClass().getCanonicalName() );
        System.out.println( array.getClass().getPackageName() );
        System.out.println( array.getClass().getSimpleName() );
        System.out.println( array.getClass().getName() );

    }

}
