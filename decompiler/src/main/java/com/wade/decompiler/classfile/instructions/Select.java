package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.BranchInstruction;
import com.wade.decompiler.classfile.instructions.base.StackConsumer;
import com.wade.decompiler.classfile.instructions.base.StackProducer;
import com.wade.decompiler.classfile.instructions.base.VariableLengthInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class Select extends BranchInstruction implements VariableLengthInstruction, StackConsumer, StackProducer {
    protected int[] match;
    protected int[] indices;
    protected int fixed_length;
    protected int match_length;
    protected int padding = 0;

    public Select(InstructionOpCodes opcode, int[] match, ConstantPool cp) {
        super(opcode, cp);
        this.match = match;
    }
}
