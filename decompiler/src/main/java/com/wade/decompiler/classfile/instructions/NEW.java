package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.CPInstruction;
import com.wade.decompiler.classfile.instructions.base.inter.AllocationInstruction;
import com.wade.decompiler.classfile.instructions.base.inter.ExceptionThrower;
import com.wade.decompiler.classfile.instructions.base.inter.LoadClass;
import com.wade.decompiler.classfile.instructions.base.inter.StackProducer;
import com.wade.decompiler.classfile.instructions.type.ObjectType;
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
public class NEW extends CPInstruction implements LoadClass, AllocationInstruction, ExceptionThrower, StackProducer {
    public NEW(int index, ConstantPool cp) {
        super(InstructionOpCodes.NEW, cp, index);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INSTANTIATION_ERROR);
    }

    @Override
    public ObjectType getLoadClassType() {
        return (ObjectType) getType();
    }

    @Override
    public int produceStack() {
        return opcode.getProduceStack();
    }
}
