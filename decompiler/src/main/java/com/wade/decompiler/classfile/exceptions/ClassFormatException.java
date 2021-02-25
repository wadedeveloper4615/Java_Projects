package com.wade.decompiler.classfile.exceptions;

public class ClassFormatException extends RuntimeException {
    private static final long serialVersionUID = -3569097343160139969L;

    public ClassFormatException() {
        super();
    }

    public ClassFormatException(String s) {
        super(s);
    }

    public ClassFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
