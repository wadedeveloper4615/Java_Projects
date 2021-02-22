package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.GotoInstruction;
import com.wade.decompiler.classfile.instructions.base.VariableLengthInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class GOTO extends GotoInstruction implements VariableLengthInstruction {
    public GOTO(ConstantPool cp) {
        super(InstructionOpCodes.GOTO, cp);
    }
}
