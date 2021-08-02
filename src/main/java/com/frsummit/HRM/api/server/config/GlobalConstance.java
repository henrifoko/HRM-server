package com.frsummit.HRM.api.server.config;

import java.util.HashMap;

public class GlobalConstance {

    public static final HashMap<String, Class<?>> PRIMITIVE_TYPES = new HashMap<String, Class<?>>() {
        private static final long serialVersionUID = 1L;

        {
            put( "int", int.class );
            put( "byte", byte.class );
            put( "long", long.class );
            put( "char", char.class );
            put( "void", void.class );
            put( "short", short.class );
            put( "float", float.class );
            put( "double", double.class );
            put( "boolean", boolean.class );
        }
    };
}
