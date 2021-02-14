package org.apache.bcel.generic.base;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.StackProducer;

public abstract class InvokeInstruction extends FieldOrMethod implements ExceptionThrower, StackConsumer, StackProducer {
    public InvokeInstruction() {
    }

    public InvokeInstruction(InstructionOpCodes opcode, final int index) {
        super(opcode, index);
    }
//
//    @Override
//    public int consumeStack(final ConstantPoolGen cpg) {
//        int sum;
//        if ((super.getOpcode() == InstructionOpCodes.INVOKESTATIC) || (super.getOpcode() == InstructionOpCodes.INVOKEDYNAMIC)) {
//            sum = 0;
//        } else {
//            sum = 1;
//        }
//        final String signature = getSignature(cpg);
//        sum += Type.getArgumentTypesSize(signature);
//        return sum;
//    }
//
//    public Type[] getArgumentTypes(final ConstantPoolGen cpg) {
//        return Type.getArgumentTypes(getSignature(cpg));
//    }
//
//    @Override
//    public String getClassName(final ConstantPoolGen cpg) {
//        final ConstantPool cp = cpg.getConstantPool();
//        final ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
//        final String className = cp.getConstantString(cmr.getClassIndex(), ClassFileConstants.CONSTANT_Class);
//        return className.replace('/', '.');
//    }
//
//    public String getMethodName(final ConstantPoolGen cpg) {
//        return getName(cpg);
//    }
//
//    public Type getReturnType(final ConstantPoolGen cpg) {
//        return Type.getReturnType(getSignature(cpg));
//    }
//
//    @Override
//    public Type getType(final ConstantPoolGen cpg) {
//        return getReturnType(cpg);
//    }
//
//    @Override
//    public int produceStack(final ConstantPoolGen cpg) {
//        final String signature = getSignature(cpg);
//        return Type.getReturnTypeSize(signature);
//    }
//    @Override
//    public String toString(final ConstantPool cp) {
//        final Constant c = cp.getConstant(super.getIndex());
//        final StringTokenizer tok = new StringTokenizer(cp.constantToString(c));
//        final String opcodeName = super.getOpcode().getName();
//        final StringBuilder sb = new StringBuilder(opcodeName);
//        if (tok.hasMoreTokens()) {
//            sb.append(" ");
//            sb.append(tok.nextToken().replace('.', '/'));
//            if (tok.hasMoreTokens()) {
//                sb.append(tok.nextToken());
//            }
//        }
//        return sb.toString();
//    }
}
