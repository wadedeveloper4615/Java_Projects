package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.base.PushInstruction;
import com.wade.decompiler.classfile.instructions.base.TypedInstruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

public class ACONST_NULL extends Instruction implements PushInstruction, TypedInstruction {
    public ACONST_NULL(ConstantPool cp) {
        super(InstructionOpCodes.ACONST_NULL, 1, cp);
    }

    @Override
    public Type getType(ConstantPool cp) {
        return Type.NULL;
    }
}