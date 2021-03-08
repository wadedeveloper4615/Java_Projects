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
public class IF_ICMPLT extends Instruction {
    public IF_ICMPLT(ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPLT, 3, cp);
    }

    public Instruction negate() {
        return new IF_ICMPGE(constantPool);
    }
}
