package org.apache.bcel.generic.base;

import org.apache.bcel.enums.InstructionOpCodes;

public abstract class CPInstruction extends Instruction implements TypedInstruction, IndexedInstruction {
    protected int index;

    protected CPInstruction() {
    }

    protected CPInstruction(InstructionOpCodes opcode, int index) {
        super(opcode, (short) 3);
    }
//
//    @Override
//    public String toString(final boolean verbose) {
//        return super.toString(verbose) + " " + index;
//    }
//
//    @Override
//    public String toString(final ConstantPool cp) {
//        final Constant c = cp.getConstant(index);
//        String str = cp.constantToString(c);
//        if (c instanceof ConstantClass) {
//            str = str.replace('.', '/');
//        }
//        return super.getOpcode().getName() + " " + str;
//    }
}
