package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.PushInstruction;
import com.wade.decompiler.generic.base.TypedInstruction;
import com.wade.decompiler.generic.type.Type;

public class ACONST_NULL extends Instruction implements PushInstruction, TypedInstruction {
    public ACONST_NULL(ConstantPool cp) {
        super(InstructionOpCodes.ACONST_NULL, 1, cp);
    }

    @Override
    public Type getType(ConstantPool cp) {
        return Type.NULL;
    }
}
