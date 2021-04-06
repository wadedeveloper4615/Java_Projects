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
public class DCMPL extends Instruction {
    private Type type;
    private Instruction negate;

    public DCMPL(ConstantPool cp) {
        super(InstructionOpCodes.DCMPL, 1, cp);
        type = Type.DOUBLE;
        negate = new DCMPG(cp);
    }
}
