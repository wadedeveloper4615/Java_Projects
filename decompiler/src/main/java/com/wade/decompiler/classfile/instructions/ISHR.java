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
public class ISHR extends Instruction {
    private Type type;

    public ISHR(ConstantPool cp) {
        super(InstructionOpCodes.ISHR, 1, cp);
        type = Type.INT;
    }

    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.ILLEGAL_MONITOR_STATE };
    }

}