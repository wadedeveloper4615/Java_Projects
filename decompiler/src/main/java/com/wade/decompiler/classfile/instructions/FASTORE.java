package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class FASTORE extends Instruction {
    private Type type;

    public FASTORE(ConstantPool cp) {
        super(InstructionOpCodes.FASTORE, 1, cp);
        type = Type.FLOAT;
    }

    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION);
    }
}
