package org.apache.bcel.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.classfile.constant.Constant;
import org.apache.bcel.generic.base.CPInstruction;
import org.apache.bcel.generic.base.ExceptionThrower;
import org.apache.bcel.generic.base.PushInstruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.gen.ConstantPoolGen;
import org.apache.bcel.util.ByteSequence;

public class LDC extends CPInstruction implements PushInstruction, ExceptionThrower {
    public LDC() {
    }

    public LDC(final int index) {
        super(org.apache.bcel.Const.LDC_W, index);
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
                final int i = ((org.apache.bcel.classfile.constant.ConstantString) c).getStringIndex();
                c = cpg.getConstantPool().getConstant(i);
                return ((org.apache.bcel.classfile.constant.ConstantUtf8) c).getBytes();
            case CONSTANT_Float:
                return new Float(((org.apache.bcel.classfile.constant.ConstantFloat) c).getBytes());
            case CONSTANT_Integer:
                return Integer.valueOf(((org.apache.bcel.classfile.constant.ConstantInteger) c).getBytes());
            case CONSTANT_Class:
                final int nameIndex = ((org.apache.bcel.classfile.constant.ConstantClass) c).getNameIndex();
                c = cpg.getConstantPool().getConstant(nameIndex);
                return new ObjectType(((org.apache.bcel.classfile.constant.ConstantUtf8) c).getBytes());
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
        if (super.getIndex() <= org.apache.bcel.Const.MAX_BYTE) { // Fits in one byte?
            super.setOpcode(org.apache.bcel.Const.LDC);
            super.setLength(2);
        } else {
            super.setOpcode(org.apache.bcel.Const.LDC_W);
            super.setLength(3);
        }
    }
}
