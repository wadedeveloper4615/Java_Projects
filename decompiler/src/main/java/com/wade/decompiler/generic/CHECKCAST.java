package com.wade.decompiler.generic;

import com.wade.decompiler.Const;
import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.generic.base.ArrayType;
import com.wade.decompiler.generic.base.CPInstruction;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.LoadClass;
import com.wade.decompiler.generic.base.ObjectType;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.base.StackProducer;
import com.wade.decompiler.generic.base.Type;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;

public class CHECKCAST extends CPInstruction implements LoadClass, ExceptionThrower, StackProducer, StackConsumer {
    public CHECKCAST() {
    }

    public CHECKCAST(final int index) {
        super(Const.CHECKCAST, index);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitLoadClass(this);
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitCHECKCAST(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.CLASS_CAST_EXCEPTION);
    }

    @Override
    public ObjectType getLoadClassType(final ConstantPoolGen cpg) {
        Type t = getType(cpg);
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }
}
