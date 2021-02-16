package com.wade.decompiler.generic;

public class ClassGenException extends RuntimeException {
    private static final long serialVersionUID = 7247369755051242791L;

    public ClassGenException() {
        super();
    }

    public ClassGenException(final String s) {
        super(s);
    }

    public ClassGenException(final String s, final Throwable initCause) {
        super(s, initCause);
    }
}
