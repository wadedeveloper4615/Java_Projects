package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

public abstract class BranchInstruction extends Instruction {
    protected int index;
    protected int position;

    public BranchInstruction(InstructionOpCodes opcode, ConstantPool constantPool) {
        super(opcode, 3, constantPool);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BranchInstruction other = (BranchInstruction) obj;
        if (index != other.index)
            return false;
        if (position != other.position)
            return false;
        return true;
    }

    public int getIndex() {
        return index;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + index;
        result = prime * result + position;
        return result;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return super.toString() + "[index=" + index + ", position=" + position + "]";
    }

}
