package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public abstract class StackInstruction extends Instruction {
    public StackInstruction(InstructionOpCodes opcode, ConstantPool constantPool) {
        super(opcode, 1, constantPool);
    }

    public Type getType() {
        return Type.UNKNOWN;
    }
}
