
package org.apache.bcel.verifier.structurals;

import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.base.ReferenceType;
import org.apache.bcel.verifier.exc.AssertionViolatedException;
import org.apache.bcel.verifier.exc.StructuralCodeConstraintException;

public class LocalVariables implements Cloneable {

    private final Type[] locals;

    public LocalVariables(final int localVariableCount) {
        locals = new Type[localVariableCount];
        for (int i = 0; i < localVariableCount; i++) {
            locals[i] = Type.UNKNOWN;
        }
    }

    @Override
    public Object clone() {
        final LocalVariables lvs = new LocalVariables(locals.length);
        for (int i = 0; i < locals.length; i++) {
            lvs.locals[i] = this.locals[i];
        }
        return lvs;
    }

    public Type get(final int slotIndex) {
        return locals[slotIndex];
    }

    public LocalVariables getClone() {
        return (LocalVariables) this.clone();
    }

    public int maxLocals() {
        return locals.length;
    }

    public void set(final int slotIndex, final Type type) { // TODO could be package-protected?
        if (type == Type.BYTE || type == Type.SHORT || type == Type.BOOLEAN || type == Type.CHAR) {
            throw new AssertionViolatedException("LocalVariables do not know about '" + type + "'. Use Type.INT instead.");
        }
        locals[slotIndex] = type;
    }

    @Override
    public int hashCode() {
        return locals.length;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof LocalVariables)) {
            return false;
        }
        final LocalVariables lv = (LocalVariables) o;
        if (this.locals.length != lv.locals.length) {
            return false;
        }
        for (int i = 0; i < this.locals.length; i++) {
            if (!this.locals[i].equals(lv.locals[i])) {
                // System.out.println(this.locals[i]+" is not "+lv.locals[i]);
                return false;
            }
        }
        return true;
    }

    public void merge(final LocalVariables localVariable) {

        if (this.locals.length != localVariable.locals.length) {
            throw new AssertionViolatedException("Merging LocalVariables of different size?!? From different methods or what?!?");
        }

        for (int i = 0; i < locals.length; i++) {
            merge(localVariable, i);
        }
    }

    private void merge(final LocalVariables lv, final int i) {
        try {

            // We won't accept an unitialized object if we know it was initialized;
            // compare vmspec2, 4.9.4, last paragraph.
            if ((!(locals[i] instanceof UninitializedObjectType)) && (lv.locals[i] instanceof UninitializedObjectType)) {
                throw new StructuralCodeConstraintException("Backwards branch with an uninitialized object in the local variables detected.");
            }
            // Even harder, what about _different_ uninitialized object types?!
            if ((!(locals[i].equals(lv.locals[i]))) && (locals[i] instanceof UninitializedObjectType) && (lv.locals[i] instanceof UninitializedObjectType)) {
                throw new StructuralCodeConstraintException("Backwards branch with an uninitialized object in the local variables detected.");
            }
            // If we just didn't know that it was initialized, we have now learned.
            if (locals[i] instanceof UninitializedObjectType) {
                if (!(lv.locals[i] instanceof UninitializedObjectType)) {
                    locals[i] = ((UninitializedObjectType) locals[i]).getInitialized();
                }
            }
            if ((locals[i] instanceof ReferenceType) && (lv.locals[i] instanceof ReferenceType)) {
                if (!locals[i].equals(lv.locals[i])) { // needed in case of two UninitializedObjectType instances
                    final Type sup = ((ReferenceType) locals[i]).getFirstCommonSuperclass((ReferenceType) (lv.locals[i]));

                    if (sup != null) {
                        locals[i] = sup;
                    } else {
                        // We should have checked this in Pass2!
                        throw new AssertionViolatedException("Could not load all the super classes of '" + locals[i] + "' and '" + lv.locals[i] + "'.");
                    }
                }
            } else {
                if (!(locals[i].equals(lv.locals[i]))) {

                    locals[i] = Type.UNKNOWN;
                }
            }
        } catch (final ClassNotFoundException e) {
            // FIXME: maybe not the best way to handle this
            throw new AssertionViolatedException("Missing class: " + e, e);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < locals.length; i++) {
            sb.append(Integer.toString(i));
            sb.append(": ");
            sb.append(locals[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void initializeObject(final UninitializedObjectType uninitializedObjectType) {
        for (int i = 0; i < locals.length; i++) {
            if (locals[i] == uninitializedObjectType) {
                locals[i] = uninitializedObjectType.getInitialized();
            }
        }
    }
}
