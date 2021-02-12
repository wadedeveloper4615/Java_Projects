
package org.apache.bcel.verifier.exc;

public abstract class CodeConstraintException extends VerificationException {
    private static final long serialVersionUID = -7265388214714996640L;

    CodeConstraintException() {
        super();
    }

    CodeConstraintException(final String message) {
        super(message);
    }
}
