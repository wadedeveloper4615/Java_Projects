package com.wade.decompiler.generic;

public interface InstructionComparator {
    InstructionComparator DEFAULT = (i1, i2) -> {
        if (i1.getOpcode() == i2.getOpcode()) {
            if (i1 instanceof BranchInstruction) {
                // BIs are never equal to make targeters work correctly (BCEL-195)
                return false;
//                } else if (i1 == i2) { TODO consider adding this shortcut
//                    return true; // this must be AFTER the BI test
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