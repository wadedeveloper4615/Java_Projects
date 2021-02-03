package com.wade.app;

public class ClassFormatException extends Exception {
    private static final long serialVersionUID = 8591008550574781913L;

    public ClassFormatException() {
        super();
    }

    public ClassFormatException(String message) {
        super(message);
    }

    public ClassFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ClassFormatException(Throwable cause) {
        super(cause);
    }
}
