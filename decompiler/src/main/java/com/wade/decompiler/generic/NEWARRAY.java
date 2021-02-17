package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.ExceptionConst;
import com.wade.decompiler.generic.base.AllocationInstruction;
import com.wade.decompiler.generic.base.ArrayType;
import com.wade.decompiler.generic.base.BasicType;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.StackProducer;
import com.wade.decompiler.generic.base.Type;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.util.ByteSequence;

public class NEWARRAY extends Instruction implements AllocationInstruction, ExceptionThrower, StackProducer {
    private byte type;

    public NEWARRAY() {
    }

    public NEWARRAY(final BasicType type) {
        this(type.getType());
    }

    public NEWARRAY(final byte type) {
        super(Const.NEWARRAY, (short) 2);
        this.type = type;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitAllocationInstruction(this);
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitNEWARRAY(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode());
        out.writeByte(type);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION };
    }

    public final Type getType() {
        return new ArrayType(BasicType.getType(type), 1);
    }

    public final byte getTypecode() {
        return type;
    }

    @Override
    public void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        type = bytes.readByte();
        super.setLength(2);
    }

    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + Const.getTypeName(type);
    }
}
