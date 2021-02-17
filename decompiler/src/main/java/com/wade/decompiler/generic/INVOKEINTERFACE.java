package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.classfile.ConstantPool;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.util.ByteSequence;

public final class INVOKEINTERFACE extends InvokeInstruction {
    private int nargs; // Number of arguments on stack (number of stack slots), called "count" in
    // vmspec2

    public INVOKEINTERFACE() {
    }

    public INVOKEINTERFACE(final int index, final int nargs) {
        super(Const.INVOKEINTERFACE, index);
        super.setLength(5);
        if (nargs < 1) {
            throw new ClassGenException("Number of arguments must be > 0 " + nargs);
        }
        this.nargs = nargs;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitStackProducer(this);
        v.visitLoadClass(this);
        v.visitCPInstruction(this);
        v.visitFieldOrMethod(this);
        v.visitInvokeInstruction(this);
        v.visitINVOKEINTERFACE(this);
    }

    @Override
    public int consumeStack(final ConstantPoolGen cpg) { // nargs is given in byte-code
        return nargs; // nargs includes this reference
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode());
        out.writeShort(super.getIndex());
        out.writeByte(nargs);
        out.writeByte(0);
    }

    public int getCount() {
        return nargs;
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_INTERFACE_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }

    @Override
    public void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.initFromFile(bytes, wide);
        super.setLength(5);
        nargs = bytes.readUnsignedByte();
        bytes.readByte(); // Skip 0 byte
    }

    @Override
    public String toString(final ConstantPool cp) {
        return super.toString(cp) + " " + nargs;
    }
}
