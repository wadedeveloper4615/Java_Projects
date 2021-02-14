package org.apache.bcel.generic;

import java.io.IOException;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.base.InvokeInstruction;
import org.apache.bcel.util.ByteSequence;

public final class INVOKEINTERFACE extends InvokeInstruction {
    private int nargs;

    public INVOKEINTERFACE() {
    }

    public INVOKEINTERFACE(final int index, final int nargs) {
        super(InstructionOpCodes.INVOKEINTERFACE, index);
        super.setLength(5);
        if (nargs < 1) {
            throw new ClassGenException("Number of arguments must be > 0 " + nargs);
        }
        this.nargs = nargs;
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitExceptionThrower(this);
//        v.visitTypedInstruction(this);
//        v.visitStackConsumer(this);
//        v.visitStackProducer(this);
//        v.visitLoadClass(this);
//        v.visitCPInstruction(this);
//        v.visitFieldOrMethod(this);
//        v.visitInvokeInstruction(this);
//        v.visitINVOKEINTERFACE(this);
//    }

    @Override
    public int consumeStack() {// final ConstantPoolGen cpg) {
        return nargs;
    }
//    @Override
//    public void dump(final DataOutputStream out) throws IOException {
//        out.writeByte(super.getOpcode().getOpcode());
//        out.writeShort(super.getIndex());
//        out.writeByte(nargs);
//        out.writeByte(0);
//    }

    public int getCount() {
        return nargs;
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_INTERFACE_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.initFromFile(bytes, wide);
        super.setLength(5);
        nargs = bytes.readUnsignedByte();
        bytes.readByte();
    }

    @Override
    public String toString(final ConstantPool cp) {
        return super.toString(cp) + " " + nargs;
    }
}
