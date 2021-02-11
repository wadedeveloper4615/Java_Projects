
package org.apache.bcel.generic.base;

import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.generic.control.InstructionList;
import org.apache.bcel.generic.control.InstructionTargeter;

public final class TargetLostException extends Exception {

    private static final long serialVersionUID = -6857272667645328384L;
    private final InstructionHandle[] targets;

    public TargetLostException(final InstructionHandle[] t, final String mesg) {
        super(mesg);
        targets = t;
    }

    public InstructionHandle[] getTargets() {
        return targets;
    }
}
