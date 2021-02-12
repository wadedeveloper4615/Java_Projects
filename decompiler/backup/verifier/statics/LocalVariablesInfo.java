
package org.apache.bcel.verifier.statics;

import org.apache.bcel.generic.Type;
import org.apache.bcel.verifier.exc.AssertionViolatedException;
import org.apache.bcel.verifier.exc.LocalVariableInfoInconsistentException;

public class LocalVariablesInfo {

    private final LocalVariableInfo[] localVariableInfos;

    LocalVariablesInfo(final int max_locals) {
        localVariableInfos = new LocalVariableInfo[max_locals];
        for (int i = 0; i < max_locals; i++) {
            localVariableInfos[i] = new LocalVariableInfo();
        }
    }

    public LocalVariableInfo getLocalVariableInfo(final int slot) {
        if (slot < 0 || slot >= localVariableInfos.length) {
            throw new AssertionViolatedException("Slot number for local variable information out of range.");
        }
        return localVariableInfos[slot];
    }

    public void add(final int slot, final String name, final int startPc, final int length, final Type type) throws LocalVariableInfoInconsistentException {
        // The add operation on LocalVariableInfo may throw the '...Inconsistent...'
        // exception, we don't throw it explicitely here.

        if (slot < 0 || slot >= localVariableInfos.length) {
            throw new AssertionViolatedException("Slot number for local variable information out of range.");
        }

        localVariableInfos[slot].add(name, startPc, length, type);
        if (type == Type.LONG) {
            localVariableInfos[slot + 1].add(name, startPc, length, LONG_Upper.theInstance());
        }
        if (type == Type.DOUBLE) {
            localVariableInfos[slot + 1].add(name, startPc, length, DOUBLE_Upper.theInstance());
        }
    }
}
