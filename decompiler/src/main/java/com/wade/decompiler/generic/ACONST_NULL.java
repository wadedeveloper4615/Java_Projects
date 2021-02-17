package com.wade.decompiler.generic;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.PushInstruction;
import com.wade.decompiler.generic.base.TypedInstruction;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.generic.type.Type;

public class ACONST_NULL extends Instruction implements PushInstruction, TypedInstruction {
    public ACONST_NULL() {
        super(InstructionOpCodes.ACONST_NULL, 1);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitTypedInstruction(this);
        v.visitACONST_NULL(this);
    }

    @Override
    public Type getType(ConstantPoolGen cp) {
        return Type.NULL;
    }
}
