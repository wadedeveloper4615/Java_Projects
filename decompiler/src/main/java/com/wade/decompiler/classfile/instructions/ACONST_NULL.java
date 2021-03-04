package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
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
public class ACONST_NULL extends Instruction {
    public ACONST_NULL(ConstantPool cp) {
        super(InstructionOpCodes.ACONST_NULL, 1, cp);
    }

    public Type getType() {
        return Type.NULL;
    }
}
