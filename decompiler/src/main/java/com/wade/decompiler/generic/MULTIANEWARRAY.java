package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.AllocationInstruction;
import com.wade.decompiler.generic.base.CPInstruction;
import com.wade.decompiler.generic.base.ClassGenException;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.LoadClass;
import com.wade.decompiler.generic.type.ArrayType;
import com.wade.decompiler.generic.type.ObjectType;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.ByteSequence;

public class MULTIANEWARRAY extends CPInstruction implements LoadClass, AllocationInstruction, ExceptionThrower {
    private short dimensions;

    public MULTIANEWARRAY() {
    }

    public MULTIANEWARRAY(int index, short dimensions) {
        super(InstructionOpCodes.MULTIANEWARRAY, index);
        if (dimensions < 1) {
            throw new ClassGenException("Invalid dimensions value: " + dimensions);
        }
        this.dimensions = dimensions;
        super.setLength(4);
    }

    @Override
    public int consumeStack(ConstantPool cpg) {
        return dimensions;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode().getOpcode());
        out.writeShort(super.getIndex());
        out.writeByte(dimensions);
    }

    public short getDimensions() {
        return dimensions;
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION);
    }

    @Override
    public ObjectType getLoadClassType(ConstantPool cpg) {
        Type t = getType(cpg);
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.initFromFile(bytes, wide);
        dimensions = bytes.readByte();
        super.setLength(4);
    }

    @Override
    public String toString(boolean verbose) {
        return super.toString(verbose) + " " + super.getIndex() + " " + dimensions;
    }

    @Override
    public String toString(ConstantPool cp) {
        return super.toString(cp) + " " + dimensions;
    }
}
