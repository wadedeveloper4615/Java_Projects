package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.classfile.Constant;
import com.wade.decompiler.classfile.ConstantClass;
import com.wade.decompiler.classfile.ConstantFloat;
import com.wade.decompiler.classfile.ConstantInteger;
import com.wade.decompiler.classfile.ConstantString;
import com.wade.decompiler.classfile.ConstantUtf8;
import com.wade.decompiler.util.ByteSequence;

public class LDC extends CPInstruction implements PushInstruction, ExceptionThrower {
    LDC() {
    }

    public LDC(final int index) {
        super(com.wade.decompiler.Const.LDC_W, index);
        setSize();
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitLDC(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode());
        if (super.getLength() == 2) { // TODO useless check?
            out.writeByte(super.getIndex());
        } else {
            out.writeShort(super.getIndex());
        }
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_STRING_RESOLUTION);
    }

    @Override
    public Type getType(final ConstantPoolGen cpg) {
        switch (cpg.getConstantPool().getConstant(super.getIndex()).getTag()) {
            case com.wade.decompiler.Const.CONSTANT_String:
                return Type.STRING;
            case com.wade.decompiler.Const.CONSTANT_Float:
                return Type.FLOAT;
            case com.wade.decompiler.Const.CONSTANT_Integer:
                return Type.INT;
            case com.wade.decompiler.Const.CONSTANT_Class:
                return Type.CLASS;
            default: // Never reached
                throw new IllegalArgumentException("Unknown or invalid constant type at " + super.getIndex());
        }
    }

    public Object getValue(final ConstantPoolGen cpg) {
        Constant c = cpg.getConstantPool().getConstant(super.getIndex());
        switch (c.getTag()) {
            case Const.CONSTANT_String:
                final int i = ((ConstantString) c).getStringIndex();
                c = cpg.getConstantPool().getConstant(i);
                return ((ConstantUtf8) c).getBytes();
            case Const.CONSTANT_Float:
                return Float.valueOf(((ConstantFloat) c).getBytes());
            case Const.CONSTANT_Integer:
                return Integer.valueOf(((ConstantInteger) c).getBytes());
            case Const.CONSTANT_Class:
                final int nameIndex = ((ConstantClass) c).getNameIndex();
                c = cpg.getConstantPool().getConstant(nameIndex);
                return new ObjectType(((ConstantUtf8) c).getBytes());
            default: // Never reached
                throw new IllegalArgumentException("Unknown or invalid constant type at " + super.getIndex());
        }
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.setLength(2);
        super.setIndex(bytes.readUnsignedByte());
    }

    @Override
    public final void setIndex(final int index) {
        super.setIndex(index);
        setSize();
    }

    // Adjust to proper size
    protected final void setSize() {
        if (super.getIndex() <= com.wade.decompiler.Const.MAX_BYTE) { // Fits in one byte?
            super.setOpcode(com.wade.decompiler.Const.LDC);
            super.setLength(2);
        } else {
            super.setOpcode(com.wade.decompiler.Const.LDC_W);
            super.setLength(3);
        }
    }
}
