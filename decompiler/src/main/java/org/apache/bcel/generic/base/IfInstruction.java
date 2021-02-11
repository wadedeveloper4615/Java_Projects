
package org.apache.bcel.generic.base;

import org.apache.bcel.generic.control.InstructionHandle;

public abstract class IfInstruction extends BranchInstruction implements StackConsumer {

    public IfInstruction() {
    }

    public IfInstruction(final short opcode, final InstructionHandle target) {
        super(opcode, target);
    }

    public abstract IfInstruction negate();
}
