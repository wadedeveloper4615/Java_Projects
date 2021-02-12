package org.apache.bcel.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.generic.base.AllocationInstruction;
import org.apache.bcel.generic.base.ArrayType;
import org.apache.bcel.generic.base.CPInstruction;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.base.ExceptionThrower;
import org.apache.bcel.generic.base.LoadClass;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.generic.gen.ConstantPoolGen;
import org.apache.bcel.util.ByteSequence;

public class MULTIANEWARRAY extends CPInstruction implements LoadClass, AllocationInstruction, ExceptionThrower {
    private short dimensions;

    public MULTIANEWARRAY() {
    }

    public MULTIANEWARRAY(final int index, final short dimensions) {
        super(org.apache.bcel.Const.MULTIANEWARRAY, index);
        if (dimensions < 1) {
            throw new ClassGenException("Invalid dimensions value: " + dimensions);
        }
        this.dimensions = dimensions;
        super.setLength(4);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitLoadClass(this);
        v.visitAllocationInstruction(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitMULTIANEWARRAY(this);
    }

    @Override
    public int consumeStack(final ConstantPoolGen cpg) {
        return dimensions;
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode());
        out.writeShort(super.getIndex());
        out.writeByte(dimensions);
    }

    public final short getDimensions() {
        return dimensions;
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION);
    }

    @Override
    public ObjectType getLoadClassType(final ConstantPoolGen cpg) {
        Type t = getType(cpg);
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.initFromFile(bytes, wide);
        dimensions = bytes.readByte();
        super.setLength(4);
    }

    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + super.getIndex() + " " + dimensions;
    }

    @Override
    public String toString(final ConstantPool cp) {
        return super.toString(cp) + " " + dimensions;
    }
}
