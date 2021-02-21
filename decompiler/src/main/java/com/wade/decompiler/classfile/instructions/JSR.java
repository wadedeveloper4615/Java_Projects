package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.InstructionHandle;
import com.wade.decompiler.classfile.instructions.base.JsrInstruction;
import com.wade.decompiler.classfile.instructions.base.VariableLengthInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class JSR extends JsrInstruction implements VariableLengthInstruction {
    public JSR() {
    }

    public JSR(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.JSR, target, cp);
    }

    @Override
    protected int updatePosition(int offset, int max_offset) {
        int i = getTargetOffset();
        setPosition(getPosition() + offset);
        if (Math.abs(i) >= (Short.MAX_VALUE - max_offset)) {
            super.setOpcode(InstructionOpCodes.JSR_W);
            short old_length = (short) super.getLength();
            super.setLength(5);
            return super.getLength() - old_length;
        }
        return 0;
    }
}
