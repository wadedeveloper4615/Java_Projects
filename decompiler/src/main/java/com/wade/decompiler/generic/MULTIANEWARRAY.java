package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.classfile.ConstantPool;
import com.wade.decompiler.generic.base.AllocationInstruction;
import com.wade.decompiler.generic.base.ArrayType;
import com.wade.decompiler.generic.base.CPInstruction;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.LoadClass;
import com.wade.decompiler.generic.base.ObjectType;
import com.wade.decompiler.generic.base.Type;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.util.ByteSequence;

public class MULTIANEWARRAY extends CPInstruction implements LoadClass, AllocationInstruction, ExceptionThrower {
    private short dimensions;

    public MULTIANEWARRAY() {
    }

    public MULTIANEWARRAY(final int index, final short dimensions) {
        super(Const.MULTIANEWARRAY, index);
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
    public void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
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
