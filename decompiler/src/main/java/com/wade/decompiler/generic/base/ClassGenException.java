package com.wade.decompiler.generic.base;

public class ClassGenException extends RuntimeException {
    private static final long serialVersionUID = 3023347252327295834L;

    public ClassGenException() {
    }

    public ClassGenException(String message) {
        super(message);
    }

    public ClassGenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassGenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ClassGenException(Throwable cause) {
        super(cause);
    }

}
