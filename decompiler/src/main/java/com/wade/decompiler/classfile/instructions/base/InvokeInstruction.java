package com.wade.decompiler.classfile.instructions.base;

import com.wade.decompiler.classfile.constant.ConstantConstantPool;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public abstract class InvokeInstruction extends FieldOrMethodInstruction implements ExceptionThrower, StackConsumer, StackProducer {
    public InvokeInstruction(InstructionOpCodes opcode, int index, ConstantPool cp) {
        super(opcode, cp, index);
    }

    @Override
    public int consumeStack() {
        int sum = 0;
        if ((super.getOpcode() == InstructionOpCodes.INVOKESTATIC) || (super.getOpcode() == InstructionOpCodes.INVOKEDYNAMIC)) {
            sum = 0;
        } else {
            sum = 1;
        }
        sum += Type.getArgumentTypesSize(signature);
        return sum;
    }

    public Type[] getArgumentTypes() {
        return Type.getArgumentTypes(this.getSignature());
    }

    public String getClassName(ConstantPool cp) {
        ConstantConstantPool cmr = (ConstantConstantPool) cp.getConstant(super.getIndex());
        String className = cp.getConstantString(cmr.getClassIndex(), ClassFileConstants.CONSTANT_Class);
        return className.replace('/', '.');
    }

    public Type getReturnType() {
        return Type.getReturnType(this.getSignature());
    }

    @Override
    public Type getType() {
        return getReturnType();
    }

    @Override
    public int produceStack() {
        return Type.getReturnTypeSize(signature);
    }
}
