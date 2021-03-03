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
public class IFLT extends IfInstruction {
    public IFLT(ConstantPool cp) {
        super(InstructionOpCodes.IFLT, cp);
    }

    @Override
    public IfInstruction negate() {
        return new IFGE(constantPool);
    }
}
