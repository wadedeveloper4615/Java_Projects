package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.IfInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class IF_ACMPEQ extends IfInstruction {
    public IF_ACMPEQ(ConstantPool cp) {
        super(InstructionOpCodes.IF_ACMPEQ, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ACMPNE(constantPool);
    }
}
