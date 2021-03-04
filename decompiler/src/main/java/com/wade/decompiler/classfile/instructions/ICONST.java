package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.exceptions.ClassGenException;
import com.wade.decompiler.classfile.instructions.base.ConstantPushInstruction;
import com.wade.decompiler.classfile.instructions.base.Instruction;
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
    public Type getType() {
        return Type.INT;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(value);
    }

    @Override
    public int produceStack() {
        return opcode.getProduceStack();
    }
}
