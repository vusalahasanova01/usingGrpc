package com.example.demo.util;


public final class ExceptionUtil {


    public static UnsupportedOperationException exUnsupported() {
        return new UnsupportedOperationException("task not exist");
    }


}
