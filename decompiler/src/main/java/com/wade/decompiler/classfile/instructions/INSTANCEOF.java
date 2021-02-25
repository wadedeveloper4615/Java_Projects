package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.CPInstruction;
import com.wade.decompiler.classfile.instructions.base.ExceptionThrower;
import com.wade.decompiler.classfile.instructions.base.LoadClass;
import com.wade.decompiler.classfile.instructions.base.StackConsumer;
import com.wade.decompiler.classfile.instructions.base.StackProducer;
import com.wade.decompiler.classfile.instructions.type.ArrayType;
import com.wade.decompiler.classfile.instructions.type.ObjectType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;

public class INSTANCEOF extends CPInstruction implements LoadClass, ExceptionThrower, StackProducer, StackConsumer {
    public INSTANCEOF() {
    }

    public INSTANCEOF(int index, ConstantPool cp) {
        super(InstructionOpCodes.INSTANCEOF, cp, index);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION);
    }

    @Override
    public ObjectType getLoadClassType(ConstantPool cpg) {
        Type t = getType(cpg);
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }
}