package com.wade.decompiler.generic;

import java.util.StringTokenizer;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.Constant;
import com.wade.decompiler.classfile.ConstantCP;
import com.wade.decompiler.classfile.ConstantPool;

public abstract class InvokeInstruction extends FieldOrMethod implements ExceptionThrower, StackConsumer, StackProducer {
    InvokeInstruction() {
    }

    protected InvokeInstruction(final short opcode, final int index) {
        super(opcode, index);
    }

    @Override
    public int consumeStack(final ConstantPoolGen cpg) {
        int sum;
        if ((super.getOpcode() == Const.INVOKESTATIC) || (super.getOpcode() == Const.INVOKEDYNAMIC)) {
            sum = 0;
        } else {
            sum = 1; // this reference
        }
        final String signature = getSignature(cpg);
        sum += Type.getArgumentTypesSize(signature);
        return sum;
    }

    public Type[] getArgumentTypes(final ConstantPoolGen cpg) {
        return Type.getArgumentTypes(getSignature(cpg));
    }

    @Override
    public String getClassName(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        final String className = cp.getConstantString(cmr.getClassIndex(), Const.CONSTANT_Class);
        return className.replace('/', '.');
    }

    public String getMethodName(final ConstantPoolGen cpg) {
        return getName(cpg);
    }

    public Type getReturnType(final ConstantPoolGen cpg) {
        return Type.getReturnType(getSignature(cpg));
    }

    @Override
    public Type getType(final ConstantPoolGen cpg) {
        return getReturnType(cpg);
    }

    @Override
    public int produceStack(final ConstantPoolGen cpg) {
        final String signature = getSignature(cpg);
        return Type.getReturnTypeSize(signature);
    }

    @Override
    public String toString(final ConstantPool cp) {
        final Constant c = cp.getConstant(super.getIndex());
        final StringTokenizer tok = new StringTokenizer(cp.constantToString(c));
        final String opcodeName = Const.getOpcodeName(super.getOpcode());
        final StringBuilder sb = new StringBuilder(opcodeName);
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