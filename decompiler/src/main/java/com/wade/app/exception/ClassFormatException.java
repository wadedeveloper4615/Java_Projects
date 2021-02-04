package com.wade.app.exception;

public class ClassFormatException extends RuntimeException {
    private static final long serialVersionUID = -3569097343160139969L;

    public ClassFormatException() {
        super();
    }

    public ClassFormatException(final String s) {
        super(s);
    }

    public ClassFormatException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
