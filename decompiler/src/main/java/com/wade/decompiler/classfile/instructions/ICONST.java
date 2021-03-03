package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.exceptions.ClassGenException;
import com.wade.decompiler.classfile.instructions.base.ConstantPushInstruction;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

public class ICONST extends Instruction implements ConstantPushInstruction {
    private int value;

    public ICONST(int i, ConstantPool cp) {
        super(InstructionOpCodes.ICONST_0, 1, cp);
        if ((i >= -1) && (i <= 5)) {
            super.setOpcode(InstructionOpCodes.read((short) (InstructionOpCodes.ICONST_0.getOpcode() + i))); // Even works for i == -1
        } else {
            throw new ClassGenException("ICONST can be used only for value between -1 and 5: " + i);
        }
        value = i;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ICONST other = (ICONST) obj;
        if (value != other.value)
            return false;
        return true;
    }

    @Override
    public Type getType() {
        return Type.INT;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + value;
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "[put constant value=" + value + " on stack]";
    }
}
