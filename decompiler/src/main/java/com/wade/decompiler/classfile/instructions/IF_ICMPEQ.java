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
public class IF_ICMPEQ extends IfInstruction {
    public IF_ICMPEQ(ConstantPool cp) {
        super(InstructionOpCodes.IF_ICMPEQ, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IF_ICMPNE(constantPool);
    }
}
