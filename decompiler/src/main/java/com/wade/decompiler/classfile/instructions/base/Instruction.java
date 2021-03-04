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
    protected int length;
    protected InstructionOpCodes opcode;
    private String name;
    private int consumeStack;
    private int produceStack;
    @ToString.Exclude
    protected ConstantPool constantPool;

    public Instruction(InstructionOpCodes opcode, int length, ConstantPool constantPool) {
        this.length = length;
        this.opcode = opcode;
        this.name = opcode.getName();
        this.consumeStack = opcode.getConsumeStack();
        this.produceStack = opcode.getProduceStack();
        this.constantPool = constantPool;
    }

    public int consumeStack() {
        return opcode.getConsumeStack();
    }

    @SuppressWarnings("unused")
    protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
    }

    public int produceStack() {
        return opcode.getProduceStack();
    }
}
