package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class IfInstruction extends BranchInstruction implements StackConsumer {
    public IfInstruction(InstructionOpCodes opcode, ConstantPool constantPool) {
        super(opcode, constantPool);
    }

    public abstract IfInstruction negate();
}
