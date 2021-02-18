package com.wade.decompiler.generic.base;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.type.Type;

public abstract class FieldInstruction extends FieldOrMethod {
    public FieldInstruction() {
    }

    public FieldInstruction(InstructionOpCodes opcode, int index) {
        super(opcode, index);
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

    @Override
    public String toString(ConstantPool cp) {
        return Const.getOpcodeName(super.getOpcode()) + " " + cp.constantToString(super.getIndex(), ClassFileConstants.CONSTANT_Fieldref);
    }
}
