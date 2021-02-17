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
import com.wade.decompiler.generic.base.CPInstruction;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.ObjectType;
import com.wade.decompiler.generic.base.PushInstruction;
import com.wade.decompiler.generic.base.Type;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.util.ByteSequence;

public class LDC extends CPInstruction implements PushInstruction, ExceptionThrower {
    public LDC() {
    }

    public LDC(final int index) {
        super(Const.LDC_W, index);
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
            case CONSTANT_String:
                return Type.STRING;
            case CONSTANT_Float:
                return Type.FLOAT;
            case CONSTANT_Integer:
                return Type.INT;
            case CONSTANT_Class:
                return Type.CLASS;
            default: // Never reached
                throw new IllegalArgumentException("Unknown or invalid constant type at " + super.getIndex());
        }
    }

    public Object getValue(final ConstantPoolGen cpg) {
        Constant c = cpg.getConstantPool().getConstant(super.getIndex());
        switch (c.getTag()) {
            case CONSTANT_String:
                final int i = ((ConstantString) c).getStringIndex();
                c = cpg.getConstantPool().getConstant(i);
                return ((ConstantUtf8) c).getBytes();
            case CONSTANT_Float:
                return Float.valueOf(((ConstantFloat) c).getBytes());
            case CONSTANT_Integer:
                return Integer.valueOf(((ConstantInteger) c).getBytes());
            case CONSTANT_Class:
                final int nameIndex = ((ConstantClass) c).getNameIndex();
                c = cpg.getConstantPool().getConstant(nameIndex);
                return new ObjectType(((ConstantUtf8) c).getBytes());
            default: // Never reached
                throw new IllegalArgumentException("Unknown or invalid constant type at " + super.getIndex());
        }
    }

    @Override
    public void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
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
        if (super.getIndex() <= Const.MAX_BYTE) { // Fits in one byte?
            super.setOpcode(Const.LDC);
            super.setLength(2);
        } else {
            super.setOpcode(Const.LDC_W);
            super.setLength(3);
        }
    }
}
