package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.base.StackConsumer;
import com.wade.decompiler.classfile.instructions.base.StackProducer;
import com.wade.decompiler.classfile.instructions.base.TypedInstruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

public class LCMP extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public LCMP(ConstantPool cp) {
        super(InstructionOpCodes.LCMP, 1, cp);
    }

    @Override
    public Type getType() {
        return Type.LONG;
    }
}
