package com.wade.decompiler.generic;

import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.FieldInstruction;
import com.wade.decompiler.generic.base.PopInstruction;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;

public class PUTFIELD extends FieldInstruction implements PopInstruction, ExceptionThrower {
    public PUTFIELD() {
    }

    public PUTFIELD(int index) {
        super(InstructionOpCodes.PUTFIELD, index);
    }

    @Override
    public void accept(Visitor v) {
        v.visitExceptionThrower(this);
        v.visitStackConsumer(this);
        v.visitPopInstruction(this);
        v.visitTypedInstruction(this);
        v.visitLoadClass(this);
        v.visitCPInstruction(this);
        v.visitFieldOrMethod(this);
        v.visitFieldInstruction(this);
        v.visitPUTFIELD(this);
    }

    @Override
    public int consumeStack(ConstantPoolGen cpg) {
        return getFieldSize(cpg) + 1;
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }
}
