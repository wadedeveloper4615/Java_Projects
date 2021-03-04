package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.CPInstruction;
import com.wade.decompiler.classfile.instructions.base.inter.AllocationInstruction;
import com.wade.decompiler.classfile.instructions.base.inter.ExceptionThrower;
import com.wade.decompiler.classfile.instructions.base.inter.LoadClass;
import com.wade.decompiler.classfile.instructions.base.inter.StackConsumer;
import com.wade.decompiler.classfile.instructions.base.inter.StackProducer;
import com.wade.decompiler.classfile.instructions.type.ArrayType;
import com.wade.decompiler.classfile.instructions.type.ObjectType;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.ExceptionConst;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ANEWARRAY extends CPInstruction implements LoadClass, AllocationInstruction, ExceptionThrower, StackConsumer, StackProducer {
    public ANEWARRAY(int index, ConstantPool cp) {
        super(InstructionOpCodes.ANEWARRAY, cp, index);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION);
    }

    @Override
    public ObjectType getLoadClassType() {
        Type t = getType();
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }

    @Override
    public int produceStack() {
        return opcode.getProduceStack();
    }
}
