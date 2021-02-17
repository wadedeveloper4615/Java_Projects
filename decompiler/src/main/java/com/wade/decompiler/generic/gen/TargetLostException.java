package com.wade.decompiler.generic.gen;

import com.wade.decompiler.generic.base.InstructionHandle;

public class TargetLostException extends Exception {
    private static final long serialVersionUID = -6857272667645328384L;
    private InstructionHandle[] targets;

    public TargetLostException(InstructionHandle[] t, String mesg) {
        super(mesg);
        targets = t;
    }

    public InstructionHandle[] getTargets() {
        return targets;
    }
}
