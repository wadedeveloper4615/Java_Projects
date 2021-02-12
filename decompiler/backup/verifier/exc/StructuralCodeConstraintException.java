
package org.apache.bcel.verifier.exc;

public class StructuralCodeConstraintException extends CodeConstraintException {
    private static final long serialVersionUID = 5406842000007181420L;

    public StructuralCodeConstraintException(final String message) {
        super(message);
    }

    public StructuralCodeConstraintException() {
        super();
    }
}
