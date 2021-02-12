
package org.apache.bcel.verifier.statics;

import java.util.Hashtable;

import org.apache.bcel.generic.Type;
import org.apache.bcel.verifier.exc.LocalVariableInfoInconsistentException;

public class LocalVariableInfo {

    private final Hashtable<String, Type> types = new Hashtable<>();

    private final Hashtable<String, String> names = new Hashtable<>();

    private void setName(final int offset, final String name) {
        names.put(Integer.toString(offset), name);
    }

    private void setType(final int offset, final Type t) {
        types.put(Integer.toString(offset), t);
    }

    public Type getType(final int offset) {
        return types.get(Integer.toString(offset));
    }

    public String getName(final int offset) {
        return names.get(Integer.toString(offset));
    }

    public void add(final String name, final int startPc, final int length, final Type type) throws LocalVariableInfoInconsistentException {
        for (int i = startPc; i <= startPc + length; i++) { // incl/incl-notation!
            add(i, name, type);
        }
    }

    private void add(final int offset, final String name, final Type t) throws LocalVariableInfoInconsistentException {
        if (getName(offset) != null) {
            if (!getName(offset).equals(name)) {
                throw new LocalVariableInfoInconsistentException("At bytecode offset '" + offset + "' a local variable has two different names: '" + getName(offset) + "' and '" + name + "'.");
            }
        }
        if (getType(offset) != null) {
            if (!getType(offset).equals(t)) {
                throw new LocalVariableInfoInconsistentException("At bytecode offset '" + offset + "' a local variable has two different types: '" + getType(offset) + "' and '" + t + "'.");
            }
        }
        setName(offset, name);
        setType(offset, t);
    }
}
