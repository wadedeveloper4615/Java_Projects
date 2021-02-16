package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.ConstantPool;

public abstract class FieldInstruction extends FieldOrMethod {
    FieldInstruction() {
    }

    protected FieldInstruction(final short opcode, final int index) {
        super(opcode, index);
    }

    public String getFieldName(final ConstantPoolGen cpg) {
        return getName(cpg);
    }

    protected int getFieldSize(final ConstantPoolGen cpg) {
        return Type.size(Type.getTypeSize(getSignature(cpg)));
    }

    public Type getFieldType(final ConstantPoolGen cpg) {
        return Type.getType(getSignature(cpg));
    }

    @Override
    public Type getType(final ConstantPoolGen cpg) {
        return getFieldType(cpg);
    }

    @Override
    public String toString(final ConstantPool cp) {
        return com.wade.decompiler.Const.getOpcodeName(super.getOpcode()) + " " + cp.constantToString(super.getIndex(), com.wade.decompiler.Const.CONSTANT_Fieldref);
    }
}
