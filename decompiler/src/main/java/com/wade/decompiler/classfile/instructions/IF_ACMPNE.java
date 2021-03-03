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
public class IF_ACMPNE extends IfInstruction {
    public IF_ACMPNE(ConstantPool cp) {
        super(InstructionOpCodes.IF_ACMPNE, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ACMPEQ(constantPool);
    }
}
