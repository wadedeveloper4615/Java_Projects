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
public class IFNONNULL extends Instruction {
    public IFNONNULL(ConstantPool cp) {
        super(InstructionOpCodes.IFNONNULL, 3, cp);
    }

    public Instruction negate() {
        return new IFNULL(constantPool);
    }
}
