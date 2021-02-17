package com.wade.decompiler.generic;

import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.CPInstruction;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.LoadClass;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.base.StackProducer;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.generic.type.ArrayType;
import com.wade.decompiler.generic.type.ObjectType;
import com.wade.decompiler.generic.type.Type;

public class INSTANCEOF extends CPInstruction implements LoadClass, ExceptionThrower, StackProducer, StackConsumer {
    public INSTANCEOF() {
    }

    public INSTANCEOF(int index) {
        super(InstructionOpCodes.INSTANCEOF, index);
    }

    @Override
    public void accept(Visitor v) {
        v.visitLoadClass(this);
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitINSTANCEOF(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION);
    }

    @Override
    public ObjectType getLoadClassType(ConstantPoolGen cpg) {
        Type t = getType(cpg);
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }
}
