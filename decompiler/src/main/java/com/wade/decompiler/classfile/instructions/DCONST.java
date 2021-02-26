package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.ClassGenException;
import com.wade.decompiler.classfile.instructions.base.ConstantPushInstruction;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.decompiler.ExpressionStack;
import com.wade.decompiler.enums.InstructionOpCodes;

public class DCONST extends Instruction implements ConstantPushInstruction {
    private double value;

    public DCONST(double f, ConstantPool cp) {
        super(InstructionOpCodes.DCONST_0, 1, cp);
        if (f == 0.0) {
            super.setOpcode(InstructionOpCodes.DCONST_0);
        } else if (f == 1.0) {
            super.setOpcode(InstructionOpCodes.DCONST_1);
        } else {
            throw new ClassGenException("DCONST can be used only for 0.0 and 1.0: " + f);
        }
        value = f;
    }

    @Override
    public String decompile(ExpressionStack stack) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type getType() {
        return Type.DOUBLE;
    }

    @Override
    public Number getValue() {
        return Double.valueOf(value);
    }
}
