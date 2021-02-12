
package org.apache.bcel.verifier.exc;

public class LoadingException extends VerifierConstraintViolatedException {

    private static final long serialVersionUID = -7911901533049018823L;

    public LoadingException() {
        super();
    }

    public LoadingException(final String message) {
        super(message);
    }
}
