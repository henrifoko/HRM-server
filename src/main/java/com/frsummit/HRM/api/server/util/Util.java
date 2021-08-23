package com.frsummit.HRM.api.server.util;

public class Util {

    /**
     * It allows you to find the class corresponding to a string corresponding
     * to its name
     * 
     * @param className
     * @return
     */
    public static Class<?> parseType( final String className ) {
        switch ( className ) {
        case "boolean":
            return boolean.class;
        case "byte":
            return byte.class;
        case "short":
            return short.class;
        case "int":
            return int.class;
        case "long":
            return long.class;
        case "float":
            return float.class;
        case "double":
            return double.class;
        case "char":
            return char.class;
        case "void":
            return void.class;
        default:
            try {
                return Class.forName( className );
            } catch ( ClassNotFoundException ex ) {
                throw new IllegalArgumentException( "Class not found: " + className );
            }
        }
    }

}
