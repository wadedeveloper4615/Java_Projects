package com.wade.decompiler.generic;

import java.util.StringTokenizer;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantCP;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.FieldOrMethod;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.base.StackProducer;
import com.wade.decompiler.generic.type.Type;

public abstract class InvokeInstruction extends FieldOrMethod implements ExceptionThrower, StackConsumer, StackProducer {
    InvokeInstruction() {
    }

    protected InvokeInstruction(InstructionOpCodes opcode, int index) {
        super(opcode, index);
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

    @Override
    public String toString(ConstantPool cp) {
        Constant c = cp.getConstant(super.getIndex());
        StringTokenizer tok = new StringTokenizer(cp.constantToString(c));
        String opcodeName = Const.getOpcodeName(super.getOpcode());
        StringBuilder sb = new StringBuilder(opcodeName);
        if (tok.hasMoreTokens()) {
            sb.append(" ");
            sb.append(tok.nextToken().replace('.', '/'));
            if (tok.hasMoreTokens()) {
                sb.append(tok.nextToken());
            }
        }
        return sb.toString();
    }
}
