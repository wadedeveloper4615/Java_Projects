package com.wade.decompiler.generic.base;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.generic.gen.ConstantPoolGen;

public abstract class FieldInstruction extends FieldOrMethod {
    public FieldInstruction() {
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
        return Const.getOpcodeName(super.getOpcode()) + " " + cp.constantToString(super.getIndex(), ClassFileConstants.CONSTANT_Fieldref);
    }
}
