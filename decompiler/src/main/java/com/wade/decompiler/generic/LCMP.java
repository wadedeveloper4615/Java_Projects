package com.wade.decompiler.generic;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.base.StackProducer;
import com.wade.decompiler.generic.base.TypedInstruction;
import com.wade.decompiler.generic.type.Type;

public class LCMP extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public LCMP(ConstantPool cp) {
        super(InstructionOpCodes.LCMP, 1, cp);
    }

    @Override
    public Type getType(ConstantPool cp) {
        return Type.LONG;
    }
}
