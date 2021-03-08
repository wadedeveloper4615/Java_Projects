package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class IFNE extends Instruction {
    public IFNE(ConstantPool cp) {
        super(InstructionOpCodes.IFNE, 3, cp);
    }

    public Instruction negate() {
        return new IFEQ(constantPool);
    }
}
