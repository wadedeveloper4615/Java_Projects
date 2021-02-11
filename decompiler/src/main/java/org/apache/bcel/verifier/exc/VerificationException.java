
package org.apache.bcel.verifier.exc;

public abstract class VerificationException extends VerifierConstraintViolatedException {
    private static final long serialVersionUID = 8012776320318623652L;

    VerificationException() {
        super();
    }

    VerificationException(final String message) {
        super(message);
    }

    VerificationException(final String message, final Throwable initCause) {
        super(message, initCause);
    }
}
