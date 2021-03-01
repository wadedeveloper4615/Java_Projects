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
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class FieldInstruction extends FieldOrMethodInstruction {
    public FieldInstruction(InstructionOpCodes opcode, ConstantPool cp, int index) {
        super(opcode, cp, index);
    }

    public int getFieldSize() {
        return Type.size(Type.getTypeSize(getSignature()));
    }
}
