package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.FieldInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class PUTFIELD extends FieldInstruction {
    public PUTFIELD(int index, ConstantPool cp) {
        super(InstructionOpCodes.PUTFIELD, cp, index);
    }

    @Override
    public int consumeStack() {
        return getFieldSize() + 1;
    }
}
