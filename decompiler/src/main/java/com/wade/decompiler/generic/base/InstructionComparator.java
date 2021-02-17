package com.wade.decompiler.generic.base;

import com.wade.decompiler.generic.NEWARRAY;

public interface InstructionComparator {
    InstructionComparator DEFAULT = (i1, i2) -> {
        if (i1.getOpcode() == i2.getOpcode()) {
            if (i1 instanceof BranchInstruction) {
                return false;
            } else if (i1 instanceof ConstantPushInstruction) {
                return ((ConstantPushInstruction) i1).getValue().equals(((ConstantPushInstruction) i2).getValue());
            } else if (i1 instanceof IndexedInstruction) {
                return ((IndexedInstruction) i1).getIndex() == ((IndexedInstruction) i2).getIndex();
            } else if (i1 instanceof NEWARRAY) {
                return ((NEWARRAY) i1).getTypecode() == ((NEWARRAY) i2).getTypecode();
            } else {
                return true;
            }
        }
        return false;
    };

    boolean equals(Instruction i1, Instruction i2);
}
