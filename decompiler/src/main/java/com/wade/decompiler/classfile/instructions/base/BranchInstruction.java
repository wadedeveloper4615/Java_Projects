package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class BranchInstruction extends Instruction {
    protected int index;
    protected int position;

    public BranchInstruction(InstructionOpCodes opcode, ConstantPool constantPool) {
        super(opcode, 3, constantPool);
    }

    public int getIndex() {
        return index;
    }

    public int getPosition() {
        return position;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
