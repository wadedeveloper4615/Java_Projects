
package org.apache.bcel.verifier.exc;

public class LocalVariableInfoInconsistentException extends ClassConstraintException {
    private static final long serialVersionUID = -2833180480144304190L;

    public LocalVariableInfoInconsistentException() {
        super();
    }

    public LocalVariableInfoInconsistentException(final String message) {
        super(message);
    }
}
