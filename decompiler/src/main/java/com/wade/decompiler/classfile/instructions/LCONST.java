package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ClassGenException;
import com.wade.decompiler.classfile.instructions.base.ConstantPushInstruction;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

public class LCONST extends Instruction implements ConstantPushInstruction {
    private long value;

    public LCONST() {
    }

    public LCONST(long l, ConstantPool cp) {
        super(InstructionOpCodes.LCONST_0, 1, cp);
        if (l == 0) {
            super.setOpcode(InstructionOpCodes.LCONST_0);
        } else if (l == 1) {
            super.setOpcode(InstructionOpCodes.LCONST_1);
        } else {
            throw new ClassGenException("LCONST can be used only for 0 and 1: " + l);
        }
        value = l;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        LCONST other = (LCONST) obj;
        if (value != other.value)
            return false;
        return true;
    }

    @Override
    public Type getType() {
        return Type.LONG;
    }

    @Override
    public Number getValue() {
        return Long.valueOf(value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (int) (value ^ (value >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "[put constant value=" + value + " on stack]";
    }
}
