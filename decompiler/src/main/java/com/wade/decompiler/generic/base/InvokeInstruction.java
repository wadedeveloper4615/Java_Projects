package com.wade.decompiler.generic.base;

import com.wade.decompiler.classfile.constant.ConstantCP;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.type.Type;

public abstract class InvokeInstruction extends FieldOrMethod implements ExceptionThrower, StackConsumer, StackProducer {
    public InvokeInstruction() {
    }

    public InvokeInstruction(InstructionOpCodes opcode, int index, ConstantPool cp) {
        super(opcode, cp, index);
    }

    @Override
    public int consumeStack(ConstantPool cpg) {
        int sum;
        if ((super.getOpcode() == InstructionOpCodes.INVOKESTATIC) || (super.getOpcode() == InstructionOpCodes.INVOKEDYNAMIC)) {
            sum = 0;
        } else {
            sum = 1; // this reference
        }
        String signature = getSignature(cpg);
        sum += Type.getArgumentTypesSize(signature);
        return sum;
    }

    public Type[] getArgumentTypes(ConstantPool cpg) {
        return Type.getArgumentTypes(getSignature(cpg));
    }

    @Override
    public String getClassName(ConstantPool cp) {
        ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        String className = cp.getConstantString(cmr.getClassIndex(), ClassFileConstants.CONSTANT_Class);
        return className.replace('/', '.');
    }

    public String getMethodName(ConstantPool cpg) {
        return getName(cpg);
    }

    public Type getReturnType(ConstantPool cpg) {
        return Type.getReturnType(getSignature(cpg));
    }

    @Override
    public Type getType(ConstantPool cpg) {
        return getReturnType(cpg);
    }

    @Override
    public int produceStack(ConstantPool cpg) {
        String signature = getSignature(cpg);
        return Type.getReturnTypeSize(signature);
    }
}
