
package org.apache.bcel.verifier.structurals;

import org.apache.bcel.Const;
import org.apache.bcel.Constants;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.base.ReferenceType;

public class UninitializedObjectType extends ReferenceType implements Constants {

    private final ObjectType initialized;

    public UninitializedObjectType(final ObjectType t) {
        super(Const.T_UNKNOWN, "<UNINITIALIZED OBJECT OF TYPE '" + t.getClassName() + "'>");
        initialized = t;
    }

    public ObjectType getInitialized() {
        return initialized;
    }

    @Override
    public int hashCode() {
        return initialized.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof UninitializedObjectType)) {
            return false;
        }
        return initialized.equals(((UninitializedObjectType) o).initialized);
    }
}
