package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public abstract class BranchInstruction extends Instruction {
    protected int index;
    protected int position;

    public BranchInstruction(InstructionOpCodes opcode, ConstantPool constantPool) {
        super(opcode, 3, constantPool);
    }
}
