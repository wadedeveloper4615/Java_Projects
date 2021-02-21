package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class FieldInstruction extends FieldOrMethod {
    public FieldInstruction() {
    }

    public FieldInstruction(InstructionOpCodes opcode, ConstantPool constantPool, int index) {
        super(opcode, constantPool, index);
    }

    public String getFieldName(ConstantPool cpg) {
        return getName(cpg);
    }

    protected int getFieldSize(ConstantPool cpg) {
        return Type.size(Type.getTypeSize(getSignature(cpg)));
    }

    public Type getFieldType(ConstantPool cpg) {
        return Type.getType(getSignature(cpg));
    }

    @Override
    public Type getType(ConstantPool cp) {
        return getFieldType(cp);
    }
}
