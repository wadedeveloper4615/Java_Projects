
package org.apache.bcel.verifier.exc;

public abstract class StaticCodeConstraintException extends CodeConstraintException {
    private static final long serialVersionUID = 3858523065007725128L;

    public StaticCodeConstraintException(final String message) {
        super(message);
    }
}
