package com.wade.decompiler.classfile.instructions.base;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public abstract class Instruction {
    protected int offset;
    protected int length;
    @ToString.Exclude
    protected InstructionOpCodes opcode;
    @ToString.Exclude
    protected ConstantPool constantPool;

    public Instruction(InstructionOpCodes opcode, int length, ConstantPool constantPool) {
        this.length = length;
        this.opcode = opcode;
        this.constantPool = constantPool;
    }

    @SuppressWarnings("unused")
    protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
    }
}
