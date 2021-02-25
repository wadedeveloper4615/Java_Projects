package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.Instruction;
import com.wade.decompiler.classfile.instructions.base.StackConsumer;
import com.wade.decompiler.classfile.instructions.base.StackProducer;
import com.wade.decompiler.classfile.instructions.base.TypedInstruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.InstructionOpCodes;

public class DCMPG extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
    public DCMPG(ConstantPool cp) {
        super(InstructionOpCodes.DCMPG, 1, cp);
    }

    @Override
    public Type getType(ConstantPool cp) {
        return Type.DOUBLE;
    }
}