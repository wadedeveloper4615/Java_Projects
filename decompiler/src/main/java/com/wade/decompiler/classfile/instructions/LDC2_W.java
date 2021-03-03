package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantDouble;
import com.wade.decompiler.classfile.constant.ConstantLong;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.CPInstruction;
import com.wade.decompiler.classfile.instructions.base.PushInstruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class LDC2_W extends CPInstruction implements PushInstruction {
    public LDC2_W(int index, ConstantPool cp) {
        super(InstructionOpCodes.LDC2_W, cp, index);
    }

    @Override
    public Type getType() {
        Constant c = this.constantPool.getConstant(super.getIndex());
        switch (c.getTag()) {
            case CONSTANT_Long:
                return Type.LONG;
            case CONSTANT_Double:
                return Type.DOUBLE;
            default:
                throw new IllegalArgumentException("Unknown constant type " + super.getOpcode());
        }
    }

    public Number getValue() {
        Constant c = constantPool.getConstant(super.getIndex());
        if (c == null)
            return null;
        switch (c.getTag()) {
            case CONSTANT_Long:
                return Long.valueOf(((ConstantLong) c).getBytes());
            case CONSTANT_Double:
                return Double.valueOf(((ConstantDouble) c).getBytes());
            default:
                throw new IllegalArgumentException("Unknown or invalid constant type at " + super.getIndex());
        }
    }
}
