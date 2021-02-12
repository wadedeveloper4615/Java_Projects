
package org.apache.bcel.verifier.exc;

public class ClassConstraintException extends VerificationException {
    private static final long serialVersionUID = -4745598983569128296L;

    public ClassConstraintException() {
        super();
    }

    public ClassConstraintException(final String message) {
        super(message);
    }

    public ClassConstraintException(final String message, final Throwable initCause) {
        super(message, initCause);
    }
}
