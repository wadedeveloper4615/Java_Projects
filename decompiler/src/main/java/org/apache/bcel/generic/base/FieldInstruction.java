package org.apache.bcel.generic.base;

import org.apache.bcel.enums.InstructionOpCodes;

public abstract class FieldInstruction extends FieldOrMethod {
    public FieldInstruction() {
    }

    public FieldInstruction(InstructionOpCodes opcode, final int index) {
        super(opcode, index);
    }
//    public String getFieldName(final ConstantPoolGen cpg) {
//        return getName(cpg);
//    }
//
//    protected int getFieldSize(final ConstantPoolGen cpg) {
//        return Type.size(Type.getTypeSize(getSignature(cpg)));
//    }
//
//    public Type getFieldType(final ConstantPoolGen cpg) {
//        return Type.getType(getSignature(cpg));
//    }
//    @Override
//    public Type getType(final ConstantPoolGen cpg) {
//        return getFieldType(cpg);
//    }
//    @Override
//    public String toString(final ConstantPool cp) {
//        // return super.getOpcode().getName() + " " +
//        // cp.constantToString(super.getIndex(), ClassFileConstants.CONSTANT_Fieldref);
//    }
}
